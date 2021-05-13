package com.thescorekeeper.sk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {

    // constructor
    public InformationNotFoundException(String msg) {
        super(msg);
    }

}
