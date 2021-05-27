/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

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

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Component
public class PortalServiceLifeCycle implements CommandLineRunner {

	@Autowired
	NotificationHolder notificationHolder;

	@Autowired
	NotificationRepository notificationRepository;

	@Transactional
	public void run(String... arg0) throws Exception {
		System.out.println("***START COWIN NOTIFIRE***");
		List<Notification> list = notificationHolder.readFromFile();
		notificationRepository.saveAll(list);
		System.out.println("found::" + notificationRepository.findAll());
	}

	@PreDestroy
	public void onExit() {
		System.out.println("***STOP COWIN NOTIFIRE***");
	}
}
