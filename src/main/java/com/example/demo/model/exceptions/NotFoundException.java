package com.example.demo.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {


    public NotFoundException(String message) {
        super(message);
    }



    public static void check(boolean expresion,String message) throws NotFoundException {
        if (expresion){
            throw new NotFoundException(message);
        }
    }
}
