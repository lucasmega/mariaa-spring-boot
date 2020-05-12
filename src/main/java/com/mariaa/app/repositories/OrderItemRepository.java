package com.mariaa.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariaa.app.domain.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	
}