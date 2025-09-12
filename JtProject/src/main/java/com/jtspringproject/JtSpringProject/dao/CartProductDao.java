package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jtspringproject.JtSpringProject.models.CartProduct;
import com.jtspringproject.JtSpringProject.models.Product;

@Repository
@Transactional
public class CartProductDao {
    @PersistenceContext
    private EntityManager entityManager;

    public CartProduct addCartProduct(CartProduct cartProduct) {
        entityManager.persist(cartProduct);
        return cartProduct;
    }

    public List<CartProduct> getCartProducts() {
        return entityManager.createQuery("from CartProduct", CartProduct.class).getResultList();
    }

    public List<Product> getProductByCartID(Integer cart_id) {
        String hql = "select cp.product from CartProduct cp where cp.cart.id = :cart_id";
        return entityManager.createQuery(hql, Product.class)
            .setParameter("cart_id", cart_id)
            .getResultList();
    }
}