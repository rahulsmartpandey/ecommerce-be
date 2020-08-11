package com.rpcompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpcompany.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
