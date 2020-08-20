package com.rpcompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpcompany.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

		
}
