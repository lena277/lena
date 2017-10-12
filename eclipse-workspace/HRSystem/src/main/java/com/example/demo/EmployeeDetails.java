package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.catalina.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.Employee;

public class EmployeeDetails implements UserDetails {

	private String password;
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	 public EmployeeDetails(Employee employee) {
		 
		 this.password = employee.getPassword();
		 this.userName = employee.getName();
		 
		 List<GrantedAuthority> auth = new ArrayList<>();
		 for(com.example.demo.entities.Role role : employee.getRoles())
			 auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
		 
		 this.authorities = auth;
		 
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
