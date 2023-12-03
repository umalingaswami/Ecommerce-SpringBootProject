package com.jtspringproject.JtSpringProject.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jtspringproject.JtSpringProject.models.Product;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductDaoTest {

    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query<Product> query;
    @InjectMocks
    private productDao productDao;


    @BeforeEach
    void setUp() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    void setSessionFactory() {
        SessionFactory newSessionFactory = mock(SessionFactory.class);
        productDao.setSessionFactory(newSessionFactory);
        assertEquals(newSessionFactory, productDao.getSessionFactory(), "Session factory was not set correctly.");
    }

    @Test
    void getProducts() {
        List<Product> expectedProducts = new ArrayList<>();

        when(session.createQuery("from PRODUCT")).thenReturn(query);
        when(query.list()).thenReturn(expectedProducts);
        assertEquals(expectedProducts, productDao.getProducts(), "The returned product list does not match the expected list.");
    }

    @Test
    void addProduct() {
        Product product = new Product();
        Product result = productDao.addProduct(product);
        verify(session).save(product);
        assertEquals(product, result, "The returned product does not match the added product.");
    }

    @Test
    void getProduct() {
        int id = 1;
        Product expectedProduct = new Product();
        when(session.get(Product.class, id)).thenReturn(expectedProduct);
        assertEquals(expectedProduct, productDao.getProduct(id), "The returned product does not match the expected product.");
    }

    @Test
    void updateProduct() {
        Product product = new Product();
        Product result = productDao.updateProduct(product);
        verify(session).update(product);
        assertEquals(product, result, "The returned product does not match the updated product.");
    }

    @Test
    void deleteProduct() {
        int id = 1;
        Product product = new Product();
        when(session.load(Product.class, id)).thenReturn(product);
        assertTrue(productDao.deletProduct(id), "Product was not deleted successfully.");
    }


    @Test
    void searchProducts() {
        String queryStr = "test";
        List<Product> expectedProducts = new ArrayList<>();
        when(session.createQuery("FROM PRODUCT WHERE name LIKE :query OR description LIKE :query")).thenReturn(query);
        when(query.setParameter("query", "%" + queryStr + "%")).thenReturn(query);
        when(query.list()).thenReturn(expectedProducts);
        assertEquals(expectedProducts, productDao.searchProducts(queryStr), "The returned product list does not match the expected list.");
    }
}
