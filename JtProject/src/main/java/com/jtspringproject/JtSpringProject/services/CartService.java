package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.CartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

    public Cart addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    public List<Cart> getCarts() {
        return this.cartDao.getCarts();
    }

    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    public void deleteCart(Cart cart) {
        cartDao.deleteCart(cart);
    }
}