package com.nagarro.supermarket.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.nagarro.supermarket.dto.OrderItemDto;
import com.nagarro.supermarket.model.OrderItem;

public class OrderItemMapper {
	
	private ProductMapper pm;
	
	public OrderItemMapper() {
		this.pm=new ProductMapper();
		
	}
	
	public OrderItem toModel(OrderItemDto orderItemDto) {
		OrderItem orderItem =new OrderItem();
		orderItem.setId(orderItemDto.getId());
		orderItem.setProduct(pm.toModel(orderItemDto.getProductDto()));
		orderItem.setQuantity(orderItemDto.getQuantity());
		return orderItem;
		
	}
	
	public OrderItemDto toDto (OrderItem orderItem) {
		OrderItemDto orderItemDto=new OrderItemDto();
		orderItemDto.setId(orderItem.getId());
		orderItemDto.setProductDto(pm.toDto(orderItem.getProduct()));
		orderItemDto.setQuantity(orderItem.getQuantity());
		return orderItemDto;
	}
	
	public List<OrderItem> toModel(List<OrderItemDto> list){
		return list.stream().map(p-> toModel(p)).collect(Collectors.toList()) ;
		
	}
	
	public List<OrderItemDto> toDto(List<OrderItem> list){
		return list.stream().map(p->toDto(p)).collect(Collectors.toList());
	}
}
