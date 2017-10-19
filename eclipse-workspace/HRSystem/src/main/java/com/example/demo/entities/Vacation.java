package com.example.demo.entities;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "vacation")
@DiscriminatorColumn(name = "vacation_type")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use =JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "vacationType")
@JsonSubTypes({
    @Type(name = "sick", value = SickVacation.class),
    @Type(name = "personal", value = PersonalVacation.class)
})
@Proxy(lazy=false) 
public  abstract class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee")
    private Employee employee;
    
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name= "vacation_id" ,referencedColumnName="id")
	@JsonManagedReference(value="vacation")
    private List<VacationApproval> vacationApproval;
    
    private boolean isValidate;
   
	   
	@Column(name = "vacation_type", insertable = false, updatable = false)
	private String vacationType;
	
	private Integer longOfVacation;
	
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	
	public List<VacationApproval> getVacationApproval() {
		return vacationApproval;
	}

	public void setVacationApproval(List<VacationApproval> vacationApproval) {
		this.vacationApproval = vacationApproval;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean validate) {
		this.isValidate = validate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVacationType() {
		return vacationType;
	}
	
	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public int getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLongOfVacation() {
		return longOfVacation;
	}

	public void setLongOfVacation(Integer longOfVacation) {
		this.longOfVacation = longOfVacation;
	}

}