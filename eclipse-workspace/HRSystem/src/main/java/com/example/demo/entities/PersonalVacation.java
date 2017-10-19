package com.example.demo.entities;


 
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
 
@Entity
@DiscriminatorValue("personal")
public class PersonalVacation extends Vacation {
 
	private Integer numTime2;
	
    public PersonalVacation() {
	// TODO Auto-generated constructor stub
    }
 
    public PersonalVacation(Integer numTime  ) {
        super();
        this.numTime2 = numTime;
	}

	public Integer getnumTime() {
		return numTime2;
	}
 
	public void setnumTime(Integer numTime) {
		this.numTime2 = numTime;
	}

	public void inform() {
		
		
	}
 
}