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
import com.example.demo.factory.VacationFactory;
import com.example.demo.servicies.VacationService;


@Controller
@RestController
@RequestMapping("/vacations")
public class VacationController {
	
	@Autowired
	private VacationService service;
	
	VacationFactory factory = new VacationFactory();

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public List<Vacation> findAll(){
		
		return service.findAll();
	}
	


	
	 @RequestMapping(method = RequestMethod.POST,value = "" )
	 @ResponseBody
	 public ResponseEntity<Vacation>  create (@RequestBody  Vacation vacation){

		Vacation vacation2 = factory.createVacation(vacation.getVacationType(), vacation);
        
		

		 service.create(vacation2);
		 return  new ResponseEntity<Vacation>(vacation2,HttpStatus.CREATED);

	 }
	 
	 
    
	 
	}
