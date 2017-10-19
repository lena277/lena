package com.example.demo.factory;

import com.example.demo.entities.SickVacation;
import com.example.demo.entities.PersonalVacation;
import com.example.demo.enums.VactionType;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Vacation;

@Component
public  class VacationFactory {
    public  Vacation createVacation(String type , Vacation vacation){
 
    	Vacation vacation2 = null;
        if(type.toLowerCase().equals(VactionType.SICK.type()))    	
            return (SickVacation)vacation;
            
       	else if(type.toLowerCase().equals(VactionType.PERSONAL.type())) 
       		return (PersonalVacation)vacation;
 
        return vacation2;
   
}

    
}


