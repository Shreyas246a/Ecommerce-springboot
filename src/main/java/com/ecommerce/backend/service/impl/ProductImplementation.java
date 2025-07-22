package com.ecommerce.backend.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.backend.DTO.ProductDTO;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.ProductImage;
import com.ecommerce.backend.repository.ProductRepo;
import com.ecommerce.backend.service.FileService;
import com.ecommerce.backend.service.ProductService;

@Service
public class ProductImplementation implements ProductService{

    private final CategoryImplementation categoryImplementation;
	@Autowired
	ProductRepo productRepo;
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private FileService fileService;

    ProductImplementation(CategoryImplementation categoryImplementation) {
        this.categoryImplementation = categoryImplementation;
    }
	@Override
	public ProductDTO addProduct(ProductDTO productdto,List<MultipartFile>images) throws IOException {
		
		Optional<Product> exist= productRepo.findProductByName(productdto.getName().toLowerCase().trim());
		if(exist!=null) {
			throw new ResourceNotFoundException("Product with same name already Exists");
		}
		
		Category c = new Category();
		c.setId(productdto.getCategoryId());
		c.setName(productdto.getCategoryName());
		
		Product p = new Product();
		p=DtoToEntity(productdto);
		p.setCategory(c);
		p.setCreateddate(new Date());
		
		List<ProductImage> newImages = new ArrayList<>();
		if(images!=null && !images.isEmpty()) {
			for(MultipartFile file : images) {
				System.out.println(file.getOriginalFilename());
				String url = fileService.UploadImageCloudinary(p.getId(), file);
				ProductImage img = new ProductImage();
				img.setUrl(url);
				img.setProduct(p);
				newImages.add(img);
			}
		}
		p.setImages(newImages);
		return EntityToDto(productRepo.save(p));
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepo.findAll().
				stream()
				.map(this::EntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long id) {
		return EntityToDto(productRepo.findProductById(id)); 
	}

	@Override
	public List<ProductDTO> getProductsByCategory(Long categoryId) {
			return productRepo.findByCategoryId(categoryId).stream()
					.map(this::EntityToDto)
					.collect(Collectors.toList());
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productdto,List<MultipartFile> newImages,List<Long> imageIdsToRetain) throws IOException {
		Product existing = productRepo.findProductById(productdto.getId());
	    
		Category category = new Category();
	    category.setId(productdto.getCategoryId());
	    category.setName(productdto.getCategoryName());
	    existing.setCategory(category);
		
	    existing.setName(productdto.getName());
	    existing.setDescription(productdto.getDescription());
	    existing.setPrice(productdto.getPrice());
	    
	    List<ProductImage> existingImage = existing.getImages();
	    List<ProductImage> retainedImg = new ArrayList<>();
	    existing.getImages().clear();
	    for(ProductImage img : existingImage) {
	    	if(imageIdsToRetain!=null && imageIdsToRetain.contains(img.getId())) {
	    		retainedImg.add(img);
	    	}
	    }
	   
	    if (newImages != null && !newImages.isEmpty()) {
	        for (MultipartFile file : newImages) {
	            String url = fileService.UploadImageCloudinary(productdto.getId(), file); // your method
	            ProductImage pi = new ProductImage();
	            pi.setUrl(url);
	            pi.setProduct(existing);
	            retainedImg.add(pi);
	        }
	    }

	    existing.getImages().addAll(retainedImg);
		return  EntityToDto(productRepo.save(existing));  
	}

	@Override
	public void deleteProduct(Long id) throws ResourceNotFoundException {
	    Product product = productRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
		productRepo.delete(product);
	}
	
	private ProductDTO EntityToDto(Product product) {
		ProductDTO dto = modelMapper.map(product,ProductDTO.class);
		return dto;
	}
	private Product DtoToEntity(ProductDTO productdto) {
		Product p = modelMapper.map(productdto, Product.class);
		return p;
	}

}
