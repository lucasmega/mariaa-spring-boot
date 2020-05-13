package com.mariaa.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mariaa.app.domain.Category;
import com.mariaa.app.repositories.CategoryRepository;
import com.mariaa.app.services.exceptions.DataIntegrityException;
import com.mariaa.app.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category find(Integer id) {
		Optional<Category> object = categoryRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria de código: " + id + " não encontrada, Tipo: " + Category.class.getName()));
	}

	public Category insert(Category object) {
		object.setId(null);
		return categoryRepository.save(object);
	}

	public Category update(Category object) {
		find(object.getId());
		return categoryRepository.save(object);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados!");
		}
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
}
