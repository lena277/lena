package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.entities.VacationType1;
import com.example.demo.entities.VacationType2;

@Transactional
public interface VacationType2Repository extends VacationBaseRepository<VacationType2> {
	 List <VacationType2> findAll();

}
