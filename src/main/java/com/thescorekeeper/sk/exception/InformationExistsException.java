package com.thescorekeeper.sk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistsException extends RuntimeException {
    public InformationExistsException(String msg){
        super(msg);
    }
}
