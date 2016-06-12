/**
 * 
 */
package com.carloscardona.microservices.services.store.config;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Resource server configuration defining what endpoints are protected.
 * 
 * @author anilallewar
 *
 */
@Configuration
@EnableResourceServer
public class StoreConfiguration extends ResourceServerConfigurerAdapter {

	@Configuration
	@Profile("cloud")
	protected static class CloudFoundryConfiguration {
		@Bean
		public Cloud cloud() {
			return new CloudFactory().getCloud();
		}
	}

	/**
	 * Provide security so that endpoints are only served if the request is already authenticated.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

	}

	/**
	 * Id of the resource that you are letting the client have access to. Supposing you have another api ("say api2"), then you can
	 * customize the access within resource server to define what api is for what resource id. <br>
	 * <br>
	 * 
	 * So suppose you have 2 APIs, then you can define 2 resource servers.
	 * <ol>
	 * <li>Client 1 has been configured for access to resourceid1, so he can only access "api1" if the resource server configures the
	 * resourceid to "api1".</li>
	 * <li>Client 1 can't access resource server 2 since it has configured the resource id to "api2"</li>
	 * </ol>
	 * 
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("apis");
	}
}
