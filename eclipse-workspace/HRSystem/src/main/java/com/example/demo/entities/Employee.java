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
		@GeneratedValue(strategy=GenerationType.IDENTITY )
		private Integer id;
		
		@Size(min=2, max=30) 
	    private String name;
		
		
		@NotEmpty @Email
	    private String email;
	     
	    @NotNull @Min(18) @Max(100)
	    private Integer age;
	   
	    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	    @JsonIgnore
		@JoinTable(name="EMPLOYEE_MANAGERE",
		
			joinColumns={@JoinColumn(name="id")},
			inverseJoinColumns={@JoinColumn(name="managerId")})
	    

		private List<Employee> managers = new ArrayList<Employee>();

		@ManyToMany(mappedBy="managers",fetch = FetchType.LAZY)
		@JsonIgnore
		private List<Employee> employees = new ArrayList<Employee>();

	    
		@OneToMany(cascade = CascadeType.ALL)
	    @JsonIgnore

	    @JoinColumn(name="vacation_id")
	    List<Vacation> vacations;

	   

		public List<Employee> getManagers() {
			return managers;
		}

		public void setManagers(List<Employee> managers) {
			this.managers = managers;
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


		
	    public Employee() {
			
		}
	    
		public Employee(String name, String email , Integer age ) {
			this.name = name;
			this.email = email;
			this.age = age;
			
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
