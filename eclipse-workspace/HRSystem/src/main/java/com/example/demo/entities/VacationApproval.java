package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="VacationApproval")
public class VacationApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "vacation_id")
	@JsonBackReference(value="vacation")
	public Vacation vacation;
	
	@ManyToOne
	@JoinColumn(name = "approver_id")
	@JsonBackReference(value="approver")
	public Employee approver;


	private String comment;
	private boolean approve;



	public VacationApproval() {

	}
	public VacationApproval(Vacation vacation, Employee approver) {
		
		this.vacation = vacation;
		this.approver = approver;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isApprove() {
		return approve;
	}
	public void setApprove(boolean approve) {
		this.approve = approve;
	}





}
