package com.example.demo.entities;


 
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
 
@Entity
@DiscriminatorValue("type1")
public class VacationType1 extends Vacation {
 
	private Integer numTime;
	
public VacationType1() {
	// TODO Auto-generated constructor stub
}
 
	public VacationType1(Integer numTime  ) {
super();
this.numTime = numTime;
	}



	public Integer getnumTime() {
		return numTime;
	}
 
	public void setnumTime(Integer numTime) {
		this.numTime = numTime;
	}

	public void inform() {
		// informing managers
		
	}
 
}