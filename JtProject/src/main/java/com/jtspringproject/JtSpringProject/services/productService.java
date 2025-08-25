package com.jtspringproject.JtSpringProject.services;

import java.util.List;

import com.jtspringproject.JtSpringProject.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.productDao;
import com.jtspringproject.JtSpringProject.models.Product;

import javax.transaction.Transactional;

@Service
public class productService {
	@Autowired
	private productDao productDao;
	
	public List<Product> getProducts(){
		return this.productDao.getProducts();
	}
	
	public Product addProduct(Product product) {
		return this.productDao.addProduct(product);
	}
	
	public Product getProduct(int id) {
		return this.productDao.getProduct(id);
	}

	@Transactional
	public Product updateProduct(int id, Product product){
		Product oldProduct = getProduct(id);
		if(oldProduct != null){
			oldProduct.setName(product.getName());
			oldProduct.setCategory(product.getCategory());
			oldProduct.setPrice(product.getPrice());
			oldProduct.setImage(product.getImage());
			oldProduct.setDescription(product.getDescription());
			oldProduct.setWeight(product.getWeight());
			oldProduct.setQuantity(product.getQuantity());
			return oldProduct;
		}
		else{
			return this.productDao.updateProduct(product);
		}
	}
	public boolean deleteProduct(int id) {
		return this.productDao.deletProduct(id);
	}

	
}
