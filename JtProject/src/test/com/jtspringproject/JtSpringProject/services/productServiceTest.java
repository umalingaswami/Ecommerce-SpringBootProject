package com.jtspringproject.JtSpringProject.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.dao.productDao;
import com.jtspringproject.JtSpringProject.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class productServiceTest {

    @InjectMocks
    private productService productService;

    @Mock
    private com.jtspringproject.JtSpringProject.dao.productDao productDao;

    /**
     * Test adding a product with zero quantity.
     */
    @Test
    public void testZeroQuantity() {
        Product product = new Product();
        product.setQuantity(0);
        product.setPrice(11.1);
        product.setWeight(55.5);

        when(productDao.addProduct(product)).thenReturn(product);

        Product addedProduct = productService.addProduct(product);

        assertEquals(0, addedProduct.getQuantity());
        assertEquals(11.1, addedProduct.getPrice());
        assertEquals(55.5, addedProduct.getWeight());
    }

    /**
     * Test adding a product with minimum price and weight.
     */
    @Test
    public void testMinimumPriceAndWeight() {
        Product product = new Product();
        product.setQuantity(99);
        product.setPrice(0.01);
        product.setWeight(0.01);

        when(productDao.addProduct(product)).thenReturn(product);

        Product addedProduct = productService.addProduct(product);

        assertEquals(99, addedProduct.getQuantity());
        assertEquals(0.01, addedProduct.getPrice());
        assertEquals(0.01, addedProduct.getWeight());
    }

    /**
     * Test adding a product with maximum price and weight.
     */
    @Test
    public void testMaximumPriceAndWeight() {
        Product product = new Product();
        product.setQuantity(77);
        product.setPrice(Double.MAX_VALUE);
        product.setWeight(Double.MAX_VALUE);

        when(productDao.addProduct(product)).thenReturn(product);

        Product addedProduct = productService.addProduct(product);

        assertEquals(77, addedProduct.getQuantity());
        assertEquals(Double.MAX_VALUE, addedProduct.getPrice());
        assertEquals(Double.MAX_VALUE, addedProduct.getWeight());
    }


}
