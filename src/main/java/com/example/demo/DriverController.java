package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")

public class DriverController {
	@Autowired
	DriverRepository res;
	
	@RequestMapping("/save")
	public String save() {
		Driver driver = new Driver("fatoo7" , new Bus("leof"));
		
		res.save(driver);
		return "saved";
	}

}
