package com.rpcompany.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rpcompany.ecommerce.model.Category;
import com.rpcompany.ecommerce.model.Product;
import com.rpcompany.ecommerce.model.User;
import com.rpcompany.ecommerce.repository.CategoryRepository;
import com.rpcompany.ecommerce.repository.ProductRepository;
import com.rpcompany.ecommerce.repository.UserRepository;

/**
 * Author: RP
 */
@RestController
//@Controller
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	// http://localhost:8080/api/users/1/products
	@GetMapping("users/{userName}/products")
	public List<Product> getProductsWithProductNameAndUserName(@PathVariable("userName") String uName, @RequestParam("productName") String pName) {
		List<Product> allProducts = productRepository.findByNameAndUserUserName(pName, uName);
		return allProducts;
	}

	// http://localhost:8080/api/users/1/products
	@PostMapping("users/products/{productId}")
	public List<Product> getProductsWithUserEmail(@RequestBody User user, @PathVariable Long productId, @RequestParam String dummy) {
		List<Product> allProducts = productRepository. findByIdAndUser(productId, user);
		return allProducts;
	}
	
	// http://localhost:8080/api/users/1/products
	@GetMapping("users/{userId}/products")
	public List<Product> getProductsForUser(@PathVariable Long userId) {
		List<Product> allProducts = productRepository.findProductsForUser(userId);
		return allProducts;
	}
	
	// http://localhost:8080/api/users/1/categories/2/products
	@GetMapping("users/{userId}/categories/{categoryId}/products")
	public List<Product> getProductsForUserAndCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
		List<Product> allProducts = productRepository.findProductsForUserAndCategory(userId, categoryId);
		return allProducts;
	}

	// http://localhost:8080/api/products ( Post method ( Data which we submit
	// in a form), pass the value in body along with Header Option : key as
	// content-type and value as application/json in Postman

	// http://localhost:8080/api/users/1/categories/1/products
	@PostMapping("users/{userId}/categories/{categoryId}/products")
	public void add(@PathVariable Long userId, @PathVariable Long categoryId, @RequestBody Product product) {
		Optional<User> userOpt = userRepository.findById(userId);
		product.setUser(userOpt.get());
		Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
		product.setCategory(categoryOpt.get());
		productRepository.save(product);

	}

	// http://localhost:8080/api/users/6/products/5
	@DeleteMapping("users/{userId}/products/{productId}")
	public void delete(@PathVariable Long userId, @PathVariable Long productId) {

		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isPresent()) {
			Optional<Product> productOpt = productRepository.findById(productId);
			if (productOpt.isPresent()) {
				productRepository.delete(productOpt.get());
			} else {
				System.out.println("Product does not exist for user " + userOpt.get().getUserName());
			}
		} else {
			System.out.println("User does not exist");
		}

	}

	@PutMapping(value = "users/{userId}/products/{productId}")
	public void update(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Product newProduct) {
		Optional<User> userOpt = userRepository.findById(userId);

		if (userOpt.isPresent()) {
			Optional<Product> productOpt = productRepository.findByIdAndUserId(productId, userOpt.get().getId());
			if (productOpt.isPresent()) {
				if (newProduct.getName() != null) {
					productOpt.get().setName(newProduct.getName());
				}
				if (newProduct.getDescription() != null) {
					productOpt.get().setDescription(newProduct.getDescription());
				}
				productRepository.save(productOpt.get());  // to store details in database
			} else {
				System.out.println("User does not exist"); // syso -> ctrl +
															// space

			}
		} else {
			System.out.println("Product doesnot exist for user");
		}

	}
	/*
	 * @GetMapping("/products/{productName}" ) public getProduct(@PathVariable
	 * String productName){ List<String> allProducts = getProducts();
	 * if(allProducts.contains(productName)){ return productName; } return
	 * productName+ " not found"; }
	 * 
	 * //http://localhost:8080/api/products/search?query=da&sortType=desc
	 * 
	 * @GetMapping("/products/search" ) public List<String>
	 * searchProduct(@RequestParam String query, @RequestParam("sortType")
	 * String rahul){ List<String> allProducts = getProducts(); List<String>
	 * filteredProducts = new ArrayList<>();
	 * 
	 * for(String p : allProducts){
	 * if(p.toLowerCase().startsWith(query.toLowerCase())){
	 * filteredProducts.add(p); } }
	 * 
	 * if(!filteredProducts.isEmpty() && rahul != null &&
	 * rahul.equalsIgnoreCase("asc")){ Collections.sort(filteredProducts); //
	 * .sort can be used here for sorting as it is string but incase of object
	 * sorting need to be done using comparator }else
	 * if(!filteredProducts.isEmpty() && rahul != null &&
	 * rahul.equalsIgnoreCase("desc")){ Collections.reverse(filteredProducts); }
	 * 
	 * return filteredProducts; }
	 * 
	 * @PostMapping("/products") public List<String> addProduct(@RequestBody
	 * String product){ List<String> allProducts = getProducts();
	 * allProducts.add(product); return allProducts; }
	 * 
	 * //http://localhost:8080/api/products/Data edge
	 * 
	 * @DeleteMapping("/products/{productName}" ) public List<String>
	 * delete(@PathVariable("productName") String name){ List<String>
	 * allProducts = getProducts(); allProducts.remove(name); return
	 * allProducts; }
	 * 
	 * 
	 * 
	 * @PutMapping("/products/{productName}" ) public List<String>
	 * update(@PathVariable("productName") String name, @RequestBody String
	 * newName){
	 * 
	 * List<String> allProducts = getProducts();
	 * 
	 * for ( String p : allProducts){ if (p.equalsIgnoreCase(name)){
	 * allProducts.remove(name); allProducts.add(newName); } } return
	 * allProducts; }
	 */
}