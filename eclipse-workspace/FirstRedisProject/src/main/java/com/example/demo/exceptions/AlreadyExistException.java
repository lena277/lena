package com.example.demo.exceptions;

public class AlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistException(Integer id) {
        super("Employee with  " + id +" is already Exists");
    }
}