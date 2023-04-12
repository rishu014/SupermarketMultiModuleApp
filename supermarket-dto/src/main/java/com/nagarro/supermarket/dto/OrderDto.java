package com.nagarro.supermarket.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	
    private Long id;
    private List<OrderItemDto> orderItems;
    private Double total;
    private String status;
}


