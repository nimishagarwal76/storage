package com.delta.storage.server.exceptions;

public class BucketAlreadyExistsException extends StorageException {
    final static String CODE = "BucketAlreadyExists";
    final static String MESSAGE = "The requested bucket name is not available. The bucket namespace is shared by all users of the system. Please select a different name and try again.";
    public BucketAlreadyExistsException(String resource)
    {
        super(CODE, MESSAGE, resource);
    }
}
