package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.exceptions.NotAllowedException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategorytController {
	@Autowired
	CategoryService cservice;
	@GetMapping("/getCategories")
	public ResponseEntity<List<Category>> getAll(){
		List<Category> categories = cservice.getAllCategory();
		return ResponseEntity.ok(categories);
		
	}
	@DeleteMapping("/DeleteCategory")
	public ResponseEntity deletecat(@RequestParam("id") long id) throws NotAllowedException {
		cservice.RemoveCategory(id);
		return ResponseEntity.ok("Category Deleted");	
	}
	@PostMapping("/AddCategory")
	public ResponseEntity<Category>addCat(@RequestPart("category") Category c){
		return 	ResponseEntity.ok(cservice.addCategory(c.getName(),c.getDescription()));			
	}
	
	@PutMapping("/UpdateCategory")
	public ResponseEntity<Category>updateCat(@RequestPart("category")Category c) throws ResourceNotFoundException{
	
		return ResponseEntity.ok(cservice.updateCategory(c));
	}
	
	
}
