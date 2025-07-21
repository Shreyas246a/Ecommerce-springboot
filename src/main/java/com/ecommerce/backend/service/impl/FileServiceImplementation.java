package com.ecommerce.backend.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.backend.service.FileService;

@Service
public class FileServiceImplementation implements FileService{

    private final CategoryImplementation categoryImplementation;
	@Autowired
	private Cloudinary cloudinary;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    
    
    FileServiceImplementation(CategoryImplementation categoryImplementation) {
        this.categoryImplementation = categoryImplementation;
    }
	
	public String UploadImageCloudinary(Long id,MultipartFile image) throws IOException {
		
		long timestamp = System.currentTimeMillis();

		Map<String, Object> paramsToSign = ObjectUtils.asMap(
		    "folder", "Ecommerce/products/" + id,
		    "upload_preset","ecommerce_preset"
		);

		// Signature must match only the signed params
//		String signature = cloudinary.apiSignRequest(paramsToSign, apiSecret);

		Map<String, Object> options = new HashMap<>();
		options.putAll(paramsToSign);
//		options.put("signature", signature);
//		options.put("api_key", apiKey);

		Map uploadRes = cloudinary.uploader().upload(image.getBytes(), options);

		return uploadRes.get("secure_url").toString();
	}
}
