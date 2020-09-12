package com.nimish.storage.server.exceptions;

public class NoSuchKeyException extends StorageException{
    final static String CODE = "NoSuchKey";
    final static String MESSAGE = "The specified key does not exist.";

    public NoSuchKeyException(String resource) {
        super(CODE, MESSAGE, resource);
    }
}
