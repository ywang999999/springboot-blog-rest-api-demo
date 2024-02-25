package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

    //erbt die benutzerdefinierte Ausnahme BlogAPIException von
    // RuntimeException und enthält einen HttpStatus-Parameter in ihrem Konstruktor.
    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
