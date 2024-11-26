package com.phonestoreweb.phonestore.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dwwlenn7f");
        config.put("api_key", "866162237736941");
        config.put("api_secret", "dWrtUTA9ik7S4Cjtba2ktczLYhA");
        return new Cloudinary(config);
    }
}
