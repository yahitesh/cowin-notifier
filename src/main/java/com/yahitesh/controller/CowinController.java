package com.yahitesh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yahitesh.config.NotificationHolder;
import com.yahitesh.model.Email;
import com.yahitesh.model.Notification;
import com.yahitesh.model.VaccineInfo;
import com.yahitesh.repository.NotificationRepository;
import com.yahitesh.services.EmailServiceImpl;
import com.yahitesh.services.VaccineService;

@Controller
@RequestMapping("/cowin")
public class CowinController {

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private NotificationHolder notificationHolder;

	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@PostMapping("/notify")
	@Transactional
	public String viewFilter(@RequestParam("email") String email, @RequestParam("pincode") String pincode,Map<String, Object> model) {
		Notification exist = notificationRepository.findByEmail(email);
		String message;
		if (null != exist) {
			exist.setPincodes(pincode);
			exist.setNotify("N");
			notificationRepository.save(exist);
			notificationHolder.updateRecord(exist);
			message = "Your "+pincode+" pincode is updated for notification";
		} else {
			Notification notification = new Notification();
			notification.setEmail(email);
			notification.setPincodes(pincode);
			notification.setNotify("N");
			notificationRepository.save(notification);
			notificationHolder.saveRecord(notification);
			message = "You will recive notification on "+pincode;
		}
		model.put("message", message);
		return "index";
	}

	@GetMapping("/greeting")
	public Email greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		List<VaccineInfo> info = new VaccineService().calendarByDistrict("644", "25-05-21");
		Email email = new Email();
		email.setRecipients(Arrays.asList("classyprashant@gmail.com"));
		email.setSubject("test");
		email.setBody(info.toString());
		emailServiceImpl.sendSimpleMessage(email);
		System.out.println("email send");
		return new Email();
	}
}
