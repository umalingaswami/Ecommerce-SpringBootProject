package com.jtspringproject.JtSpringProject.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    
    @Test
    public void testBasicProductProperties() {
        Product product = new Product();
        assertNotNull(product);
    }

    @Test
    public void testProductSettersAndGetters() {
        Product product = new Product();
        
        product.setId(1);
        product.setName("Test Product");
        product.setPrice(100);
        product.setDescription("Test Description");
        product.setImage("test.jpg");
        product.setQuantity(10);
        
        assertEquals(1, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(100, product.getPrice());
        assertEquals("Test Description", product.getDescription());
        assertEquals("test.jpg", product.getImage());
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testProductPriceValidation() {
        Product product = new Product();
        product.setPrice(100);
        assertTrue(product.getPrice() >= 0, "Price should not be negative");
    }
} 