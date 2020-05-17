package com.mariaa.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mariaa.app.domain.Client;

public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O nome da pessoal deve ter entre 5 e 80 caracteres!")
	private String name;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido!")
	private String email;
	
	public ClientDTO() { }
	
	public ClientDTO(Client object) {
		id = object.getId();
		name = object.getName();
		email = object.getEmail();
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
