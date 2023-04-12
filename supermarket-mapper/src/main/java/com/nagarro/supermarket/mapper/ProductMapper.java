package com.nagarro.supermarket.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.nagarro.supermarket.dto.ProductDto;
import com.nagarro.supermarket.model.Product;

public class ProductMapper {
	
	public Product toModel(ProductDto productDto) {
		Product p= new Product();
		p.setId(productDto.getId());
		p.setCategory(productDto.getCategory());
		p.setDescription(productDto.getDescription());
		p.setName(productDto.getName());
		p.setQuantity(productDto.getQuantity());
		p.setPrice(productDto.getPrice());
		return p; 
	}

	public ProductDto toDto(Product productDto) {
		ProductDto p= new ProductDto();
		p.setId(productDto.getId());
		p.setCategory(productDto.getCategory());
		p.setDescription(productDto.getDescription());
		p.setName(productDto.getName());
		p.setQuantity(productDto.getQuantity());
		p.setPrice(productDto.getPrice());
		p.setTimeStamp(productDto.getTimeStamp());
		return p; 
	}
	
	public List<Product> toModel(List<ProductDto> list){
		return list.stream().map(p-> toModel(p)).collect(Collectors.toList()) ;
		
	}
	
	public List<ProductDto> toDto(List<Product> list){
		return list.stream().map(p->toDto(p)).collect(Collectors.toList());
	}
	
}
