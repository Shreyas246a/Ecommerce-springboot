package com.ecommerce.backend.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String UploadImageCloudinary(Long id, MultipartFile image) throws IOException;
	
}
