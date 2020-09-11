package com.delta.storage.server.exceptions;

public class InternalErrorException extends StorageException {
    final static String CODE = "InternalError";
    final static String MESSAGE = "We encountered an internal error. Please try again.";

    public InternalErrorException() {
        super(CODE, MESSAGE, "");
    }
}
