package com.delta.storage.server.requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomStorageHttpServletRequest extends HttpServletRequestWrapper {
    CustomStorageHttpServletRequest(HttpServletRequest request) {
        super(request);
    }


    String method;
    String bucket;
    String object;

    @Override
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
