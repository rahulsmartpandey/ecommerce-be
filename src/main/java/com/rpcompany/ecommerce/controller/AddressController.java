package com.rpcompany.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpcompany.ecommerce.model.Address;
import com.rpcompany.ecommerce.repository.AddressRepository;


@RestController
@RequestMapping("/api/")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	
	@GetMapping("/address")
	public List<Address>  getAllAddress()
	{
		List<Address> address = addressRepository.findAll();
		return address;
	}
	
}
