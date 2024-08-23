package io.rcrr.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rcrr.springboot.domains.Order;
import io.rcrr.springboot.repositories.OrderRepository;
import io.rcrr.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order find(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Order.class.getName()));
		
	}
}
