package com.nagarro.SupermarketApp.service;

import java.util.List;

import com.nagarro.supermarket.model.Product;

public interface ProductService {
	
	void updateProduct(Product product);
	void deleteProduct(Long id);
	void addProduct(Product product);
	List<Product> getProducts();
	Product getProduct(Long id);
	List<Product> sortProductByCategory(String category);
	List<Product> sortProductByName(String name);
	List<Product> sortProductByDescription(String description);

}
