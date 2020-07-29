package com.mycompany.ecommerce.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ecommerce.model.Category;
import com.mycompany.ecommerce.utils.SortByCategoryName;



@RestController
@RequestMapping("/api")
public class CategoryController {
	
	List<Category> categories = null;
	
	//http://localhost:8080/api/categories  -> by default sort type will be ascending order as ( @RequestParam (value = "sortType", defaultValue = "asc") String condition )
	@GetMapping("/categories")
	public List<Category>  getAll(@RequestParam (value = "sortType", defaultValue = "asc") String condition){
		
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
		category.setName("Home Utensils");
		categories.add(category);
		
		category = new Category ();
		category.setId(6l);
		category.setName("Home Appliances");
		categories.add(category);
		
		category = new Category ();
		category.setId(7l);
		category.setName("Grocery");
		categories.add(category);
		
		category = new Category ();
		category.setId(8l);
		category.setName("TVs");
		categories.add(category);
		
		category = new Category ();
		category.setId(9l);
		category.setName("Toys and Baby");
		categories.add(category);
		
		category = new Category ();
		category.setId(10l);
		category.setName("Beauty and Personal Care");
		categories.add(category);
		
		category = new Category ();
		category.setId(11l);
		category.setName("Books");
		categories.add(category);
		
		Collections.sort(categories, new SortByCategoryName(condition));
		return categories;
	}
	
	//http://localhost:8080/api/categories/list/4
	@GetMapping("/categories/list/{categoryId}" ) // @GetMapping("/categories/{categoryId}") - same mapping like below method getByName, so will get error as Ambiguos mapping, to avoid change path or use request Param//
	public Category getById(@PathVariable("categoryId") Long idValue){
		
		List<Category> allCategories = getAll("asc");
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
		
		List<Category> allCategories = getAll("asc");
		
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
	List<Category> allCategories = getAll("asc");
	
	Category catFound = null;
	
	for( Category cat : allCategories){
		if (cat.getName().toLowerCase().startsWith(query)){
			catFound = cat;
			break;
		}
	}
	return catFound;
	
}

@GetMapping("/categories/sort")
public List<Category> sortCategory(@RequestParam String query, @RequestParam ("sortType") String condition){
	
	List<Category> allCategories = getAll("asc");
	List<Category> filteredProducts = new ArrayList<>();
	
	for(Category cat : allCategories){
		if(cat.getName().toLowerCase().startsWith(query.toLowerCase())){
			filteredProducts.add(cat);
		}
	}
	
	if(!filteredProducts.isEmpty() && condition != null ){
		Collections.sort(filteredProducts, new SortByCategoryName(condition));
	}
	return filteredProducts;
}
	



	public void add(){
		
	}
	

	
	public void delete(){
		
		
	}
	
	public void update(){
		
	}

}
