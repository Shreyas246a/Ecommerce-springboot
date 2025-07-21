package com.ecommerce.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.exceptions.NotAllowedException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.CategoryRepo;
import com.ecommerce.backend.repository.ProductRepo;
import com.ecommerce.backend.service.CategoryService;

@Service
public class CategoryImplementation implements CategoryService{
	@Autowired
	CategoryRepo crepo;
	@Autowired
	ProductRepo prepo;
	@Override
	public Category getCategoryById(long id) {
		return crepo.findCategoryById(id); 
	}
	@Override
	public List<Category> getAllCategory() {
		return crepo.findAll();
	}
	@Override
	public Category addCategory(String name,String desc) {
		Category c = new Category();
		c.setName(name);
		c.setDescription(desc);
		return crepo.save(c);
	}
	@Override
	public void RemoveCategory(long id) throws NotAllowedException {
		
	    Category categoryToDelete = crepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	    
	    if (categoryToDelete.getName().equalsIgnoreCase("No Category")) {
	        throw new NotAllowedException("Default category cannot be deleted");
	    }
	    
	    
	    Category defaultCat = getDefaultCategory();
		
		
		
		List<Product> products = prepo.findByCategoryId(id);
		for(Product product : products) {
			product.setCategory(defaultCat);
		}
		prepo.saveAll(products);
		crepo.delete(categoryToDelete);
		
	}
	
	private Category getDefaultCategory() {
	    return crepo.findByName("No Category").orElseGet(() -> {
	            Category defaultCategory = new Category();
	            defaultCategory.setName("No Category");
	            defaultCategory.setDescription("These products have not been assigned any category by the seller");
	            return crepo.save(defaultCategory);
	        });
	}
	@Override
	public Category updateCategory(Category c) throws ResourceNotFoundException {
		
	    Category categoryUpdate = crepo.findById(c.getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	
		categoryUpdate.setName(c.getName());
		categoryUpdate.setName(c.getName());
		return crepo.save(categoryUpdate);
		
	}


}
