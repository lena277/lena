package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Vacation;
import com.example.demo.entities.VacationApproval;
import com.example.demo.repositories.VacationApprovalRespority;

@Controller
@RestController
@RequestMapping("/vacationApprovals")
public class VacationApprovalController {
	
	@Autowired
	VacationApprovalRespority approval ;
	
	@RequestMapping(value= "/" , method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VacationApproval> findAll(){
		
		return approval.findAll();
	}

}
