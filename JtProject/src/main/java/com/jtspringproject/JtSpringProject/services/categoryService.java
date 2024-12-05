package com.jtspringproject.services;

import java.util.List;

import com.jtspringproject.modeltest.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.dao.categoryDao;

@Service
public class categoryService {
	@Autowired
	private categoryDao categoryDao;
	
	public Category addCategory(String name) {
		return this.categoryDao.addCategory(name);
	}
	
	public List<Category> getCategories(){
		return this.categoryDao.getCategories();
	}

	public void deleteCategory(int id) {
		this.categoryDao.deleteCategory(id);
	}
	
	public Category updateCategory(int id,String name) {
		return this.categoryDao.updateCategory(id, name);
	}

	public Category getCategory(int id) {
		return this.categoryDao.getCategory(id);
	}
}
