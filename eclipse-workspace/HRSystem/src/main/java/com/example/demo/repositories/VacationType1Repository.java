package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.entities.VacationType1;

@Transactional
public interface VacationType1Repository extends VacationBaseRepository<VacationType1> {
	 List <VacationType1> findAll();

}
