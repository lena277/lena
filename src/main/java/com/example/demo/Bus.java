package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="bus")
public class Bus {

	@Id
	@Column(name = "Bus_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;
	
	@Column(name = "Bus_Driver_Name")
	private String busDriverName;
	
	@OneToOne(mappedBy="bus")
	private Driver driver;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "Bus_id" ,referencedColumnName="Bus_id")
	private List<Student> student = new ArrayList<Student>();

	
	
	public Bus(String busDriverName) {
		super();
		this.busDriverName = busDriverName;
	}
	public Bus() {

	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getBusDriverName() {
		return busDriverName;
	}

	public void setBusDriverName(String busDriverName) {
		this.busDriverName = busDriverName;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	

	
	
}
