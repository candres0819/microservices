package com.carloscardona.microservices.services.store.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.microservices.services.store.dao.StoreRepository;
import com.carloscardona.microservices.services.store.model.Store;

@RestController
@RequestMapping("/")
public class StoreController {

	@Autowired
	StoreRepository repository;

	@RequestMapping("/simple/stores")
	@ResponseBody
	List<Store> getStores() {
		Page<Store> all = repository.findAll(new PageRequest(0, 10));
		return all.getContent();
	}
}
