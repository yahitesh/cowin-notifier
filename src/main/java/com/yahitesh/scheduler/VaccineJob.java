package com.yahitesh.scheduler;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yahitesh.config.NotificationHolder;
import com.yahitesh.endpoint.State;
import com.yahitesh.model.Email;
import com.yahitesh.model.Notification;
import com.yahitesh.model.StateData.District;
import com.yahitesh.model.VaccineInfo;
import com.yahitesh.repository.NotificationRepository;
import com.yahitesh.services.EmailServiceImpl;
import com.yahitesh.services.VaccineService;

@Component
public class VaccineJob {
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	@Autowired
	private NotificationHolder notificationHolder;
	@Autowired
	VaccineService vaccineService;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void midNightJob() {
		List<Notification> notifications = notificationRepository.findAll();
		notifications.forEach(notify -> {
			notify.setNotify("N");
		});
		notificationRepository.saveAll(notifications);
	}

	@Scheduled(cron = "0/60 * * * * ?")
	@Transactional
	public void execute() {
		calendarByPinJob();
	}

	private void calendarByPinJob() {
		List<Notification> notifications = notificationRepository.findAllUnNotify("N");
		List<String> pincodeList = notifications.stream().map(Notification::getPincodes).collect(Collectors.toList())
				.stream().distinct().collect(Collectors.toList());
		for (String pincode : pincodeList) {
			List<VaccineInfo> vaccineList = vaccineService.calendarByPin(pincode, DATE_FORMAT.format(new Date()));
			List<Notification> userList = notifications.stream().filter(p -> p.getPincodes().equals(pincode))
					.collect(Collectors.toList());
			userList.forEach(user -> {
				Email email = new Email();
				email.setRecipients(Arrays.asList(user.getEmail()));
				email.setSubject("Cowin Dose Notification");
				email.setBody(vaccineList.toString());
				emailServiceImpl.sendSimpleMessage(email);
				System.out.println("email send");
				user.setNotify("Y");
				notificationRepository.save(user);
				notificationHolder.updateRecord(user);
			});
		}
	}

	private void test() {

		List<State> stateList = Arrays.asList(State.values());

		for (State s : stateList) {
			List<District> districtList = new VaccineService().findAllDistrict(s.getId());
			for (District district : districtList) {
				List<VaccineInfo> list = new VaccineService()
						.calendarByDistrict(String.valueOf(district.getDistrict_id()), "27-05-21");
				List<Notification> notifications = notificationRepository.findAllUnNotify("N");
				for (Notification notification : notifications) {
					String pincode = notification.getPincodes();
					List<VaccineInfo> filter = list.stream().filter(e -> e.pincode.equals(pincode))
							.collect(Collectors.toList());
					if (!filter.isEmpty()) {
						Email email = new Email();
						email.setRecipients(Arrays.asList(notification.getEmail()));
						email.setSubject("Cowin Dose Notification");
						email.setBody(filter.toString());
						emailServiceImpl.sendSimpleMessage(email);
						System.out.println("email send");
						notification.setNotify("Y");
						notificationRepository.save(notification);
						notificationHolder.updateRecord(notification);
					}
				}

			}
		}

	}

}
