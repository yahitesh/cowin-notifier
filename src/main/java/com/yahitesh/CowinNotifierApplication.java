/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@SpringBootApplication
@EnableScheduling
public class CowinNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CowinNotifierApplication.class, args);
	}
}
