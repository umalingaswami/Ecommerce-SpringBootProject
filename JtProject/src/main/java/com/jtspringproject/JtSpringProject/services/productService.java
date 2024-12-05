package com.jtspringproject.services;

import java.util.List;

import com.jtspringproject.modeltest.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productService {
	@Autowired
	private com.jtspringproject.dao.productDao productDao;
	
	public List<Product> getProducts(){
		return this.productDao.getProducts();
	}
	
	public Product addProduct(Product product) {
		return this.productDao.addProduct(product);
	}
	
	public Product getProduct(int id) {
		return this.productDao.getProduct(id);
	}

	public Product updateProduct(int id,Product product){
		product.setId(id);
		return this.productDao.updateProduct(product);
	}
	public boolean deleteProduct(int id) {
		return this.productDao.deletProduct(id);
	}

	
}
