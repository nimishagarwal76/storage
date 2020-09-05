package com.delta.storage.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectService {
    @Autowired
    StoreService storeService;

    public void storeObject(String bucket, String key, byte[] content) throws Exception {
        // Validations done here
        storeService.storeObject(bucket, key, content);
    }
}
