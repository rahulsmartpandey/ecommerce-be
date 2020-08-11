package com.rpcompany.ecommerce.utils;

import java.util.Comparator;

import com.rpcompany.ecommerce.model.Category;

public class SortByCategoryName  implements Comparator<Category>{

	private String sortType;
	
	public SortByCategoryName(String sortType){
		this.sortType=sortType;
	}
	@Override
	public int compare(Category o1, Category o2) {
		if (sortType.equals("asc")){
			return o1.getName().compareTo(o2.getName());
		}
		return o2.getName().compareTo(o1.getName());
	}
	

}
