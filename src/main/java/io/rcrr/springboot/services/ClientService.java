package io.rcrr.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.rcrr.springboot.domains.Address;
import io.rcrr.springboot.domains.City;
import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.domains.enums.ClientType;
import io.rcrr.springboot.dto.ClientDTO;
import io.rcrr.springboot.dto.ClientNewDTO;
import io.rcrr.springboot.repositories.AddressRepository;
import io.rcrr.springboot.repositories.CityRepository;
import io.rcrr.springboot.repositories.ClientRepository;
import io.rcrr.springboot.services.exceptions.DataIntegrityException;
import io.rcrr.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Client find(Integer id) {
		
		Optional<Client> obj = clientRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
		
	}
	
	@Transactional
	public Client insert(Client obj) {
		
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		
		return obj;
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
	
	public Client fromDTO(ClientNewDTO objDto) { 
		
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getClientType()));
		City city = cityRepository.findById(objDto.getCityId())
                .orElseThrow(() -> new ObjectNotFoundException("City not found with id: " + objDto.getCityId()));
		Address end = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getCompletement(), objDto.getNeighborhood(), objDto.getPostalCode(), cli, city);
		
		cli.getAddresses().add(end);
		cli.getTelephones().add(objDto.getTelephone1());
		
		if (objDto.getTelephone2()!=null) {
			cli.getTelephones().add(objDto.getTelephone2());
		}
		if (objDto.getTelephone3()!=null) {
			cli.getTelephones().add(objDto.getTelephone3());
		}
		
		return cli;
	}
	
	private void updateData(Client newObj, Client obj) {
		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
