package com.nagarro.supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
	
	private Long id;
	private ProductDto productDto;
	private Integer quantity;
	
	

}
