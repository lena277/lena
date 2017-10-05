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
import com.example.demo.servicies.EmployeeService;


@Controller
@RestController
@RequestMapping("/managers")
public class ManagerController {
	
/*	@Autowired
	private EmployeeService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public Iterable<Employee> findAll(){
		
		return service.getManagers();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public ResponseEntity<Employee> findById(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null|| employee.getEmployees().size()==0) {
	           return new ResponseEntity( HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/{id}/employees/{idEmployee}"  ,method = RequestMethod.GET)
	public ResponseEntity<?> findManagerById(@PathVariable Integer id,@PathVariable Integer idEmployee){
	    
	     Employee manger = service.findById(id);
	     Employee employee =  service.findById(idEmployee);
	     if (employee == null|| employee.getEmployees().size()==0|| manger==null) {
	           return new ResponseEntity(HttpStatus.NOT_FOUND);
	       }
	     for(Employee manger2 :manger.getEmployees())
	    	 if(manger2.getId()==idEmployee)
	       return new ResponseEntity<Employee>(manger2, HttpStatus.OK);
	     
         return new ResponseEntity(HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(value = "/{id}/employees"  ,method = RequestMethod.GET)
	public ResponseEntity<?> findManagerById(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null|| employee.getEmployees().size()==0 ) {
	           return new ResponseEntity(HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<List<Employee>>(employee.getEmployees(), HttpStatus.OK);
	}
	
       
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 @ResponseBody
	 public ResponseEntity<Employee>  create (@RequestBody @Valid Employee employee,Errors errors){
		  
			 if(errors.hasErrors())
			       return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
		 
		 service.create(employee);
		 return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED);

	 }
	 @RequestMapping(method = RequestMethod.POST,value = "/{id}/employees")
	 @ResponseBody
	 public ResponseEntity<Employee>  createEmployee (@PathVariable Integer id,@RequestBody @Valid Employee employee,Errors errors){
		  
		    Employee Currentemployee = service.findById(id);
		     if (Currentemployee == null ) {
		           return new ResponseEntity(HttpStatus.NOT_FOUND);
		       }
		      
		
			 if(errors.hasErrors())
			       return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
		 
			 Currentemployee.getEmployees().add(employee);
			 employee.getManagers().add(Currentemployee);
		 service.create(employee);
		 return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED);

	 }
	 
    
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Employee> deleteAll (){
	    List <Employee> managers = service.getManagers();
	    if(managers!=null)
	    for(Employee manger : managers)
	    	{
	    	manger.getEmployees().clear();
	    	service.create(manger);
	    	}
	    	
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);

	 }

	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Employee> deleteMangerById(@PathVariable Integer id)
	 {
		 Employee currentEmployee = service.findById(id);
		 if (currentEmployee == null ||currentEmployee.getEmployees().size()==0 ) {
	         return new ResponseEntity(currentEmployee, HttpStatus.NOT_FOUND);
	     }
		 currentEmployee.getEmployees().clear();
		 
		 service.create(currentEmployee);
	     return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	 }
    
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}/employees")
	 public ResponseEntity<Employee> deleteEmployeesOfManger(@PathVariable Integer id)
	 {
		 Employee currentEmployee = service.findById(id);
		 if (currentEmployee == null ||currentEmployee.getEmployees().size()==0 ) {
	         return new ResponseEntity(currentEmployee, HttpStatus.NOT_FOUND);
	     }
		 currentEmployee.getEmployees().clear();
		 
		 service.create(currentEmployee);
	     return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	 }
    
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}/employees/{idEmployee}")
	 public ResponseEntity<Employee> deleteEmployeeOfMangerById(@PathVariable Integer id,@PathVariable Integer idEmployee)
	 {
		 Employee manger = service.findById(id);
		 Employee employee =  service.findById(idEmployee);
		 if (manger == null ||manger.getEmployees().size()==0||employee==null ) {
	         return new ResponseEntity(manger, HttpStatus.NOT_FOUND);
	     }
		 List<Employee> employees = manger.getEmployees();
			for (int i = 0; i < employees.size(); i++) 
				if(employees.get(i).getId() == idEmployee)
					{employees.remove(i);
					manger.setEmployees(employees);
					 service.create(manger);
					 return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);

					}
					 
			
	     return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	 }
	  
	 
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Employee> updateById (@RequestBody Employee employee, @PathVariable Integer id){
	  
		 Employee currentEmployee = service.findById(id);

	     if (currentEmployee == null || currentEmployee.getEmployees().size()==0 ) {
	         return new ResponseEntity(currentEmployee, HttpStatus.NOT_FOUND);
	     }

	     currentEmployee.setName(employee.getName());
	     currentEmployee.setEmail(employee.getEmail());
	     currentEmployee.setAge(employee.getAge());
	     
	     service.updateById(currentEmployee);
	     return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);	
	     
	 }
	 
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}/employees/{idEmployee}")
	 public ResponseEntity<Employee> updateById (@RequestBody Employee employee, @PathVariable Integer id , @PathVariable Integer idEmployee){
	  
		 
		 Employee currentEmployee = service.findById(idEmployee);
		 Employee manger = service.findById(id);
         
		 if (currentEmployee == null || manger.getEmployees().size()==0|| manger==null ) {
	         return new ResponseEntity( HttpStatus.NOT_FOUND);
	     }
		for(Employee employee2: manger.getEmployees())
			if(employee2.getId() == idEmployee)
			{
	     currentEmployee.setName(employee.getName());
	     currentEmployee.setEmail(employee.getEmail());
	     currentEmployee.setAge(employee.getAge());
	     
	     service.updateById(currentEmployee);
	     return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);	
			}
        return new ResponseEntity( HttpStatus.NOT_FOUND);

	 }
 @RequestMapping( method = RequestMethod.POST, value = "/{id}/managers/" )
	 @ResponseBody

	 public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee,@PathVariable Integer id , Errors errors){
		 Employee currentEmployee = service.findById(id);
 		
		 if (currentEmployee == null) {
	         return new ResponseEntity(currentEmployee, HttpStatus.NOT_FOUND);
	     }

		 if(errors.hasErrors())
		       return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
	 if (service.isEmployeeExis(employee)) {
       return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
   }
	 currentEmployee.getManagers().add(employee);
	 service.create(currentEmployee);

     return new ResponseEntity<Employee>(currentEmployee, HttpStatus.CREATED);	

		
}*/}

