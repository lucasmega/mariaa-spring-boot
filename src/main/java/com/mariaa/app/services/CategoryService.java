package com.mariaa.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariaa.app.domain.Category;
import com.mariaa.app.repositories.CategoryRepository;
import com.mariaa.app.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category find(Integer id) {
		Optional<Category> object = categoryRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Categoria de código: " + id + " não encontrada, Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category object) {
		object.setId(null);
		return categoryRepository.save(object);
	}
}
