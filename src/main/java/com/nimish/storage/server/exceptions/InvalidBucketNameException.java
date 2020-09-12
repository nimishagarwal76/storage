package com.nimish.storage.server.exceptions;

public class InvalidBucketNameException extends StorageException {
    final static String CODE = "InvalidBucketName";
    final static String MESSAGE = "The specified bucket is not valid.";
    public InvalidBucketNameException(String resource) {
        super(CODE, MESSAGE, resource);
    }
}
