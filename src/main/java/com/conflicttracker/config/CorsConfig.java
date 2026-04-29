package com.conflicttracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${CORS_ALLOWED_ORIGINS:http://localhost:5173}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Intenta leer primero de la variable de entorno (importante en Railway)
        String origins = System.getenv("CORS_ALLOWED_ORIGINS");
        if (origins == null || origins.isEmpty()) {
            origins = allowedOrigins;
        }
        
        System.out.println("CORS_ALLOWED_ORIGINS configured as: " + origins);
        
        // Parsear múltiples orígenes si están separados por comas
        String[] originArray = origins.split(",");
        for (int i = 0; i < originArray.length; i++) {
            originArray[i] = originArray[i].trim();
        }
        
        registry.addMapping("/**")
                .allowedOrigins(originArray)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}