/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.yahitesh.model.Email;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public Boolean sendSimpleMessage(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getRecipients().stream().collect(Collectors.joining(",")));
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getBody());

		Boolean isSent = false;
		try {
			emailSender.send(mailMessage);
			isSent = true;
		} catch (Exception e) {
			System.out.println("Sending e-mail error: {}" + e.getMessage());
		}
		return isSent;
	}
}
