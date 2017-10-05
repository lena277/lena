package com.example.demo.servicies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public List<Employee> list(){
		return repository.findAll();
	}
	
	public Employee findById(Integer id) {
		return repository.findOne(id);
	}
	
	public List<Employee> findEmployeesByName(String name) {
		return repository.findByName(name);
	}
	

    public boolean isEmployeeExis(Employee employee) {
    	return repository.exists(employee.getId());
    }
	public Employee create(Employee employee) {
		return repository.save(employee);
	}
	
	public void deleteById(Integer inttger) {
		 repository.delete(inttger);
	}
/*	public List<Employee> getManagers () {
	return repository.findByIsManger(true);
	}*/
	public void deleteAll() {
		 repository.deleteAll();
	}


	public void updateById(Employee employee) {
		  
	    repository.save(employee);  
	
	}}
