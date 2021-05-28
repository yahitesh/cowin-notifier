/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Configuration
public class EmailConfiguration {

	@Autowired
	private ProviderConfiguration providerConfiguration;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(providerConfiguration.getHost());
		javaMailSender.setPort(providerConfiguration.getPort());
		javaMailSender.setUsername(providerConfiguration.getUsername());
		javaMailSender.setPassword(providerConfiguration.getPassword());
		Properties properties = javaMailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "false");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", providerConfiguration.getDebug().toString());

		return javaMailSender;
	}

}
