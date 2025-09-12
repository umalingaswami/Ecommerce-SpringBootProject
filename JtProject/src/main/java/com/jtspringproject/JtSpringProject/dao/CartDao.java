package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jtspringproject.JtSpringProject.models.Cart;

@Repository
@Transactional
public class CartDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Cart addCart(Cart cart) {
        entityManager.persist(cart);
        return cart;
    }

    public List<Cart> getCarts() {
        return entityManager.createQuery("from Cart", Cart.class).getResultList();
    }

    public void updateCart(Cart cart) {
        entityManager.merge(cart);
    }

    public void deleteCart(Cart cart) {
        entityManager.remove(entityManager.contains(cart) ? cart : entityManager.merge(cart));
    }
}