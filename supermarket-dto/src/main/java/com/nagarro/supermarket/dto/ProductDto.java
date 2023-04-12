package com.nagarro.supermarket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Long id;
	private String name;
	private String description;
	private String category;
	private Double price;
	private Integer quantity;
	private LocalDateTime timeStamp;

}
