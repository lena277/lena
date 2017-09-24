package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="driver")
public class Driver {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer driver_id;
	
	@Column(name="Driver_Name")
	private String driver_Name;
	
	/*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Bus_id")
	private Bus bus;*/

	

	public Driver() {
	
	}

//	public Driver(String driver_Name,  Bus bus) {
//		super();
//		this.driver_Name = driver_Name;
//		this.bus= bus;
//	}

	public String getDriver_Name() {
		return driver_Name;
	}

	public void setDriver_Name(String driver_Name) {
		this.driver_Name = driver_Name;
	}
/*
	public Bus getBus() {
		return bus;
	}*/

//	public void setBus(Bus bus) {
//		this.bus = bus;;
//	}
	
	

}
