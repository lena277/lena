package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Bus;
import com.example.demo.entites.Bus;
import com.example.demo.entites.Bus;
import com.example.demo.services.BusService;


@RestController
@RequestMapping("/buses")
public class BusController {
	
	@Autowired
	private BusService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public Iterable<Bus> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public ResponseEntity<Bus> findById(@PathVariable Integer id){
	    
	     Bus bus = service.findById(id);
	     if (bus == null) {
	           return new ResponseEntity(bus, HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}
	
       
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 public ResponseEntity<Bus>  create (@RequestBody Bus bus){
	  
		 if (service.isBusExis(bus)) {
	         return new ResponseEntity<Bus>(HttpStatus.CONFLICT);
	     }
		 service.create(bus);
		 return  new ResponseEntity<Bus>(bus,HttpStatus.CREATED);

	 }
	 
	 
    
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Bus> deleteAll (){
	    service.deleteAll();
        return new ResponseEntity<Bus>(HttpStatus.NO_CONTENT);

	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Bus> deleteById(@PathVariable Integer id)
	 {
		 Bus currentBus = service.findById(id);
		 if (currentBus == null) {
	         return new ResponseEntity(currentBus, HttpStatus.NOT_FOUND);
	     }
		 	
		 service.deleteById(id);
	     return new ResponseEntity<Bus>(HttpStatus.NO_CONTENT);
	 }
    

	  
	 
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Bus> updateById (@RequestBody Bus bus, @PathVariable Integer id){
	  
		 Bus currentBus = service.findById(id);

	     if (currentBus == null) {
	         return new ResponseEntity(currentBus, HttpStatus.NOT_FOUND);
	     }

	     currentBus.setDriverName(bus.getDriverName());

	     service.updateById(currentBus);
	     return new ResponseEntity<Bus>(currentBus, HttpStatus.OK);	
	     
	 }
	 }
