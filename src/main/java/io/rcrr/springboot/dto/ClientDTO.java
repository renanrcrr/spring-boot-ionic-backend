package io.rcrr.springboot.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import io.rcrr.springboot.domains.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Mandatory field")
	@Length(min=5, max=120, message="Size needs to be between 5 and 120 characters.")
	private String name;
	
	@NotEmpty(message="Mandatory field")
	@Email(message="Invalid email")
	private String email;
	
	public ClientDTO() {}
	
	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
