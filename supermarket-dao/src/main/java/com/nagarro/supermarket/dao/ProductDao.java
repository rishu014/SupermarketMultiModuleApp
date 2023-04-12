package com.nagarro.supermarket.dao;

import java.util.List;

import com.nagarro.supermarket.model.Product;

public interface ProductDao {

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(Long id);

	List<Product> getProducts();

	Product getProduct(Long id);

	List<Product> sortProductByCategory(String category);

	List<Product> sortProductByName(String name);

	List<Product> sortProductByDescription(String description);
}
