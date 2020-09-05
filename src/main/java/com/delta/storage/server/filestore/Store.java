package com.delta.storage.server.filestore;

import com.delta.storage.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class Store {
    @Value("${app.upload.dir:${user.home}/storage}")
    String root;

    final static String METADATA_DIR = ".object_metadata";

    public Store() {

    }

    public Store(String root) {
        this.root = root;
    }

    public void createBucket(String bucket) throws Exception{
        FileUtils.mkdirs(Paths.get(root, bucket));
    }

    public void storeObject(String bucket, String key, byte[] content) throws Exception {
        FileUtils.writeBytes(Paths.get(root, bucket, key), content);
    }

}
