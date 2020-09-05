package com.delta.storage.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    @Autowired
    StoreService storeService;

    public void createBucket(String bucket) throws Exception{
        // Do all the validation logic (Of bucket name) here
        storeService.createBucket(bucket);
    }
}
