package com.mycompany.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ecommerce.model.Category;



@RestController
@RequestMapping("/api")
public class CategoryController {
	
	List<Category> categories = null;
	
	//http://localhost:8080/api/categories
	@GetMapping("/categories")
	public List<Category>  getAll(){
		categories = new ArrayList<>();
		Category category = new Category ();
		category.setId(1l);
		category.setName("Footwear");
		categories.add(category);
		
		category = new Category ();
		category.setId(2l);
		category.setName("Cloth");
		categories.add(category);
		
		category = new Category ();
		category.setId(3l);
		category.setName("Electronics");
		categories.add(category);
		
		category = new Category ();
		category.setId(4l);
		category.setName("Accessories");
		categories.add(category);
		
		category = new Category ();
		category.setId(5l);
		category.setName("Home and Kitchen");
		categories.add(category);
		
		return categories;
	}
	
	//http://localhost:8080/api/categories/list/4
	@GetMapping("/categories/list/{categoryId}" ) // @GetMapping("/categories/{categoryId}") - same mapping like below method getByName, so will get error as Ambiguos mapping, to avoid change path or use request Param//
	public Category getById(@PathVariable("categoryId") Long idValue){
		
		List<Category> allCategories = getAll();
		Category catFound = null;
		
			for (Category cat : allCategories){
				if(cat.getId() == idValue){
					catFound = cat;
					break;
				}
			}
			return catFound;
	}
	
	//http://localhost:8080/api/categories/Cloth
	
@GetMapping("/categories/{categoryName}" )
	public Category  getByName(@PathVariable("categoryName") String catName){
		
		List<Category> allCategories = getAll();
		
		Category catFound = null;
		
		for( Category cat : allCategories){
			if (cat.getName().equalsIgnoreCase(catName)){
				catFound = cat;
				break;
			}
		}
		return catFound;
}

@GetMapping("/categories/search")
public Category searchCategory(@RequestParam String query){
	List<Category> allCategories = getAll();
	
	Category catFound = null;
	
	for( Category cat : allCategories){
		if (cat.getName().toLowerCase().startsWith(query)){
			catFound = cat;
			break;
		}
	}
	return catFound;
	
}

public Category sortCategory(@RequestParam String query, @RequestParam ("sortType") String condition){
	
	List<Category> allCategories = getAll();
	List<Category> filteredProducts = new ArrayList<>();
	
	for(Category cat : allCategories){
		if(cat.getName().toLowerCase().startsWith(query.toLowerCase())){
			filteredProducts.add(cat);
		}
	}
	return null;
}
	



	public void add(){
		
	}
	

	
	public void delete(){
		
		
	}
	
	public void update(){
		
	}

}
