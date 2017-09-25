package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entites.Bus;
import com.example.demo.Entites.Student;
import com.example.demo.services.BusService;

@Controller

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
	public Bus findById(@PathVariable Integer id){
	
		return service.findById(id);
	}
	
	
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 public Integer create (@RequestBody Bus bus){
	   service.create(bus);
	   return bus.getBusId();
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public String deleteAll (){
	   service.deleteAll();
	   return"All Buses deleted";
	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public String deleteById(@PathVariable Integer id)
	 {
		 	 
		 service.deleteById(id);
		 return "Bus with id   = "+ id + "deleted";
	 }
	  
	 
	 @RequestMapping(method = RequestMethod.PUT,value = "/" )
	 public void update (){
	
	 }
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public Bus updateById (@RequestBody Bus bus, @PathVariable Integer id){
	  return service.updateById(bus, id);
	 }
}
