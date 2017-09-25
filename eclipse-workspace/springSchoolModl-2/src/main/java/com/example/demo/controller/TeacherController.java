package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entites.Student;
import com.example.demo.Entites.Teacher;
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
	public Teacher findById(@PathVariable Integer id){
	
		return service.findById(id);
	}
	
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 public Integer create (@RequestBody Teacher teacher){
	   service.create(teacher);
	   return teacher.getTeacherID();
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public String deleteAll (){
	   service.deleteAll();
	   return"All Teachers deleted";
	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public String deleteById(@PathVariable Integer id)
	 {
		 	 
		 service.deleteById(id);
		 return "Teacher with id   = "+ id + "deleted";
	 }
	  
	 
	 @RequestMapping(method = RequestMethod.PUT,value = "/" )
	 public void update (){
	
	 }
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public Teacher updateById (@RequestBody Teacher teacher, @PathVariable Integer id){
	  return service.updateById(teacher, id);
	 }
}
