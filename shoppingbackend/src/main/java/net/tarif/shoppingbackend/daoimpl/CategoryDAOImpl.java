package net.tarif.shoppingbackend.daoimpl;


import java.util.ArrayList;
import java.util.List;

import net.tarif.shoppingbackend.dao.CategoryDAO;
import net.tarif.shoppingbackend.dto.Category;

import org.springframework.stereotype.Repository;


@Repository("categoryDAO")

public class CategoryDAOImpl implements CategoryDAO {

	
	private static List<Category> categories=new ArrayList<Category>();
	
	static{
		Category category=new Category();
	    category.setId(1);
	    category.setName("television");
	    category.setDescription("this is television");
	    categories.add(category);
	   
	    category=new Category();
	    category.setId(2);
	    category.setName("mobile");
	    category.setDescription("this is mobile");
	    categories.add(category);
	    
	    category=new Category();
	    category.setId(3);
	    category.setName("laptop");
	    category.setDescription("this is laptop");
	    categories.add(category);
	}
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}
	@Override
	public Category get(int id) {
		for(Category category : categories){
			if(category.getId()==id)  return category;
		}
		return null;
	}

	
}

	

	