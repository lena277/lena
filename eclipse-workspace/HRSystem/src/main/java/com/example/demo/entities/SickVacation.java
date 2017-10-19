package com.example.demo.entities;


 
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
 
@Entity
@DiscriminatorValue("sick")
public class SickVacation extends Vacation {
 
	private boolean hrAcceptance;
	private boolean adminAcceptance;
	
    public SickVacation() {
    }
 
    
	

	public boolean isHrAcceptance() {
		return hrAcceptance;
	}

	public void setHrAcceptance(boolean hrAcceptance) {
		this.hrAcceptance = hrAcceptance;
	}

	public boolean isAdminAcceptance() {
		return adminAcceptance;
	}

	public void setAdminAcceptance(boolean adminAcceptance) {
		this.adminAcceptance = adminAcceptance;
	}

	public void inform() {
		
		
	}
 
}