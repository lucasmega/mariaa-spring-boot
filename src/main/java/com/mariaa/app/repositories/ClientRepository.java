package com.mariaa.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariaa.app.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
}
