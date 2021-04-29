package com.onehoeyman.battle;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS")
                .allowedHeaders("X-Requested-With,Content-Type,Accept,Origin, ");
    }
}
