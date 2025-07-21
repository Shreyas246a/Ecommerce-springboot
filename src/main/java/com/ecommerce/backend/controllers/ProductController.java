package com.ecommerce.backend.controllers;

import java.io.IOException;

import java.util.List;

import com.ecommerce.backend.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.backend.DTO.ProductDTO;
import com.ecommerce.backend.exceptions.NotAllowedException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private ProductService Productservice;
	
	public ModelMapper getModelMapper() {
		return modelMapper;
	}
	
    ProductController(ModelMapper modelMapper) {
        this.setModelMapper(modelMapper);
    }
	@GetMapping("/getAll")
	public List<ProductDTO> getAllProducts(){
		return Productservice.getAllProducts();
	}
	@GetMapping("/test")
	public List<ProductDTO> test(){
		throw new NotAllowedException("Not Allowed");
	}
	@PostMapping(path = "/admin/addProduct",consumes =org.springframework.http.MediaType.ALL_VALUE)
	public ResponseEntity<ProductDTO>addProduct(@RequestPart("productdto") ProductDTO productdto,@RequestPart("image") List<MultipartFile> image) throws IOException {
		System.out.println(image.toString());
		ProductDTO savedProduct = Productservice.addProduct(productdto,image);
		return ResponseEntity.ok(savedProduct);
	}
	
	@PutMapping("/admin/updateProduct")
	public ResponseEntity<ProductDTO>updateProduct(@RequestPart("productdto")ProductDTO productdto,@RequestPart(name = "image",required = false) List<MultipartFile> image,@RequestPart(name = "imageId", required = false)List<Long> imagesToRetain) throws IOException{		
		ProductDTO pto = Productservice.updateProduct(productdto,image,imagesToRetain);
		return ResponseEntity.ok(pto);
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<ProductDTO>getProduct(@RequestParam("id")long id){
		ProductDTO pdto= Productservice.getProductById(id);
		if(pdto==null) {
			return ResponseEntity.ofNullable(null);
		}
		return ResponseEntity.ok(pdto);
	}
	
	@GetMapping("/getProductByCat")
	public ResponseEntity<List<ProductDTO>> getProductByCat(@RequestParam("id") long id){
		List<ProductDTO> pdto = Productservice.getProductsByCategory(id);
		
		return ResponseEntity.ok(pdto);
	
	}
	
	@DeleteMapping("/admin/DeleteProduct")
	public ResponseEntity deleteProduct(@RequestParam("id")long id) throws ResourceNotFoundException {
		Productservice.deleteProduct(id);
		return ResponseEntity.ok("Product deleted");
	}
	
	
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
}
