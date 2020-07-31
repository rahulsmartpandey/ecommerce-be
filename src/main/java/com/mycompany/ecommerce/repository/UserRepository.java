package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
