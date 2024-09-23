package io.rcrr.springboot.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.rcrr.springboot.domains.Category;
import io.rcrr.springboot.dto.CategoryDTO;
import io.rcrr.springboot.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	@Autowired
	private CategoryService categoryService;
	
 	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category obj = categoryService.find(id); 
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	// ResquestBody converts JSON to obj Java automatically
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto) {
		Category obj = categoryService.fromDTO(objDto); 
		obj = categoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDto, @PathVariable Integer id) {
		Category obj = categoryService.fromDTO(objDto);
		obj.setId(id);
		obj = categoryService.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = categoryService.findAll();
		List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList()); 
		
		return ResponseEntity.ok().body(listDto); 
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			// Adicionando parametros opcionais pelo annotations
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
		// Como o Page é Java8 compliance, nao preciso usar o stream() e nem o collect()
		Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj)); 
		
		return ResponseEntity.ok().body(listDto); 
	}
}
