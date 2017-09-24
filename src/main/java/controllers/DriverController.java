package controllers;



import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Bus;
import entities.Driver;
import repositories.DriverRepository;

@Controller
@RestController
@RequestMapping(value="/drivers")

public class DriverController {
	@Autowired
	DriverRepository res;
	
//	@RequestMapping(value = "/save"  )
//	public String save() {
//		Driver driver = new Driver("" , new Bus("leof"));
//		
//		res.save(driver);
//		return "saved";
//	}

}
