package com.rpcompany.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpcompany.ecommerce.model.User;
import com.rpcompany.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
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
	public void login(@RequestBody User user){
		
	}
}
