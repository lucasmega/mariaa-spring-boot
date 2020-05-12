package com.mariaa.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariaa.app.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
}
