package com.carloscardona.microservices.services.customer.apis;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.microservices.services.customer.model.Address;
import com.carloscardona.microservices.services.customer.model.Customer;
import com.carloscardona.microservices.services.customer.model.Location;

/**
 * REST endpoint for the user functionality.
 * 
 * @author candr
 *
 */
@RestController
@RequestMapping("/")
public class CustomerController {

	private Location location = new Location(1.0, 1.0);
	private Address address = new Address("123", "9002", "Medellin", location);
	private List<Customer> customers = Arrays.asList(new Customer(0L, "Carlos", "Cardona", address),
			new Customer(0L, "Rohit", "Ghatol", address), new Customer(0L, "John", "Snow", address));

	/**
	 * Return all users
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Customer> all() {
		return customers;
	}

	/**
	 * Return user associated with specific user name
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "{userName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Customer userByUserName(@PathVariable("userName") String userName) {
		Customer userDtoToReturn = null;
		for (Customer currentUser : customers) {
			if (currentUser.getFirstname().equalsIgnoreCase(userName)) {
				userDtoToReturn = currentUser;
				break;
			}
		}
		return userDtoToReturn;
	}
}