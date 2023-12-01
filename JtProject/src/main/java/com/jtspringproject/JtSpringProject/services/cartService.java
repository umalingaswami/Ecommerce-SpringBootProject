package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.cartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cartService {
    @Autowired
    private cartDao cartDao;


    public Cart addCart(Cart cart)
    {
        return cartDao.addCart(cart);
    }
//    public Cart getCart(int id)
//    {
//        return cartDao.getCart(id);
//    }
public List<Cart> getCarts(){
    return this.cartDao.getCarts();
}

    public void updateCart(Cart cart){
        cartDao.updateCart(cart);
    }
    public void deleteCart(Cart cart)
    {
        cartDao.deleteCart(cart);
    }


    public Cart getOrCreateCart(User user) {
        Cart cart = cartDao.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(user);
            cartDao.addCart(cart);
        }
        return cart;
    }

    @Autowired
    private productService productService;
    public void removeProductFromCart(User user, int productId) {
        Cart cart = getOrCreateCart(user); // Retrieves the cart for the user

        cart.removeProduct(productService.getProduct(productId));
        updateCart(cart);
    }


}
