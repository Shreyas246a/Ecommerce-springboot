package com.ecommerce.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.model.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

Category findCategoryById(long id);

Optional<Category> findByName(String string);
}
