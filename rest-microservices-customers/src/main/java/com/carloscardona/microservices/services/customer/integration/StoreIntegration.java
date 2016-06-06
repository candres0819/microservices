package com.carloscardona.microservices.services.customer.integration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@ConfigurationProperties("integration.stores")
public class StoreIntegration {

	Logger log;

	private LoadBalancerClient loadBalancer;

	private String uri = "http://localhost:8085/stores";

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	@HystrixCommand(fallbackMethod = "defaultLink")
	public Link getStoresByLocationLink(Map<String, Object> parameters, String host) {
		URI storesUri = URI.create(uri);

		ServiceInstance instance = null;
		try {
			instance = loadBalancer.choose("stores");
			if (instance != null) {
				storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
			}
		} catch (RuntimeException e) {
			// Eureka not available
		}

		log.info("Trying to access the stores system at {}…", storesUri);

		// TODO: all of the above could be replaced with restTemplate/ribbon
		// The uri would be http://stores
		// traverson.setRestOperations and stuff from Traverson.createDefaultTemplate
		Traverson traverson = new Traverson(storesUri, MediaTypes.HAL_JSON);

		Link link = traverson.follow("stores", "search", "by-location").withTemplateParameters(parameters).asLink();

		String href = link.getHref();
		if (host != null && instance != null) {
			href = reconstructURI(host, href);
		}
		log.info("Found stores-by-location link pointing to {}.", href);

		return new Link(href, link.getRel());
	}

	private String reconstructURI(String host, String href) {
		URI original;
		try {
			original = new URI(href);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Cannot create URI from: " + href);
		}
		int port = 80;
		if ("https".equals(original.getScheme())) {
			port = 443;
		}
		if (host.contains(":")) {
			String[] pair = host.split(":");
			host = pair[0];
			port = Integer.valueOf(pair[1]);
		}
		if (host.equals(original.getHost()) && port == original.getPort()) {
			return href;
		}
		String scheme = original.getScheme();
		if (scheme == null) {
			scheme = port == 443 ? "https" : "http";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(scheme).append("://");
		if (StringUtils.hasText(original.getRawUserInfo())) {
			sb.append(original.getRawUserInfo()).append("@");
		}
		sb.append(host);
		if (port >= 0) {
			sb.append(":").append(port);
		}
		sb.append(original.getRawPath());
		if (StringUtils.hasText(original.getRawQuery())) {
			sb.append("?").append(original.getRawQuery());
		}
		if (StringUtils.hasText(original.getRawFragment())) {
			sb.append("#").append(original.getRawFragment());
		}
		return sb.toString();
	}

	public Link defaultLink(Map<String, Object> parameters, String host) {
		return null;
	}
}