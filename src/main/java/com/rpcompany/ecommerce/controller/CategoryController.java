package com.rpcompany.ecommerce.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rpcompany.ecommerce.repository.CategoryRepository;
import com.rpcompany.ecommerce.utils.SortByCategoryName;



@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Postman 
	// http://localhost:8080/api/categories  -> by default sort type will be ascending order as ( @RequestParam (value = "sortType", defaultValue = "asc") String condition )
	// http://localhost:8080/api/categories/8 ( Use GET in Postman ( Client ) ) --> To Get details for a specific ID
	@GetMapping("/categories")
	public List<Category>  getAll(@RequestParam (value = "sortType", defaultValue = "asc") String condition){
		
		List<Category> categories = categoryRepository.findAll();
		Collections.sort(categories, new SortByCategoryName(condition));
		return categories;
	}
	
	//   http://localhost:8080/api/categories/list/4
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
	
	//  http://localhost:8080/api/categories/Cloth -> use this URL in postman to check request testing
	
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
//  http://localhost:8080/api/categories/search?query=toys and baby --> it will list specific category 
@GetMapping("/categories/search")
public Category searchCategory(@RequestParam("query") String apple){
	List<Category> allCategories = getAll("asc");
	
	Category catFound = null;
	
	for( Category cat : allCategories){
		if (cat.getName().equalsIgnoreCase(apple)){
			catFound = cat;
			break;
		}
	}
	return catFound;
	
}

// http://localhost:8080/api/categories/searchlist?query=t   ---> it will return the list of categories starting with t
@GetMapping("/categories/searchlist")
public List<Category> searchCategoryList(@RequestParam String query){
	List<Category> allCategories = getAll("asc");
	List<Category> filteredProducts = new ArrayList<>();
	
	
	for( Category cat : allCategories){
		if (cat.getName().toLowerCase().startsWith(query)){
			filteredProducts.add(cat);
			
		}
	}
	return filteredProducts;
	
}

//http://localhost:8080/api/categories/sort?query=b&&sortType=asc   --> it will list categories which start with char "b" and sort it in ascending order as well
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
	// http://localhost:8080/api/categories ( Post method ( Data which we submit in a form),  pass the value in body along with Header Option : key as content-type and value as application/json in Postman
	@PostMapping("/categories")
	public void add(@RequestBody Category category){ //@ResponseBody (multiple fields or for an object ) is same as @RequestParam ( single field )
		categoryRepository.save(category);
	}
	
//  http://localhost:8080/api/categories/8  ( Use DELETE Method in Postman )
	@DeleteMapping("/categories/{id}")
	public void delete(@PathVariable ("id") Long idValue)
	{
		categoryRepository.deleteById(idValue);
		
	}
	
	// http://localhost:8080/api/categories/4 ( Use PUT in Postman) and ( place the value in BODY raw, to be updated with and key as Content-Type and Value as application/json in HEADERS ) 
	@PutMapping("/categories/{id}")
	public void update(@PathVariable("id") Long catId, @RequestBody Category newCategory){
		Optional<Category> categoryOpt = categoryRepository.findById(catId);
		Category category = categoryOpt.get();
		
		if(newCategory.getName() != null){
			category.setName(newCategory.getName());
		}
		if(newCategory.getDescription() != null){
			category.setDescription(newCategory.getDescription());
		}
		categoryRepository.save(category);
		
	}

}
