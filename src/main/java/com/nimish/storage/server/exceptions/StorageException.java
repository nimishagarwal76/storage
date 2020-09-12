package com.nimish.storage.server.exceptions;

public class StorageException extends RuntimeException {
    String code, message, resource;

    public StorageException(String code, String message, String resource) {
        this.code = code;
        this.message = message;
        this.resource = resource;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
