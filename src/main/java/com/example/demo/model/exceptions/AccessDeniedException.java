package com.example.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }



    public static void check(boolean expresion, String message) throws AccessDeniedException {
        if (expresion) {
            throw new AccessDeniedException(message);
        }
    }
}


// int,long , heap - stack ,  String
