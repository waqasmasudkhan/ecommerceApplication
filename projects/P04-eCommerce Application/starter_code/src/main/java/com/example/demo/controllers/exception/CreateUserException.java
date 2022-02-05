package com.example.demo.controllers.exception;

public class CreateUserException extends RuntimeException {

    public CreateUserException(String message){
        super(message);
    }

}
