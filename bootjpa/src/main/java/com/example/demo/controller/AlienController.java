package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@RestController
public class AlienController {

	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
	}
	
////////////////////////RESTFUL API //////////////////////////////
	@PostMapping("/alien")
	public Alien addAlien(Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@GetMapping(path="/aliens")
	@ResponseBody
	public List<Alien> getAliens() {
		
		return repo.findAll();
	}
	
	@GetMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getSpecificAlien(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	
	@PutMapping(path="/alien")
	public Alien updateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "Data deleted";
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showalien.jsp");
		Alien a = repo.findById(aid).orElse(new Alien());
		mv.addObject(a);
		return mv;
	}
	
}
