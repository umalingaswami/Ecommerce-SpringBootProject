package com.jtspringproject.JtSpringProject.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void testBasicCategoryProperties() {
        assertNotNull(category);
    }

    @Test
    void testCategorySettersAndGetters() {
        category.setId(1);
        category.setName("Electronics");
        
        assertEquals(1, category.getId());
        assertEquals("Electronics", category.getName());
    }

    @Test
    void testCategoryProductAssociation() {
        Product product = new Product();
        product.setCategory(category);
        
        assertNotNull(product.getCategory());
        assertEquals(category, product.getCategory());
    }
}