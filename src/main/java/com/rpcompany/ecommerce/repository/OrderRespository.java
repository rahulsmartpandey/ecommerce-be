package com.rpcompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpcompany.ecommerce.model.Order;

public interface OrderRespository extends JpaRepository<Order, Long> {

}
