package com.delta.storage.server.service;

import com.delta.storage.server.exceptions.InvalidBucketNameException;
import com.delta.storage.utils.BucketUtils;
import com.delta.storage.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    @Autowired
    StoreService storeService;

    public void createBucket(String bucket) {
        // Do all the validation logic (Of bucket name) here
        if(!BucketUtils.validateBucketName(bucket)) {
            System.out.println("Illegal bucket name");
            throw new InvalidBucketNameException(bucket);
        }

        storeService.createBucket(bucket);
    }
}
