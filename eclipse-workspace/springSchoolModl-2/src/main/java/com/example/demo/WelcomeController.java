package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@Value("${welcomeMassage}")
	private String welcomeMassage;
	
	@RequestMapping("/home")
	public String  hi() {
		return welcomeMassage;
		
	}
	

}
