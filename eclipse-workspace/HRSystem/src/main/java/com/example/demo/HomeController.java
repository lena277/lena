package com.example.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping(value="/" , method = RequestMethod.GET)
	public String home() {
	      return " Home";	
	}

    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/private/", method = RequestMethod.GET)
    public String privateHome() {
		return "private";
	}

}
