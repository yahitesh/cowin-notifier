/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.controller;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yahitesh.cowin.config.InMemoryDbServiceImpl;
import com.yahitesh.cowin.model.Notification;
import com.yahitesh.cowin.repository.NotificationRepository;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Controller
@RequestMapping("/cowin")
public class CowinController {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private InMemoryDbServiceImpl notificationHolder;

	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@PostMapping("/notify")
	@Transactional
	public String viewFilter(@RequestParam("email") String email, @RequestParam("pincode") String pincode,
			Map<String, Object> model) {
		Notification exist = notificationRepository.findByEmail(email);
		String message;
		if (null != exist) {
			exist.setPincodes(pincode);
			exist.setNotify("N");
			notificationRepository.save(exist);
			notificationHolder.updateRecord(exist);
			message = "Your " + pincode + " pincode is updated for notification";
		} else {
			Notification notification = new Notification();
			notification.setEmail(email);
			notification.setPincodes(pincode);
			notification.setNotify("N");
			notificationRepository.save(notification);
			notificationHolder.saveRecord(notification);
			message = "You will recive notification on " + pincode;
		}
		model.put("message", message);
		return "index";
	}

}
