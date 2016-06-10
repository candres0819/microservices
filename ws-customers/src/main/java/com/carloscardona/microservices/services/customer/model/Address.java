package com.carloscardona.microservices.services.customer.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String street;
	private String zipCode;
	private String city;
	private Location location;

	protected Address() {
		this.street = null;
		this.zipCode = null;
		this.city = null;
		this.location = null;
	}

	public Address(String street, String zipCode, String city, Location location) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.location = location;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
}