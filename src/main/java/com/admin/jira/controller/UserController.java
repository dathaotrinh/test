package com.admin.jira.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.jira.entity.User;
import com.admin.jira.repo.UserRepo;

@RequestMapping("/api")
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	
	@Autowired
	private UserRepo userRepo;
	
	
	@GetMapping("/users/get")
	public List<User> getUsers() {
		return userRepo.findAll();
	}


	@GetMapping("/users/get/{id}")
	public User getUser(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);

		if (!user.isPresent())
			return null;
			
		return user.get();	
	}
	
	@PostMapping("/users/create")
	public User saveUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	

	@PutMapping("/users/update/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int id) {
		Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setAvatarUrl(user.getAvatarUrl());
		user.setEmail(user.getEmail());
		user.setName(user.getName());
		user.setProjectid(user.getProjectid());
		
		userRepo.save(user);

		return ResponseEntity.noContent().build();
		

	}

	@DeleteMapping("/users/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepo.deleteById(id);
	}
}
