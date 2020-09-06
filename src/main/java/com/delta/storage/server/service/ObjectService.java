package com.delta.storage.server.service;

import org.apache.commons.lang3.RandomStringUtils;
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


    public String initMultipartUpload(String bucket) throws Exception {
        String uploadId = RandomStringUtils.randomAlphanumeric(32);
        storeService.initMultipartUpload(bucket, uploadId);
        return uploadId;
    }
}
