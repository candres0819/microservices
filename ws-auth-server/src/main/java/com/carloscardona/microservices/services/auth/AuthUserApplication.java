/**
 * 
 */
package com.carloscardona.microservices.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Main Spring Boot Application class that starts the authorization server.</br>
 * </br>
 * 
 * Note that the server is also a Eureka client so as to register with the Eureka server and be auto-discovered by other Eureka clients.
 *
 * @author candr
 */

@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
public class AuthUserApplication {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthUserApplication.class, args);
	}
}
