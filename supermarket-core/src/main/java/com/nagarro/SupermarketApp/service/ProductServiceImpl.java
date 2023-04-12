package com.nagarro.SupermarketApp.service;

import java.util.List;

import com.nagarro.supermarket.dao.ProductDao;
import com.nagarro.supermarket.daoImpl.ProductDaoImpl;
import com.nagarro.supermarket.model.Product;

/**
 * @author rishabhgusain
 *
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao pd;

	public ProductServiceImpl() {
		this.pd = new ProductDaoImpl();
	}

	@Override
	public void updateProduct(Product product) {
		pd.updateProduct(product);
	}

	@Override
	public void addProduct(Product product) {
		pd.addProduct(product);
	}

	@Override
	public void deleteProduct(Long id) {
		pd.deleteProduct(id);
	}

	@Override
	public List<Product> getProducts() {
		return pd.getProducts();
	}

	@Override
	public Product getProduct(Long id) {
		return pd.getProduct(id);
	}

	@Override
	public List<Product> sortProductByCategory(String category) {
		return pd.sortProductByCategory(category);
	}

	@Override
	public List<Product> sortProductByName(String name) {
		return pd.sortProductByName(name);
	}

	@Override
	public List<Product> sortProductByDescription(String description) {
		return pd.sortProductByDescription(description);
	}

}
