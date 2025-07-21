package com.ecommerce.backend.service;

import java.util.List;

import com.ecommerce.backend.exceptions.NotAllowedException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.model.Category;

public interface CategoryService {
	
	Category getCategoryById(long id);
	List<Category> getAllCategory();
	void RemoveCategory(long id) throws NotAllowedException;
	Category addCategory(String name, String desc);
	Category updateCategory(Category c) throws ResourceNotFoundException;
}
