package com.delta.storage.server.service;

import com.delta.storage.server.exceptions.InvalidBucketNameException;
import com.delta.storage.utils.BucketUtils;
import com.delta.storage.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    @Autowired
    StoreService storeService;

    private static final Logger logger = LoggerFactory.getLogger(BucketService.class);

    public void createBucket(String bucket) throws Exception {
        // Do all the validation logic (Of bucket name) here
        if(!BucketUtils.validateBucketName(bucket)) {
            logger.error("Illegal bucket name");
            throw new InvalidBucketNameException(bucket);
        }

        storeService.createBucket(bucket);
    }
}
