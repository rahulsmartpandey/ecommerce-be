package com.rpcompany.ecommerce.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rpcompany.ecommerce.model.User;
import com.rpcompany.ecommerce.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/signup/users")
	public List<User>  getAllUsers()
	{
		List<User> users = userRepository.findAll();
		return users;
	}

	@PostMapping("/signup")
	public String signUp (@RequestBody User user) {
		Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
		User u = null;
		if(userOpt.isPresent()){
			u = userOpt.get();
		}else{
			u = userRepository.save(user);
		}
		return u.getEmail();
	}
	
	@PostMapping("/login")
	public boolean login(@RequestBody User user){
		
	Optional<User> userOpt = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	
	if (userOpt.isPresent()){
		return true;
		
	}
	return false;
		
	}
}
