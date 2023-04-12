package com.nagarro.SupermarketApp.controller;

import java.util.List;

import com.nagarro.SupermarketApp.service.ProductService;
import com.nagarro.SupermarketApp.service.ProductServiceImpl;
import com.nagarro.supermarket.dto.ProductDto;
import com.nagarro.supermarket.mapper.ProductMapper;

/**
 * @author rishabhgusain
 *
 */
public class ProductController {

	private ProductService ps;
	private ProductMapper pm;

	public ProductController() {
		this.ps = new ProductServiceImpl();
		this.pm = new ProductMapper();
	}

	/**
	 * @param productDto
	 */
	public void updateProductDto(ProductDto productDto) {
		ps.updateProduct(pm.toModel(productDto));
	}

	/**
	 * @param productDto
	 */
	public void addProductDto(ProductDto productDto) {
		ps.addProduct(pm.toModel(productDto));
	}

	/**
	 * @param id
	 */
	public void deleteProductDto(Long id) {
		ps.deleteProduct(id);
	}

	/**
	 * @return
	 */
	public List<ProductDto> getProductDtos() {
		return pm.toDto(ps.getProducts()) ;
	}

	/**
	 * @param id
	 * @return
	 */
	public ProductDto getProductDto(Long id) {
		return pm.toDto(ps.getProduct(id)) ;
	}
	
	/**
	 * @param category
	 * @return
	 */
	public List<ProductDto> sortProductDtoByCategory(String category){
		return pm.toDto(ps.sortProductByCategory(category)) ;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public List<ProductDto> sortProductDtoByName(String name) {
		return pm.toDto(ps.sortProductByName(name));
	}

	/**
	 * @param description
	 * @return
	 */
	public List<ProductDto> sortProductDtoByDescription(String description) {
		return pm.toDto( ps.sortProductByDescription(description));
	}
	

}
