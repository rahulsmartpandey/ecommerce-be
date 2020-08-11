package com.rpcompany.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rpcompany.ecommerce.model.Product;
import com.rpcompany.ecommerce.model.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
		public Optional<Product> findByIdAndUserId(Long id, Long userIdnew);
		public List<Product> findByIdAndUser(Long id, User userObj);
		public List<Product> findByIdAndUserEmail(Long id, String uEmail);
		public List<Product> findByNameAndUserUserName(String pName, String uname);
		
		@Query("SELECT p FROM Product p WHERE p.user.id = :userId")
	    public List<Product> findProductsForUser(@Param("userId") Long userId);
		
		@Query("SELECT p FROM Product p WHERE p.user.id = :userId AND p.category.id = :categoryId")
	    public List<Product> findProductsForUserAndCategory(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
