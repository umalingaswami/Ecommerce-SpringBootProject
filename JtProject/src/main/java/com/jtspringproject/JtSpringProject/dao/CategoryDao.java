package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jtspringproject.JtSpringProject.models.Category;

@Repository
@Transactional
public class CategoryDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        entityManager.persist(category);
        return category;
    }

    public List<Category> getCategories() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }

    public Boolean deletCategory(int id) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            entityManager.remove(category);
            return true;
        }
        return false;
    }

    public Category updateCategory(int id, String name) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            category.setName(name);
            entityManager.merge(category);
        }
        return category;
    }

    public Category getCategory(int id) {
        return entityManager.find(Category.class, id);
    }
}