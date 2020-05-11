package com.mariaa.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariaa.app.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
}
