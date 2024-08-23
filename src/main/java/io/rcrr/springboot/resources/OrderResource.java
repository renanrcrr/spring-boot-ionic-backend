package io.rcrr.springboot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.rcrr.springboot.domains.Order;
import io.rcrr.springboot.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order obj = orderService.find(id); 
		
		return ResponseEntity.ok().body(obj);
	}	
}
