package com.example.demo.controllers;

import com.example.demo.controllers.exception.CreateUserException;
import com.example.demo.controllers.exception.UserAlreadyExistsException;
import com.example.demo.controllers.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity handleCreateUserException(CreateUserException exception){

        return new ResponseEntity(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException(UserAlreadyExistsException exception){

        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception){
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

}
