package io.rcrr.springboot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Client obj = clientService.search(id); 
		
		return ResponseEntity.ok().body(obj);
	}	
}
