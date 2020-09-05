package com.delta.storage.server.config;

import com.delta.storage.server.interceptor.StorageServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StorageServiceInterceptorConfig implements WebMvcConfigurer{
    @Autowired
    private StorageServiceInterceptor storageServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(storageServiceInterceptor);
    }
}