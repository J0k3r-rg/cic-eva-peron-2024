package com.j0k3r_dev.cic_eva_peron.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfigs implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String corsPattern = getRequiredProperty("CORS_PATTERN");
        String[] corsMethods = getRequiredProperty("CORS_METHODS").split(",");
        String corsOrigins = environment.getProperty("CORS_ORIGINS");

        registry.addMapping(corsPattern)
                .allowedMethods(corsMethods)
                .allowedOrigins(corsOrigins)
                .allowCredentials(true);
    }

    private String getRequiredProperty(String propertyName) {
        String value = environment.getProperty(propertyName);
        if (value == null) {
            throw new IllegalArgumentException("Missing required environment variable: " + propertyName);
        }
        return value;
    }
}
