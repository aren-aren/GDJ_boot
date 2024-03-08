package com.winter.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  **-context.xml 과 같은 역할
 */
@Configuration
@Slf4j
public class FileMapping implements WebMvcConfigurer {
    @Value("${app.url.path}")
    private String urlPath;
    @Value("${app.upload.base}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(urlPath)
                .addResourceLocations(filePath);

        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }
}