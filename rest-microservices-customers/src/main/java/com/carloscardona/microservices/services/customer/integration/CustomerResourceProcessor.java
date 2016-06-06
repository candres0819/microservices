package com.carloscardona.microservices.services.customer.integration;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import com.carloscardona.microservices.services.customer.integration.StoreIntegration;
import com.carloscardona.microservices.services.customer.model.Customer;
import com.carloscardona.microservices.services.customer.model.Location;

@Component
public class CustomerResourceProcessor implements ResourceProcessor<Resource<Customer>> {

	private static final String X_FORWARDED_HOST = "X-Forwarded-Host";

	@Autowired
	private StoreIntegration storeIntegration;

	@Autowired
	private Provider<HttpServletRequest> request;

	@Override
	public Resource<Customer> process(Resource<Customer> resource) {
		Customer customer = resource.getContent();
		Location location = customer.getAddress().getLocation();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("location", String.format("%s,%s", location.getLatitude(), location.getLongitude()));
		parameters.put("distance", "50km");
		String host = this.request.get().getHeader(X_FORWARDED_HOST);
		Link link = this.storeIntegration.getStoresByLocationLink(parameters, host);
		if (link != null) {
			resource.add(link.withRel("stores-nearby"));
		}
		return resource;
	}
}