package com.mariaa.app.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariaa.app.domain.Client;
import com.mariaa.app.services.ClientService;

@RestController
@RequestMapping(value="customers")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Client obj = clientService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
