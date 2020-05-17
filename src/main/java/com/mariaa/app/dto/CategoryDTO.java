package com.mariaa.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mariaa.app.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Nome da categoria deve ser preenchido")
	@Length(min = 5, max = 80, message = "O campo categoria de ter entre 5 e 80 caracteres")
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Category object) {
		id = object.getId();
		name = object.getName();
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
