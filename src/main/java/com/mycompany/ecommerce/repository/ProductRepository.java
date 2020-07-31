package com.mycompany.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycompany.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
		@Query("SELECT p FROM Product p WHERE p.user.id = :userId")
	    public List<Product> findProductsForUser(@Param("userId") Long userId);
		
		@Query("SELECT p FROM Product p WHERE p.user.id = :userId AND p.category.id = :categoryId")
	    public List<Product> findProductsForUserAndCategory(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
