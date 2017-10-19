package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Role;
import com.example.demo.entities.Vacation;
import com.example.demo.entities.VacationApproval;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.NoEmployeeException;
import com.example.demo.exceptions.VacationNotFoundException;
import com.example.demo.factory.VacationFactory;
import com.example.demo.repositories.VacationApprovalRespority;
import com.example.demo.servicies.EmployeeService;
import com.example.demo.servicies.VacationService;


@Controller
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	
	
	@Autowired
	private VacationService vacationService;  
	
	@Autowired
	private VacationApprovalRespority approval;  
	
	
	VacationFactory factory = new VacationFactory();

	@Autowired
	private EmployeeService service;

	
	@RequestMapping(value= "" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> findAll(){
		
		return service.list();
	}
	

	@RequestMapping(value = "/{id}"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> findById(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	      return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/{id}/managers"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findManagersByEmployeeId(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     return new ResponseEntity<List<Employee>>(employee.getManagers(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/vacationApprovals"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findVacationApprovalsByEmployeeId(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     return new ResponseEntity<List<VacationApproval>>(employee.getVacationApproval(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/vacationApprovals/{approveId}/approve"  ,method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveVacationApprovalsByEmployeeId(@PathVariable Integer id,@PathVariable Integer approveId){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	     
	     VacationApproval vacationApproval = approval.findOne(approveId);
	     if (vacationApproval == null) 
	    	 throw new EmployeeNotFoundException(id);
	     
	     vacationApproval.setApprove(true);
	     
	     approval.save(vacationApproval);
	     
	     Vacation vacation = vacationApproval.vacation;
	     for(VacationApproval app:vacation.getVacationApproval())
	    	 if(app.isApprove()){
	    		 vacation.setValidate(true);
	    		 vacationService.updateById(vacation);
	    	 }
	     
	     return new ResponseEntity<List<Role>>(employee.getRoles(), HttpStatus.OK);

	}
	@RequestMapping(value = "/{id}/vacationApprovals/{approveId}/reject"  ,method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rejectVacationApprovalsByEmployeeId(@PathVariable Integer id,@PathVariable Integer approveId){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	     
	     VacationApproval vacationApproval = approval.findOne(approveId);
	     if (vacationApproval == null) 
	    	 throw new EmployeeNotFoundException(id);
	     
	     vacationApproval.setApprove(false);
	     
	     approval.save(vacationApproval);
	     Vacation vacation = vacationApproval.vacation;
	     vacation.setValidate(false);
	     vacationService.updateById(vacation);
	    	     
	     return new ResponseEntity<List<Role>>(employee.getRoles(), HttpStatus.OK);

	}
	@RequestMapping(value = "/{id}/roles"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findRolesByEmployeeId(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     return new ResponseEntity<List<Role>>(employee.getRoles(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}/vacations"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findVacationsByEmployeeId(@PathVariable Integer id){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     if(employee.getVacations().size()==0)
	    	 throw new VacationNotFoundException();
	     
	      return new ResponseEntity<List<Vacation>>(employee.getVacations(),  HttpStatus.OK);
	}
	
	
	
    @PreAuthorize("{hasRole('ADMIN')}")
    
	@RequestMapping(value = "/vacationRequests"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findVacationRequestsByEmployeeId(){
	    
	     
	      return new ResponseEntity<List<Vacation>>(vacationService.findByValidatio(false),  HttpStatus.OK);
	}
	

	
	@RequestMapping(value = "/{id}/vacations"  ,method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addVacationsByEmployeeId(@PathVariable Integer id ,@RequestBody Vacation vacation){

	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	       
	     
	 	Vacation vacation2 = factory.createVacation(vacation.getVacationType(), vacation);
        vacation2.setEmployee(employee);
        employee.getVacations().add(vacation2);
        
        for(Employee manager:employee.getManagers()) {
        	VacationApproval vacationAppreoval = new VacationApproval(vacation2 , manager);
        	manager.getVacationApproval().add(vacationAppreoval);
        	service.updateById(manager);
        }
        service.create(employee);

		return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED);

		 
	}
	
	@RequestMapping(value = "/{id}/roles"  ,method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<?> addRolesByEmployeeId(@PathVariable Integer id ,@RequestBody Role role){
	    
	     Employee employee = service.findById(id);
	     if (employee == null) 
	    	 throw new EmployeeNotFoundException(id);
	    
        employee.getRoles().add(role);
        role.getEmployees().add(employee);
        service.create(employee);
		return  new ResponseEntity<Employee>(employee,HttpStatus.CREATED); 
	}

	
	@RequestMapping(value = "/{id}/managers/{idManger}"  ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
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

	     employee.setId(id);

	     service.updateById(employee);
	     return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);	
	     
	 }
	 
	 
     @RequestMapping( method = RequestMethod.POST, value = "/{id}/managers/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
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
