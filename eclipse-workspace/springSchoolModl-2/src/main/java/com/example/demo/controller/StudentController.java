package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.FormSubmitEvent.MethodType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Student;
import com.example.demo.services.StudentService;

@RestController


@RequestMapping(value="/students")
public class StudentController  {
	
	@Autowired
	private StudentService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public List<Student> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public ResponseEntity<Student> findById(@PathVariable Integer id){
	    
	     Student student = service.findById(id);
	     if (student == null) {
	           return new ResponseEntity(student, HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
       
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 public ResponseEntity<Student>  create (@RequestBody @Valid Student student,Errors errors){
	  
		 if(errors.hasErrors())
		       return new ResponseEntity<Student>(student, HttpStatus.NOT_ACCEPTABLE);
		 if (service.isStudentExis(student)) {
	         return new ResponseEntity<Student>(HttpStatus.CONFLICT);
	     }
		 service.create(student);
		 return  new ResponseEntity<Student>(student,HttpStatus.CREATED);

	 }
	 
	 
    
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Student> deleteAll (){
	    service.deleteAll();
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);

	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Student> deleteById(@PathVariable Integer id)
	 {
		 Student currentStudent = service.findById(id);
		 if (currentStudent == null) {
	         return new ResponseEntity(currentStudent, HttpStatus.NOT_FOUND);
	     }
		 	
		 service.deleteById(id);
	     return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	 }
    

	  
	 
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Student> updateById (@RequestBody Student student, @PathVariable Integer id){
	  
		 Student currentStudent = service.findById(id);

	     if (currentStudent == null) {
	         return new ResponseEntity(currentStudent, HttpStatus.NOT_FOUND);
	     }

	     currentStudent.setName(student.getName());
	     currentStudent.setEmail(student.getEmail());

	     service.updateById(currentStudent);
	     return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);	
	     
	 }
	 
	
	 
	 
}
