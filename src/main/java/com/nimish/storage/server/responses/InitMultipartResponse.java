package com.nimish.storage.server.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;

@JacksonXmlRootElement(localName = "InitiateMultipartUploadResult", namespace = "http://s3.amazonaws.com/doc/2006-03-01/")
public class InitMultipartResponse {
    @JacksonXmlProperty(localName = "Bucket")
    String bucket;
    @JacksonXmlProperty(localName = "Key")
    String key;
    @JacksonXmlProperty(localName = "UploadId")
    String uploadId;

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

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public InitMultipartResponse(String bucket, String key, String uploadId) {
        this.bucket = bucket;
        this.key = key;
        this.uploadId = uploadId;
    }
}
