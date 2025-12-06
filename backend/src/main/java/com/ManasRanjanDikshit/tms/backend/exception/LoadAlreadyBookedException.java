package com.ManasRanjanDikshit.tms.backend.exception;

public class LoadAlreadyBookedException extends RuntimeException {
    public LoadAlreadyBookedException(String message) {
        super(message);
    }
}
