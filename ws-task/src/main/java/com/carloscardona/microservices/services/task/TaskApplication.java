/**
 * 
 */
package com.carloscardona.microservices.services.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * The boot application class that defines the spring boot application to have the following properties<br>
 * <br>
 * 
 * <ol>
 * <li>Act as a Eureka client; this behavior is provided by the {@link EnableEurekaClient} annotation. The Eureka server URL is provided by
 * the external configuration provided by the config server.</li>
 * <li>The security is enabled to be covered by OAuth2 access token using the {@link EnableResourceServer} annotation. The URL from where
 * the user would be authenticated is provided by the <code>spring.oauth2.resource.userInfoUri</code> property defined in the external
 * configuration.</li>
 * <li>{@link EnableEurekaClient} makes the app into both a Eureka "instance" (i.e. it registers itself) and a "client" (i.e. it can query
 * the registry to locate other services).</li>
 * <li>{@link EnableCircuitBreaker} allows the application to respond to + * failures on services it relies. For example consider that you
 * have an + * "employee" service that uses the "address" service to get addresses. Now if + * the address service goes down, then we can
 * provide a fallback method using + * the circuit breaker. Now the address would be sent back using the static + * value from the method
 * till the "address" service comes back again.</li>
 * <li>Note that all these annotations work in conjunction with properties defined in the external configuration files specified by the
 * config server.</li>
 * </ol>
 * 
 * @author rohitghatol
 *
 */
@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableResourceServer
@EnableCircuitBreaker
public class TaskApplication {
	public static void main(String[] args) {

		SpringApplication.run(TaskApplication.class, args);

	}
}
