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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;

import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.NoEmployeeException;
import com.example.demo.exceptions.VacationNotFoundException;
import com.example.demo.servicies.EmployeeService;


@Controller
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	


	@Autowired
	private EmployeeService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public ResponseEntity<Employee> findById(@PathVariable String id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException();
	       
	      return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	
	}
	

	
	


	

	 @RequestMapping(method = RequestMethod.POST,value = "/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
	 @ResponseBody
	 public ResponseEntity<Employee>  create (@RequestBody Employee employee,Errors errors){
		  
	      if(errors.hasErrors())
			  return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
	      
	      
	   
		 service.create(employee);
		 return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED);

	 }
	 
	 
    
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Employee> deleteAll (){
	    
		service.deleteAll();
        throw new NoEmployeeException();

	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Employee> deleteById(@PathVariable String id){
		 
		 Employee currentEmployee = service.findById(id);
		 if (currentEmployee == null) 
	    	 throw new EmployeeNotFoundException();
	     	
		 service.deleteById(id);
	     return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	 }
    

	 
	
	  
	 
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Employee> updateById (@RequestBody Employee employee, @PathVariable String id){
	  
		 Employee currentEmployee = service.findById(id);

	     if (currentEmployee == null) {
	    	 throw new EmployeeNotFoundException();
	     }

	     employee.setId(id);

	     service.updateById(employee);
	     return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);	
	     
	 }
	 
	 
	 

     
     }
