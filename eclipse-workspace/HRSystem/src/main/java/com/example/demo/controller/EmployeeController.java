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
import com.example.demo.entities.Vacation;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.NoEmployeeException;
import com.example.demo.exceptions.VacationNotFoundException;
import com.example.demo.factory.VacationFactory;
import com.example.demo.servicies.EmployeeService;
import com.example.demo.servicies.VacationService;


@Controller
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	
	@Autowired
	private VacationService vacationService;  
	
	VacationFactory factory = new VacationFactory();

	@Autowired
	private EmployeeService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET)
	public List<Employee> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET)
	public ResponseEntity<Employee> findById(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	      return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/{id}/managers"  ,method = RequestMethod.GET)
	public ResponseEntity<?> findManagersByEmployeeId(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     return new ResponseEntity<List<Employee>>(employee.getManagers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}/vacations"  ,method = RequestMethod.GET)
	public ResponseEntity<?> findVacationsByEmployeeId(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     if(employee.getVacations().size()==0)
	    	 throw new VacationNotFoundException();
	     
	      return new ResponseEntity<List<Vacation>>(employee.getVacations(),  HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}/vacations"  ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addVacationsByEmployeeId(@PathVariable Integer id ,@RequestBody Vacation vacation){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     
	 	Vacation vacation2 = factory.createVacation(vacation.getVacationType(), vacation);
        vacation2.setEmployee(employee);
        employee.getVacations().add(vacation2);
        service.create(employee);
		return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED);

		 
	}
	@RequestMapping(value = "/{id}/managers/{idManger}"  ,method = RequestMethod.GET)
	public ResponseEntity<Employee> findManagerByMangerId(@PathVariable Integer id ,@PathVariable Integer idManger ){
	    
	     Employee employee = service.findById(id);
	     Employee manger = service.findById(idManger);
	     if (employee == null || manger==null ) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     for (Employee manger2: employee.getManagers()  ) 
			if(manger2.getId() == idManger)
	           return new ResponseEntity<Employee>(manger2, HttpStatus.OK);
			
    	 throw new EmployeeNotFoundException(id);

	  
	}
	
	 @RequestMapping(method = RequestMethod.POST,value = "/" )
	 @ResponseBody
	 public ResponseEntity<Employee>  create (@RequestBody @Valid Employee employee,Errors errors){
		  
	      if(errors.hasErrors())
			  return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
	      
		 if (service.isEmployeeExis(employee)) 
              throw new AlreadyExistException(employee.getId());

		 service.create(employee);
		 return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED);

	 }
	 
	 
    
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/" )
	 public ResponseEntity<Employee> deleteAll (){
	    
		service.deleteAll();
        throw new NoEmployeeException();

	 }
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}")
	 public ResponseEntity<Employee> deleteById(@PathVariable Integer id){
		 
		 Employee currentEmployee = service.findById(id);
		 if (currentEmployee == null) 
	    	 throw new EmployeeNotFoundException(id);
	     	
		 service.deleteById(id);
	     return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	 }
    
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}/mangers")
	 public ResponseEntity<Employee> deleteManger(@PathVariable Integer id){
		 
		 Employee currentEmployee = service.findById(id);
		 if (currentEmployee == null) 
	    	 throw new EmployeeNotFoundException(id);
	     
		 	
		 currentEmployee.getManagers().clear();
		 service.create(currentEmployee);
		 return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	 }
	 
	 
	 @RequestMapping( method = RequestMethod.DELETE, value = "/{id}/mangers/{idManger}")
	 public ResponseEntity<Employee> deleteMangerById(@PathVariable Integer id,@PathVariable Integer idManger) {
		
		 Employee currentEmployee = service.findById(id);
		 Employee manger = service.findById(idManger);

		 if (currentEmployee == null || manger ==null ) 
	    	 throw new EmployeeNotFoundException(id);
	     
		List<Employee> employees = currentEmployee.getManagers();
		for (int i = 0; i < employees.size(); i++) 
			if(employees.get(i).getId() == idManger){		
				employees.remove(i);
				currentEmployee.setManagers(employees);
			    service.create(currentEmployee);
			    return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);

				}
		
    	 throw new EmployeeNotFoundException(id);
	
	 }

	  
	 
	
	 @RequestMapping( method = RequestMethod.PUT, value = "/{id}")
	 public ResponseEntity<Employee> updateById (@RequestBody Employee employee, @PathVariable Integer id){
	  
		 Employee currentEmployee = service.findById(id);

	     if (currentEmployee == null) {
	    	 throw new EmployeeNotFoundException(id);
	     }

	     currentEmployee.setName(employee.getName());
	     currentEmployee.setEmail(employee.getEmail());

	     service.updateById(currentEmployee);
	     return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);	
	     
	 }
	 
	 
     @RequestMapping( method = RequestMethod.POST, value = "/{id}/managers/" )
	 @ResponseBody
	 public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee,@PathVariable Integer id , Errors errors){
		 Employee currentEmployee = service.findById(id);
 		
		 if (currentEmployee == null) 
	    	 throw new EmployeeNotFoundException(id);

		 if(errors.hasErrors())
		       return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
		 
	     if (service.isEmployeeExis(employee)) 
               throw new AlreadyExistException(employee.getId());

     	 employee.getEmployees().add(currentEmployee);
	     currentEmployee.getManagers().add(employee);
	     service.create(currentEmployee);

        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.CREATED);	
	
        }
     
     }
