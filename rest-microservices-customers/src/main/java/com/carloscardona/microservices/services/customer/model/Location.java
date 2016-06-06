package com.carloscardona.microservices.services.customer.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double latitude;
	private double longitude;

	protected Location() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
}