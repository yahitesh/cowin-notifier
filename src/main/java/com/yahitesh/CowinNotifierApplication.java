package com.yahitesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CowinNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CowinNotifierApplication.class, args);
	}
}
