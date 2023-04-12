package com.nagarro.supermarket.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String category;
	private Double price;
	private Integer quantity;
	private Boolean outOfStock;
	private LocalDateTime timeStamp=LocalDateTime.now();

	public void setQuantity(Integer quantity) {
		if (quantity >= 0) {
			this.quantity = quantity;
			if (this.quantity > 0)
				setOutOfStock(false);
			else
				setOutOfStock(true);
		}
	}

}
