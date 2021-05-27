package com.yahitesh.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.stream.Collectors;

import com.yahitesh.model.Email;
@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;

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
			System.out.println("Sending e-mail error: {}"+e.getMessage());
		}
		return isSent;
	}
}
