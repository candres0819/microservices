package com.carloscardona.microservices.services.customer;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.carloscardona.microservices.services.customer.dao.CustomerRepository;
import com.carloscardona.microservices.services.customer.model.Address;
import com.carloscardona.microservices.services.customer.model.Customer;
import com.carloscardona.microservices.services.customer.model.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerApp.class)
public class CustomerRepositoryIntegrationTest {

	@Autowired
	CustomerRepository repository;

	@Test
	public void instance() {
		Assert.isInstanceOf(CustomerRepository.class, repository);
	}

	@Test
	public void testname() {
		Customer customer = new Customer();
		customer.setFirstname("Dave");
		customer.setLastname("Matthews");
		customer.setAddress(new Address("street", "zipCode", "city", new Location(55.349451, -131.673817)));
		customer = repository.save(customer);
		assertThat(repository.findOne(customer.getId()), isA(Customer.class));
	}
}
