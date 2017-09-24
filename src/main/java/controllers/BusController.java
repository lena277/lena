package controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entities.Bus;
import repositories.BusRepository;

@Controller

@RestController
@RequestMapping("/bus")
public class BusController {
	@Autowired
	BusRepository repo;
	
	@PutMapping("/save/{id}")
	public String update( @RequestBody Bus b ,@PathVariable Integer id ) {
		repo.delete(id);
		
		Bus bus = new Bus(b.getBusDriverName());
		bus.setBusId(id);
		repo.save(bus);
			
		return "updated";
		
	}

}
