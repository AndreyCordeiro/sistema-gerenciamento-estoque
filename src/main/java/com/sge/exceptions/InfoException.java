package com.sge.exceptions;

import org.springframework.http.HttpStatus;

public class InfoException extends Exception {
    private HttpStatus status;

    public InfoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public InfoException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
