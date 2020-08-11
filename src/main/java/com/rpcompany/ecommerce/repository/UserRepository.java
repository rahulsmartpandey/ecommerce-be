package com.rpcompany.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpcompany.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByEmailAndPassword(String email, String password);
	public Optional<User> findByEmail(String email);
}