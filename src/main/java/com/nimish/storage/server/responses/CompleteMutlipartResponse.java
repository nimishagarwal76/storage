package com.nimish.storage.server.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "CompleteMultipartUploadResult", namespace = "http://s3.amazonaws.com/doc/2006-03-01/")
public class CompleteMutlipartResponse {
    @JacksonXmlProperty(localName = "Location")
    String location;
    @JacksonXmlProperty(localName = "Bucket")
    String bucket;
    @JacksonXmlProperty(localName = "Key")
    String key;
    @JacksonXmlProperty(localName = "ETag")
    String etag;

    public CompleteMutlipartResponse(String location, String bucket, String key, String etag) {
        this.location = location;
        this.bucket = bucket;
        this.key = key;
        this.etag = etag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
