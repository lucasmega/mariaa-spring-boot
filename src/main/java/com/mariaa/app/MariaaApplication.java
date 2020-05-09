package com.mariaa.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mariaa.app.domain.Category;
import com.mariaa.app.repositories.CategoryRepository;

@SpringBootApplication
public class MariaaApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(MariaaApplication.class, args);
	}
	
	
	public void run(String... args) throws Exception {
		Category domestic = new Category(null, "Domestica");
		
		categoryRepository.saveAll(Arrays.asList(domestic));
	}
}
