package com.mariaa.app.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariaa.app.domain.Order;
import com.mariaa.app.services.OrderService;

@RestController
@RequestMapping(value="/pedidos")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order obj = orderService.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
