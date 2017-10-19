package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Vacation;
import com.example.demo.entities.VacationApproval;

public interface VacationApprovalRespority extends CrudRepository<VacationApproval, Integer> {

	 List <VacationApproval> findAll();
	 VacationApproval getById(Integer id);

}
