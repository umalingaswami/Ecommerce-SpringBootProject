package com.jtspringproject.JtSpringProject.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testBasicCartProperties() {
        Cart cart = new Cart();
        assertNotNull(cart);
    }

    @Test
    public void testCartSettersAndGetters() {
        Cart cart = new Cart();
        User user = new User();
        
        cart.setId(1);
        cart.setCustomer(user);
        
        assertEquals(1, cart.getId());
        assertEquals(user, cart.getCustomer());
    }
} 