package com.delta.storage.server.filestore;

import org.springframework.stereotype.Component;

@Component
public class Metadata {
    String bucket, key;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
