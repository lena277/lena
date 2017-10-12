package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Vacation;
import com.example.demo.entities.Vacation;
import com.example.demo.entities.Vacation;
import com.example.demo.entities.Vacation;
import com.example.demo.exceptions.VacationNotFoundException;
import com.example.demo.exceptions.VacationNotFoundException;
import com.example.demo.exceptions.VacationNotFoundException;
import com.example.demo.factory.VacationFactory;
import com.example.demo.servicies.VacationService;


@Controller
@RestController
@RequestMapping("/vacations")
public class VacationController {
	
	@Autowired
	private VacationService service;
	
	VacationFactory factory = new VacationFactory();

	
	@RequestMapping(value= "/" , method = RequestMethod.GET)
	public List<Vacation> findAll(){
		
		return service.findAll();
	}
	


	
	 @RequestMapping(method = RequestMethod.POST,value = "" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 @ResponseBody
	 public ResponseEntity<Vacation>  create (@RequestBody  Vacation vacation){
		Vacation vacation2 = factory.createVacation(vacation.getVacationType(), vacation);
		service.create(vacation2);
		return  new ResponseEntity<Vacation>(vacation2,HttpStatus.CREATED);

	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Vacation> deleteById(@PathVariable Integer id)
	 {
		 Vacation currentVacation = service.findById(id);
		 if (currentVacation == null) {
	    	 throw new VacationNotFoundException(id);
	     }
		 	
		 service.deleteById(id);
	     return new ResponseEntity<Vacation>(HttpStatus.NO_CONTENT);
	 }
    
	 
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Vacation> updateById (@RequestBody Vacation vacation, @PathVariable Integer id){
	  
		 Vacation currentVacation = service.findById(id);

	     if (currentVacation == null) {
	    	 throw new VacationNotFoundException(id);
	     }
	     
	     Vacation type =factory.createVacation(currentVacation.getVacationType(), vacation) ;
         type.setId(id);
	     
	     service.updateById(type);
	     return new ResponseEntity<Vacation>(type, HttpStatus.OK);	
	     
	 }
	 @RequestMapping( method = RequestMethod.GET, value = "")
	 public ResponseEntity<?> updateByType (@RequestParam("type") String type){
		 
		 List <Vacation> vacations = service.findByType(type);
		 if(vacations.size()==0)
             throw new VacationNotFoundException();
		 
		 return	 new ResponseEntity<List<Vacation>>(vacations, HttpStatus.OK);
		 
		 
	     
	 }
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Vacation> deleteAll (){
	    service.deleteAll();
        throw new VacationNotFoundException();

	 }
	 
	
	 
	}
