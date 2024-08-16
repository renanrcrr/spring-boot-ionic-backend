package io.rcrr.springboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.rcrr.springboot.domains.Address;
import io.rcrr.springboot.domains.Category;
import io.rcrr.springboot.domains.City;
import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.domains.ItemOrder;
import io.rcrr.springboot.domains.Order;
import io.rcrr.springboot.domains.Payment;
import io.rcrr.springboot.domains.PaymentWithBankSlip;
import io.rcrr.springboot.domains.PaymentWithCard;
import io.rcrr.springboot.domains.Product;
import io.rcrr.springboot.domains.State;
import io.rcrr.springboot.domains.enums.ClientType;
import io.rcrr.springboot.domains.enums.StatePayment;
import io.rcrr.springboot.repositories.AddressRepository;
import io.rcrr.springboot.repositories.CategoryRepository;
import io.rcrr.springboot.repositories.CityRepository;
import io.rcrr.springboot.repositories.ClientRepository;
import io.rcrr.springboot.repositories.ItemOrderRepository;
import io.rcrr.springboot.repositories.OrderRepository;
import io.rcrr.springboot.repositories.PaymentRepository;
import io.rcrr.springboot.repositories.ProductRepository;
import io.rcrr.springboot.repositories.StateRepository;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemOrderRepository itemOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// Creates objects in runtime to be save in the database
	@Override
	public void run(String... args) throws Exception {
		System.out.println("---------------- Category and Product ----------------");
		
		Category cat1 = new Category(null, "Technology");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		System.out.println("--------------- State and City -----------------------");
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "Sao Paulo");
		
		City c1 = new City(null, "Urbelândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		System.out.println("------------ Client, Address and Telephone ------------");
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.INDIVIDUAL);
		cli1.getTelephones().addAll(Arrays.asList("27363323", "93837393"));
		
		Address a1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(a1, a2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
		
		System.out.println("------------ Order, Payment, PaymentWithBankSlip, PaymentWithCard and StatePayment ------------");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("15/08/2024 10:32"), cli1, a1);
		Order ord2 = new Order(null, sdf.parse("15/08/2024 19:42"), cli1, a2);
		
		Payment pay1 = new PaymentWithCard(null, StatePayment.PAID, ord1, 6);
		ord1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithBankSlip(null, StatePayment.PENDING, ord2, sdf.parse("20/04/2024 00:00"), null); // Payment date is null because it was not paid yet.
		ord2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		System.out.println("------------ ItemOrder ------------");
		
		ItemOrder io1 = new ItemOrder(ord1, p1, 0.00, 1, 2000.00);
		ItemOrder io2 = new ItemOrder(ord1, p3, 0.00, 2, 80.00);
		ItemOrder io3 = new ItemOrder(ord2, p2, 100.00, 1, 800.00);
		
		ord1.getItems().addAll(Arrays.asList(io1, io2));
		ord2.getItems().addAll(Arrays.asList(io3));
		
		p1.getItems().addAll(Arrays.asList(io1));
		p2.getItems().addAll(Arrays.asList(io3));
		p3.getItems().addAll(Arrays.asList(io2));
		
		itemOrderRepository.saveAll(Arrays.asList(io1, io2, io3));
		
	}
}
