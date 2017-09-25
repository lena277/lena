package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import com.example.demo.Entites.Student;
import com.example.demo.services.StudentService;

@RestController


@RequestMapping(value="/students")
public class StudentController  {
	
	@Autowired
	private StudentService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public Iterable<Student> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public Student findById(@PathVariable Integer id){
	
		return service.findById(id);
	}
	
	
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 public Integer create (@RequestBody Student student){
	   service.create(student);
	   return student.getStudentId();
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public String deleteAll (){
	   service.deleteAll();
	   return"All Students deleted";
	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public String deleteById(@PathVariable Integer id)
	 {
		 	 
		 service.deleteById(id);
		 return "Studen with id   = "+ id + "deleted";
	 }
	  
	 
	 @RequestMapping(method = RequestMethod.PUT,value = "/" )
	 public void update (){
	
	 }
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public Student updateById (@RequestBody Student student, @PathVariable Integer id){
	  return service.updateById(student, id);
	 }
	 
	 /*
		@RequestMapping("/saveBus")
		public void saveBus() {
			service.saveBus();
			
		}*/
	 

		/*@RequestMapping("/saveTeacher")
		public void saveTeacher() {
			service.saveTeacher();
			
		}*/
}
