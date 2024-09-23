package io.rcrr.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.dto.ClientDTO;
import io.rcrr.springboot.repositories.ClientRepository;
import io.rcrr.springboot.services.exceptions.DataIntegrityException;
import io.rcrr.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	public Client find(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
		
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete because there are related entities!");
		}
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Client newObj, Client obj) {
		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
