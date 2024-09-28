package io.rcrr.springboot.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.rcrr.springboot.domains.Client;
import io.rcrr.springboot.dto.ClientDTO;
import io.rcrr.springboot.services.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		
		Client obj = clientService.find(id); 
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id) {
		
		Client obj = clientService.fromDTO(objDto);
		obj.setId(id);
		obj = clientService.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		clientService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		
		List<Client> list = clientService.findAll();
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList()); 
		
		return ResponseEntity.ok().body(listDto); 
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			// Adicionando parametros opcionais pelo annotations
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Client> list = clientService.findPage(page, linesPerPage, orderBy, direction);
		// Como o Page é Java8 compliance, nao preciso usar o stream() e nem o collect()
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj)); 
		
		return ResponseEntity.ok().body(listDto); 
	}
}
