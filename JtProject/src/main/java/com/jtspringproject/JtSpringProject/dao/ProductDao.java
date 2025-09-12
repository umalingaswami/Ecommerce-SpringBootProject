package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jtspringproject.JtSpringProject.models.Product;

@Repository
@Transactional
public class ProductDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getProducts() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    public Product addProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    public Product getProduct(int id) {
        return entityManager.find(Product.class, id);
    }

    public Product updateProduct(Product product) {
        entityManager.merge(product);
        return product;
    }

    public boolean deletProduct(int id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
            return true;
        }
        return false;
    }
}