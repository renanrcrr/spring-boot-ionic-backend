package io.rcrr.springboot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.rcrr.springboot.domains.Category;
import io.rcrr.springboot.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = categoryService.search(id); 
		
		return ResponseEntity.ok().body(obj);
	}

}
