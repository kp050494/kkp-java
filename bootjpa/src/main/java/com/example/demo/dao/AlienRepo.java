package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;

import com.example.demo.model.Alien;

public interface AlienRepo extends JpaRepository<Alien, Integer> {

	List<Alien> findByTech(String string);

}
