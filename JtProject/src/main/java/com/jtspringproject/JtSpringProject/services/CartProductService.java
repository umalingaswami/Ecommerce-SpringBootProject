package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.CartProduct;
import com.jtspringproject.JtSpringProject.models.CartProductId;
import com.jtspringproject.JtSpringProject.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    public List<CartProduct> getAllCartProducts() {
        return cartProductRepository.findAll();
    }

    public Optional<CartProduct> getCartProductById(CartProductId id) {
        return cartProductRepository.findById(id);
    }

    @Transactional
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    @Transactional
    public void deleteCartProduct(CartProductId id) {
        cartProductRepository.deleteById(id);
    }
}