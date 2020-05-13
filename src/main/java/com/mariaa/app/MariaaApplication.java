package com.mariaa.app;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mariaa.app.domain.Address;
import com.mariaa.app.domain.Category;
import com.mariaa.app.domain.City;
import com.mariaa.app.domain.Client;
import com.mariaa.app.domain.Order;
import com.mariaa.app.domain.OrderItem;
import com.mariaa.app.domain.Payment;
import com.mariaa.app.domain.PaymentByBillet;
import com.mariaa.app.domain.PaymentByCard;
import com.mariaa.app.domain.Product;
import com.mariaa.app.domain.State;
import com.mariaa.app.domain.enums.CustomerType;
import com.mariaa.app.domain.enums.PaymentStatus;
import com.mariaa.app.repositories.AddressRepository;
import com.mariaa.app.repositories.CategoryRepository;
import com.mariaa.app.repositories.CityRepository;
import com.mariaa.app.repositories.ClientRepository;
import com.mariaa.app.repositories.OrderItemRepository;
import com.mariaa.app.repositories.OrderRepository;
import com.mariaa.app.repositories.PaymentRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	private static final Integer NUMBER_OF_INSTALLMENT = 0;
	private static final Double DISCOUNT = 0.00;
	private static final Integer AMOUNT = 1;
	

	public static void main(String[] args) {
		SpringApplication.run(MariaaApplication.class, args);
	}
	
	
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		 // INSTANCIAS 
		Category scheduled = new Category(null, "Com agendamento");
		Category noScheduled = new Category(null, "Sem agendamento");
		
		Category cat1 = new Category(null, "NOME CATEGORIA");
		Category cat2 = new Category(null, "NOME CATEGORIA");
		Category cat3 = new Category(null, "NOME CATEGORIA");
		Category cat4 = new Category(null, "NOME CATEGORIA");
		Category cat5 = new Category(null, "NOME CATEGORIA");
		Category cat6 = new Category(null, "NOME CATEGORIA");
		Category cat7 = new Category(null, "NOME CATEGORIA");
		Category cat8 = new Category(null, "NOME CATEGORIA");
		
		
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
		
		Address ruaFranca = new Address(null, "Rua França", 45, "Casa 1", "Jd. Nações", "09941070", mariaDaSilva, diadema);
		Address avenidaMatos = new Address(null, "Avenida Matos", 105, "Sala 800", "Centro", "38777012", mariaDaSilva, campinas);
		
		Order orderOne = new Order(null, sdt.parse("12/05/2020 15:43"), mariaDaSilva, ruaFranca);
		Order orderTwo = new Order(null, sdt.parse("06/04/2020 11:51"), mariaDaSilva, avenidaMatos);
		
		Payment paymentByCard = new PaymentByCard(null, PaymentStatus.SETTLED, orderOne, NUMBER_OF_INSTALLMENT);
		Payment paymentByBillet = new PaymentByBillet(null, PaymentStatus.PENDING, orderTwo, sdt.parse("12/05/2020 23:59"), null);
		
		OrderItem orderdiarist = new OrderItem(orderOne, diarist, DISCOUNT, AMOUNT, diarist.getPrice());
		OrderItem orderCooker = new OrderItem(orderTwo, cooker, DISCOUNT, AMOUNT, cooker.getPrice());
		OrderItem orderCleaningLady = new OrderItem(orderTwo, cleaningLady, DISCOUNT, AMOUNT, cleaningLady.getPrice());
		
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
		
		mariaDaSilva.getAdresses().addAll(Arrays.asList(ruaFranca, avenidaMatos));
		
		orderOne.setPayment(paymentByCard);
		orderTwo.setPayment(paymentByBillet);
		
		mariaDaSilva.getOrder().addAll(Arrays.asList(orderOne, orderTwo));
		
		orderOne.getOrderItem().addAll(Arrays.asList(orderdiarist, orderCooker));
		orderOne.getOrderItem().addAll(Arrays.asList(orderCleaningLady));
		
		diarist.getOrderItem().addAll(Arrays.asList(orderdiarist));
		cooker.getOrderItem().addAll(Arrays.asList(orderCooker));
		cleaningLady.getOrderItem().addAll(Arrays.asList(orderCleaningLady));
		
		 // COMMIT NO BANCO 
		stateRepository.saveAll(Arrays.asList(saoPaulo, minasGerais));
		cityRepository.saveAll(Arrays.asList(diadema, campinas, uberlandia));
		categoryRepository.saveAll(Arrays.asList(scheduled, noScheduled, cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		productRepository.saveAll(Arrays.asList(diarist, cooker, washerwoman, cleaningLady));
		clientRepository.saveAll(Arrays.asList(mariaDaSilva));
		addressRepository.saveAll(Arrays.asList(ruaFranca, avenidaMatos));
		orderRepository.saveAll(Arrays.asList(orderOne, orderTwo));
		paymentRepository.saveAll(Arrays.asList(paymentByCard, paymentByBillet));
		orderItemRepository.saveAll(Arrays.asList(orderdiarist, orderCooker, orderCleaningLady));
	}
}
