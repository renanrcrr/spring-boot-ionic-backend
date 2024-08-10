package io.rcrr.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.repositories.ClientRepository;
import io.rcrr.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	public Client search(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
		
	}
}
