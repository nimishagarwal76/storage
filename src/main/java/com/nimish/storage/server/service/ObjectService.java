package com.nimish.storage.server.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class ObjectService {
    @Autowired
    StoreService storeService;

    private static final Logger logger = LoggerFactory.getLogger(ObjectService.class);

    public void storeObject(String bucket, String key, byte[] content) throws Exception {
        // Validations done here
        storeService.storeObject(bucket, key, content);
    }


    public String initMultipartUpload(String bucket) throws Exception {
        String uploadId = RandomStringUtils.randomAlphanumeric(32);
        storeService.initMultipartUpload(bucket, uploadId);
        return uploadId;
    }

    public String storePart(String bucket, String uploadId, Integer partNumber, byte[] content) throws Exception {
        return storeService.storePart(bucket, uploadId, partNumber, content);
    }

    public String completeMultipart(String bucket, String key, String uploadId) throws Exception {
        return storeService.completMultipartUpload(bucket, key, uploadId);
    }

    public File getObjectFile(String bucket, String key) {
        return storeService.getObjectFile(bucket, key);
    }
}
