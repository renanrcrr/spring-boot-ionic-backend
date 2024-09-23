package io.rcrr.springboot.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import io.rcrr.springboot.domains.Category;
import jakarta.validation.constraints.NotEmpty;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Mandatory field")
	@Length(min=5, max=80, message="Size needs to be between 5 and 80 characters")
	private String name;
	
	public CategoryDTO() {}

	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getName();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
