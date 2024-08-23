package io.rcrr.springboot.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.rcrr.springboot.domains.Category;
import io.rcrr.springboot.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	@Autowired
	private CategoryService categoryService;
	
//	@GetMapping(value="/{id}")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category obj = categoryService.find(id); 
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	// ResquestBody converts JSON to obj Java automatically
	public ResponseEntity<Void> insert(@RequestBody Category obj) {
		obj = categoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = categoryService.update(obj);
		
		return ResponseEntity.noContent().build();
	}
}
