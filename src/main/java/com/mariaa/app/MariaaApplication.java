package com.mariaa.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mariaa.app.domain.Address;
import com.mariaa.app.domain.Category;
import com.mariaa.app.domain.City;
import com.mariaa.app.domain.Client;
import com.mariaa.app.domain.Product;
import com.mariaa.app.domain.State;
import com.mariaa.app.domain.enums.CustomerType;
import com.mariaa.app.repositories.AddressRepository;
import com.mariaa.app.repositories.CategoryRepository;
import com.mariaa.app.repositories.CityRepository;
import com.mariaa.app.repositories.ClientRepository;
import com.mariaa.app.repositories.ProductRepository;
import com.mariaa.app.repositories.StateRepository;

@SpringBootApplication
public class MariaaApplication implements CommandLineRunner {
	
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	

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
		
		State minasGerais = new State(null, "Minas Gerais");
		State saoPaulo = new State(null, "São Paulo");
		
		City diadema = new City(null, "Diadema", saoPaulo);
		City campinas = new City(null, "Campinas", saoPaulo);
		City uberlandia = new City(null, "Uberlândia", minasGerais);
		
		Client mariaDaSilva = new Client(null, "Maria da Silva", "maria@mail.com", "42636730850", CustomerType.PERSON);
		
		Address ruaFlores = new Address(null, "Rua França", 45, "Casa 1", "Jd. Nações", "09941070", mariaDaSilva, diadema);
		Address avenidaMatos = new Address(null, "Avenida Matos", 105, "Sala 800", "Centro", "38777012", mariaDaSilva, campinas);
		
		 // ASSOCIAÇÕES 
		scheduled.getProducts().addAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
		noScheduled.getProducts().addAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
		
		cooker.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		diarist.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		washerwoman.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		cleaningLady.getCategories().addAll(Arrays.asList(scheduled, noScheduled));
		
		minasGerais.getCities().addAll(Arrays.asList(uberlandia));
		saoPaulo.getCities().addAll(Arrays.asList(campinas, uberlandia));
		
		mariaDaSilva.getPhones().addAll(Arrays.asList("958640164", "985146857"));
		
		mariaDaSilva.getAdresses().addAll(Arrays.asList(ruaFlores, avenidaMatos));
		
		 // COMMIT NO BANCO 
		stateRepository.saveAll(Arrays.asList(saoPaulo, minasGerais));
		cityRepository.saveAll(Arrays.asList(diadema, campinas, uberlandia));
		categoryRepository.saveAll(Arrays.asList(scheduled, noScheduled));
		productRepository.saveAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
		clientRepository.saveAll(Arrays.asList(mariaDaSilva));
		addressRepository.saveAll(Arrays.asList(ruaFlores, avenidaMatos));
	}
}
