package com.ecommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

		List<Product>findByCategoryId(Long categoryId);

		Product findProductById(Long id);
		

}
