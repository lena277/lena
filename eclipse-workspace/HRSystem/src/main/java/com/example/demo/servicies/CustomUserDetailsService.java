package com.example.demo.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.Employee;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private EmployeeRepository em;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Employee> employee = em.findByEmail(email);
		
		employee
		.orElseThrow(() ->  new UsernameNotFoundException("Not found"));
		return employee.map(CustomUserDetails::new).get();
	}

}
