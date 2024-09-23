package io.rcrr.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.rcrr.springboot.domains.Category;
import io.rcrr.springboot.repositories.CategoryRepository;
import io.rcrr.springboot.services.exceptions.DataIntegrityException;
import io.rcrr.springboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	// Using that annotation Spring creates a dependency via dependency injection or control inversion mechanism
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category find(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		// Id null for new obj, otherwise will be a modification
		obj.setId(null);
		return categoryRepository.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return categoryRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			categoryRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible delete a category with products!");
		}
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return categoryRepository.findAll(pageRequest);
	}
}
