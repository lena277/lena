package com.example.demo.exceptions;

public class NoEmployeeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoEmployeeException() {
        super("There's No Employee Found");
    }
}