package com.example.webksz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Дозволяємо CORS для всіх шляхів, що починаються з /api
                registry.addMapping("/**")
                        // URL фронтенду
                        .allowedOrigins("http://localhost:3000", "http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Дозволені методи
                        .allowedHeaders("*") // Дозволити будь-які заголовки
                        .allowCredentials(true); // Дозволити передачу кук, якщо потрібно
            }
        };
    }
}
