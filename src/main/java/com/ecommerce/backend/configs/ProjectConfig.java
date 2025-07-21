package com.ecommerce.backend.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class ProjectConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    
    @Value("${cloudinary.api_secret}")
    private String apiSecret;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    
    @Bean
    public Cloudinary cloudinary() {

    	return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
    			));
    	
    }
    
}
