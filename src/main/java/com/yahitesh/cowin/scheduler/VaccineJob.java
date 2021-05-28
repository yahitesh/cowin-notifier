/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.scheduler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yahitesh.cowin.config.InMemoryDbServiceImpl;
import com.yahitesh.cowin.constant.Constant;
import com.yahitesh.cowin.model.Email;
import com.yahitesh.cowin.model.Notification;
import com.yahitesh.cowin.model.VaccineInfo;
import com.yahitesh.cowin.repository.NotificationRepository;
import com.yahitesh.cowin.services.EmailService;
import com.yahitesh.cowin.services.VaccineService;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Component
public class VaccineJob {

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private InMemoryDbServiceImpl inMemoryDbServiceImpl;
	@Autowired
	VaccineService vaccineService;

	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void midNightJob() {
		List<Notification> notifications = notificationRepository.findByNotify(Constant.FLAG_Y);
		notifications.forEach(notify -> {
			notify.setNotify(Constant.FLAG_N);
		});
		notificationRepository.saveAll(notifications);
	}

	@Scheduled(cron = "0/60 * * * * ?")
	@Transactional
	public void execute() {
		calendarByPinJob();
	}

	private void calendarByPinJob() {
		List<Notification> notifications = notificationRepository.findByNotify(Constant.FLAG_N);
		notifications.stream().map(Notification::getPincodes).collect(Collectors.toList()).stream().distinct()
				.collect(Collectors.toList()).forEach(pincode -> {
					List<VaccineInfo> vaccineList = vaccineService.calendarByPin(pincode,
							Constant.DATE_FORMAT.format(new Date()));
					System.out.println(vaccineList);
					List<Notification> userList = notifications.stream().filter(p -> p.getPincodes().equals(pincode))
							.collect(Collectors.toList());
					if (!vaccineList.isEmpty()) {
						userList.forEach(user -> {
							Email email = Email.builder().recipients(Arrays.asList(user.getEmail()))
									.subject(Constant.EMAIL_SUBJECT).body(vaccineList.toString()).build();
							if (emailService.sendSimpleMessage(email)) {
								user.setNotify(Constant.FLAG_Y);
								notificationRepository.save(user);
								inMemoryDbServiceImpl.updateRecord(user);
							}
						});
					}
				});
	}

}
