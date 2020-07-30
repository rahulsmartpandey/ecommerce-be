package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
