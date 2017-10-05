package com.example.demo.entities;


 
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.demo.enums.VactionType;
 
@Entity
@DiscriminatorValue("type2")
public class VacationType2 extends Vacation {
 
	private Integer numTime2;
	
public VacationType2() {
	// TODO Auto-generated constructor stub
}
 
	public VacationType2(Integer numTime2 ) {
super();
this.numTime2 = numTime2;
	}

	public Integer getnumTime() {
		return numTime2;
	}
 
	public void setnumTime(Integer numTime2) {
		this.numTime2 = numTime2;
	}

	public void inform() {
		// informing mang + hr		
	}
 
}