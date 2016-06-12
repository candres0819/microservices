package com.carloscardona.microservices.services.store;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.carloscardona.microservices.services.store.model.Store;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableCircuitBreaker
public class StoreApplication extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Store.class);
	}

	@PostConstruct
	public void exposeIds() {
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}