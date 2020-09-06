package com.delta.storage.server.service;

import com.delta.storage.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StoreService {
    @Value("${app.upload.dir:${user.home}/storage}")
    String root;

    final static String CONTENT_PREFIX = ".content";
    final static String METADATA_DIR = ".object_metadata";

    final static String MULTIPART_DIR = ".multipart";

    public StoreService() {

    }

    public void createBucket(String bucket) throws Exception {
        FileUtils.mkdirs(Paths.get(root, bucket));
    }

    public void storeObject(String bucket, String key, byte[] content) throws Exception {
        FileUtils.mkdir(Paths.get(root, bucket, key));
        FileUtils.writeBytes(Paths.get(root, bucket, key, key + ".content"), content);
    }


    public Path getMultipartUploadDir(String bucket, String uploadId) {
        return Paths.get(root, bucket, MULTIPART_DIR, uploadId);
    }

    public void initMultipartUpload(String bucket, String uploadId) throws Exception {
        Path uploadLocation = getMultipartUploadDir(bucket, uploadId);
        FileUtils.mkdirs(uploadLocation);
    }
}
