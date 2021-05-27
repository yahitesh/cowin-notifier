package com.yahitesh;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yahitesh.config.NotificationHolder;
import com.yahitesh.model.Notification;
import com.yahitesh.repository.NotificationRepository;

@Component
public class PortalServiceLifeCycle implements CommandLineRunner {

	@Autowired
	NotificationHolder notificationHolder;
	@Autowired
	NotificationRepository notificationRepository;

	@Transactional
	public void run(String... arg0) throws Exception {

		System.out.println("###START FROM THE LIFECYCLE###");
		List<Notification> list = notificationHolder.readFromFile();
		notificationRepository.saveAll(list);
		System.out.println("found::"+notificationRepository.findAll());
	}

	@PreDestroy
	public void onExit() {
		System.out.println("###STOP FROM THE LIFECYCLE###");
	}
}
