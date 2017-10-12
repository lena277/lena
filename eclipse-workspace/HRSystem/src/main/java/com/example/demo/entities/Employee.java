package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO )
	private Integer id;

	@Size(min=2, max=30) 
	private String name;


	@NotEmpty @Email
	private String email;
    
	private String password;
	
	@NotNull @Min(18) @Max(100)
	private Integer age;

	@OneToMany(cascade = CascadeType.ALL , fetch= FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	@ManyToMany(cascade={CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinTable(name="EMPLOYEE_MANAGERE",joinColumns={@JoinColumn(name="id")},inverseJoinColumns={@JoinColumn(name="managerId")})
	@JsonBackReference(value="employees")
	private List<Employee> managers = new ArrayList<Employee>();

	@ManyToMany(mappedBy="managers",fetch = FetchType.LAZY)
	@JsonBackReference(value="managers")
	private List<Employee> employees = new ArrayList<Employee>();

	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name= "employee_id" ,referencedColumnName="id")
	@JsonManagedReference(value="employee")
	List<Vacation> vacations;


	public List<Employee> getManagers() {
		return managers;
	}

	public void setManagers(List<Employee> managers) {
		this.managers = managers;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee() {

	}

	public Employee(String name, String email , Integer age,List<Employee > manager ) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.managers = manager;

	}



	public Employee(Employee em) {
		this.name = em.getName();
		this.email = em.getEmail();
		this.age = em.getAge();
		this.managers = em.getManagers();
		this.password = em.getPassword();
        this.id = em.getId();
        this.vacations = em.getVacations();
        this.roles = em.getRoles();
	}

	public List<Vacation> getVacations() {
		return vacations;
	}

	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
