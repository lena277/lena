package com.example.demo.exceptions;

public class VacationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VacationNotFoundException() {
        super("You have no vacation " );
    }
    
    public VacationNotFoundException(Integer id) {
        super("Vacation with " + id + " Not Found" );
    }
    
    
}