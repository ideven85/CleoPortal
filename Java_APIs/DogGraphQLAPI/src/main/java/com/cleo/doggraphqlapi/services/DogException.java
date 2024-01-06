package com.cleo.doggraphqlapi.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dog Not Found")
public class DogException extends RuntimeException {
    public DogException() {
    }
    public DogException(String message) {
        super(message);
    }

}
