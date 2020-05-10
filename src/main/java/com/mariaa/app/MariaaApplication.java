package com.mariaa.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mariaa.app.domain.Category;
import com.mariaa.app.domain.Product;
import com.mariaa.app.repositories.CategoryRepository;
import com.mariaa.app.repositories.ProductRepository;

@SpringBootApplication
public class MariaaApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MariaaApplication.class, args);
	}
	
	
	public void run(String... args) throws Exception {
		
		 // INSTANCIAS 
		
		Category scheduled = new Category(null, "Com agendamento");
		Category noScheduled = new Category(null, "Sem agendamento");
		
		Product diarist = new Product(null, "Diarista", 150.00);
		Product cooker = new Product(null, "Cozinheira", 120.00);
		Product washerwoman = new Product(null, "Lavadeira", 120.00);
		Product cleaningLady = new Product(null, "Faxineira", 120.00);
		
		 // ASSOCIAÇÕES 
		
		cooker.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		diarist.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		washerwoman.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		cleaningLady.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		
		scheduled.getProducts().addAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
		noScheduled.getProducts().addAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
		
		 // COMMIT NO BANCO 
		
		categoryRepository.saveAll(Arrays.asList(scheduled, noScheduled));
		productRepository.saveAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
	}
}
