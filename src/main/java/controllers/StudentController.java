package controllers;

import java.util.List;
import java.util.Optional;

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

import entities.Student;
import services.StudentService;

@Controller


@RequestMapping(value="/students")
public class StudentController  {
	
	@Autowired
	private StudentService service;

	
	@RequestMapping("/saveBus")
	public void saveBus() {
		service.saveBus();
		
	}
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email,@RequestParam String driver) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		service.saveStudent(name, email, driver);
		return "Saved";
	}
	@RequestMapping("/saveTeacher")
	public void saveTeacher() {
		service.saveTeacher();
		
	}
	
	@RequestMapping("/as")
	public Iterable<Student> findAll(){
		
		
		return service.list();
	}
	@RequestMapping(value="/hi" , method = RequestMethod.GET)
	public String hi(){
		
		
		return "hiiiii";
	}
	
	
	@RequestMapping("/find/{first}")
	public Student findOne(@PathVariable Integer first){
		
		
		return service.find(first);
	}
	 @PostMapping("/account")
	 public Student create (@RequestBody Student student){
	  return service.create(student);
	 }
	 @DeleteMapping("/delete/{id}")
	 public String delete(@PathVariable Integer id)
	 {
		 	 
		 service.delete(id);
		 return "deleted";
	 }
	
	 @PutMapping("/update/{id}")
	 public Student update (@RequestBody Student student, @PathVariable Integer id){
	  return service.update(student, id);
	 }
	 
}
