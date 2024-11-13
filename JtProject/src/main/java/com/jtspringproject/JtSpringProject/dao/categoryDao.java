package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.Category;

@Repository
public class categoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Category addCategory(String name) {
		Category category = new Category();
		category.setName(name);
		this.sessionFactory.getCurrentSession().saveOrUpdate(category);
		return category;
	}

	@Transactional
	public List<Category> getCategories() {
		return this.sessionFactory.getCurrentSession().createQuery("from CATEGORY").list();
	}

	@Transactional
	public void deleteCategory(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Category category = session.get(Category.class, id);
			if (category != null) {
				// Check for and handle any existing relationships first
				// Clear any associations if needed
				session.delete(category);
				session.flush(); // Ensure the delete is executed
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete category with ID: " + id, e);
		}
	}

	@Transactional
	public Category updateCategory(int id, String name) {
		Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
		category.setName(name);

		this.sessionFactory.getCurrentSession().update(category);
		return category;
	}

	@Transactional
	public Category getCategory(int id) {
		return this.sessionFactory.getCurrentSession().get(Category.class,id);
	}
}
