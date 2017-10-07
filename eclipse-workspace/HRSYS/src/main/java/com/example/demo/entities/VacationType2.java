package com.example.demo.entities;


 
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
 
@Entity
@DiscriminatorValue("type2")
public class VacationType2 extends Vacation {
 
	private Integer numTime2;
	
    public VacationType2() {
	// TODO Auto-generated constructor stub
    }
 
    public VacationType2(Integer numTime  ) {
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