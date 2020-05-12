package com.mariaa.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariaa.app.domain.Order;
import com.mariaa.app.repositories.OrderRepository;
import com.mariaa.app.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order find(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido de código " + id + " não encontrado, Tipo: " + Order.class.getName()));
	}
}
