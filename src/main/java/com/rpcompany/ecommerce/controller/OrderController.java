package com.rpcompany.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpcompany.ecommerce.dto.OrderDTO;
import com.rpcompany.ecommerce.model.Address;
import com.rpcompany.ecommerce.model.Order;
import com.rpcompany.ecommerce.model.Product;
import com.rpcompany.ecommerce.model.User;
import com.rpcompany.ecommerce.repository.AddressRepository;
import com.rpcompany.ecommerce.repository.OrderRespository;
import com.rpcompany.ecommerce.repository.ProductRepository;
import com.rpcompany.ecommerce.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AddressRepository addressRespository;
	@Autowired
	private OrderRespository orderRespository;
	
	@PostMapping("createorder")
	public void createOrder(@RequestBody OrderDTO orderdto){
		
		System.out.println("createorder");
		Optional<User> userOpt = userRepository.findById(orderdto.getUserId());
		User userObj = userOpt.get();
		
		Optional<Product> productOpt = productRepository.findById(orderdto.getProductId());
		Product productObj = productOpt.get();
		
		Address address = orderdto.getAddress();
		address.setUser(userObj);
		addressRespository.save(address);
		
		Order order = new Order();
		order.setProduct(productObj);
		order.setQuantity(orderdto.getQuantity());
		order.setUser(userObj);
		
		orderRespository.save(order);
	
	
		
	}

}
