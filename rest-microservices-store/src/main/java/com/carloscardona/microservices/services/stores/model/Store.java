package com.carloscardona.microservices.services.stores.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final @Id String id;
	private final String name;
	private final Address address;

	public Store(String name, Address address) {
		this.name = name;
		this.address = address;
		this.id = null;
	}

	protected Store() {
		this.id = null;
		this.name = null;
		this.address = null;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

}