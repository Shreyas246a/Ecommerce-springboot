package com.ecommerce.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.backend.DTO.ProductDTO;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;


public interface ProductService {
	
    ProductDTO addProduct(ProductDTO productdto, List<MultipartFile> image) throws IOException;
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    List<ProductDTO> getProductsByCategory(Long categoryId);
    void deleteProduct(Long id) throws ResourceNotFoundException;
	ProductDTO updateProduct(ProductDTO productdto, List<MultipartFile> newImages, List<Long> imageIdsToDelete) throws IOException;
	
}
