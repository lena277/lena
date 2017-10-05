package com.example.demo.factory;

import com.example.demo.entities.VacationType1;
import com.example.demo.entities.VacationType2;
import com.example.demo.enums.VactionType;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Vacation;

@Component

public  class VacationFactory {
    public  Vacation createVacation(String type , Vacation vacation){
        Vacation vacation2 = null;

     
               if(type.toLowerCase().equals(VactionType.TYPE1.type())) 
            	
            	return (VacationType1)vacation;
            
            	else if(type.toLowerCase().equals(VactionType.TYPE2.type())) 
            		return (VacationType2)vacation;
        
 
     
        return vacation2;
   
}

}