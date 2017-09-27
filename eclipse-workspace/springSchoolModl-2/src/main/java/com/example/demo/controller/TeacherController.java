package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Teacher;
import com.example.demo.entites.Teacher;
import com.example.demo.services.TeacherService;


@RestController
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public Iterable<Teacher> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public ResponseEntity<Teacher> findById(@PathVariable Integer id){
	    
	     Teacher teacher = service.findById(id);
	     if (teacher == null) {
	           return new ResponseEntity(teacher, HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}
	
       
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 public ResponseEntity<Teacher>  create (@RequestBody Teacher teacher){
	  
		 if (service.isTeacherExis(teacher)) {
	         return new ResponseEntity<Teacher>(HttpStatus.CONFLICT);
	     }
		 service.create(teacher);
		 return  new ResponseEntity<Teacher>(teacher,HttpStatus.CREATED);

	 }
	 
	 
    
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Teacher> deleteAll (){
	    service.deleteAll();
        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);

	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Teacher> deleteById(@PathVariable Integer id)
	 {
		 Teacher currentTeacher = service.findById(id);
		 if (currentTeacher == null) {
	         return new ResponseEntity(currentTeacher, HttpStatus.NOT_FOUND);
	     }
		 	
		 service.deleteById(id);
	     return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
	 }
    

	  
	 
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Teacher> updateById (@RequestBody Teacher teacher, @PathVariable Integer id){
	  
		 Teacher currentTeacher = service.findById(id);

	     if (currentTeacher == null) {
	         return new ResponseEntity(currentTeacher, HttpStatus.NOT_FOUND);
	     }

	     currentTeacher.setName(teacher.getName());
	     currentTeacher.setEmail(teacher.getEmail());

	     service.updateById(currentTeacher);
	     return new ResponseEntity<Teacher>(currentTeacher, HttpStatus.OK);	
	     
	 }}
