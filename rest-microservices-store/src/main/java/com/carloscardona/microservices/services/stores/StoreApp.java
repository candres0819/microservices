package com.carloscardona.microservices.services.stores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carloscardona.microservices.services.stores.model.Store;

@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
public class StoreApp extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Store.class);
	}

	@PostConstruct
	public void exposeIds() {
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreApp.class, args);
	}

	@Controller
	public static class SimpleStoresController {
		@Autowired
		StoreRepository repository;

		@RequestMapping("/simple/stores")
		@ResponseBody
		List<Store> getStores() {
			Page<Store> all = repository.findAll(new PageRequest(0, 10));
			return all.getContent();
		}
	}

	@Configuration
	@Profile("cloud")
	protected static class CloudFoundryConfiguration {

		@Bean
		public Cloud cloud() {
			return new CloudFactory().getCloud();
		}
	}
}