package com.mycompany.ecommerce.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	List<String> products = null;
	
	//http://localhost:8080/api/products
	@GetMapping("/products")
	public List<String> getProducts(){
		products = new ArrayList<>();
		products.add("Data cable");
		products.add("Pendrive");
		products.add("Data drive");
		products.add("Data edge");
		return products;
	}

	@GetMapping("/products/{productName}" )
	public String getProduct(@PathVariable String productName){
		List<String> allProducts = getProducts();
		if(allProducts.contains(productName)){
			return productName;
		}
		return productName+ " not found";
	}
	
	//http://localhost:8080/api/products/search?query=da&sortType=desc
	@GetMapping("/products/search" )
	public List<String> searchProduct(@RequestParam String query, @RequestParam("sortType") String rahul){
		List<String> allProducts = getProducts();
		List<String> filteredProducts = new ArrayList<>();
		
		for(String p : allProducts){
			if(p.toLowerCase().startsWith(query.toLowerCase())){
				filteredProducts.add(p);
			}
		}
		
		if(!filteredProducts.isEmpty() && rahul != null && rahul.equalsIgnoreCase("asc")){
			Collections.sort(filteredProducts);
		}else if(!filteredProducts.isEmpty() && rahul != null && rahul.equalsIgnoreCase("desc")){
			Collections.reverse(filteredProducts);
		}
		
		return filteredProducts;
	}
	
	@PostMapping("/products")
	public List<String> addProduct(@RequestBody String product){
		List<String> allProducts = getProducts();
		allProducts.add(product);
		return allProducts;
	}
	
	//http://localhost:8080/api/products/Data edge
	@DeleteMapping("/products/{productName}" )
	public List<String> delete(@PathVariable("productName") String name){
		List<String> allProducts = getProducts();
		allProducts.remove(name);
		return allProducts;
	}



@PutMapping("/products/{productName}" )
public List<String> update(@PathVariable("productName") String name, @RequestBody String newName){
	
	List<String> allProducts = getProducts();
	
	for ( String p : allProducts){
		if (p.equalsIgnoreCase(name)){
			allProducts.remove(name);
			allProducts.add(newName);
		}
	}
	return allProducts;
}
}