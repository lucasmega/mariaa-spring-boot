package com.mariaa.app.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariaa.app.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> list() {
		Category cat1 = new Category(1, "Domestica");
		
		List<Category> list = new ArrayList<Category>();
		list.add(cat1);
		
		return list;
	}
}
