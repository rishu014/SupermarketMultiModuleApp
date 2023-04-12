package com.nagarro.supermarket.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.nagarro.supermarket.dto.OrderDto;
import com.nagarro.supermarket.model.Order;

public class OrderMapper {
	
	private OrderItemMapper orderItemMapper;
	
	public OrderMapper() {
		this.orderItemMapper=new OrderItemMapper();
		
	}
	
	
	
	public Order toModel(OrderDto orderDto) {
		Order order=new Order();
		order.setId(orderDto.getId());
		order.setOrderItems(orderItemMapper.toModel(orderDto.getOrderItems()));
		order.setStatus(orderDto.getStatus());
		order.setTotal(orderDto.getTotal());
		return order;
	}
	
	public OrderDto toDto(Order order) {
		OrderDto orderDto=new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setOrderItems(orderItemMapper.toDto(order.getOrderItems()));
		orderDto.setStatus(order.getStatus());
		orderDto.setTotal(order.getTotal());
		return orderDto;
	}
	
	public List<Order> toModel(List<OrderDto> orderDtolist){
		return orderDtolist.stream().map(o->toModel(o)).collect(Collectors.toList());
	}
	
	
	public List<OrderDto> toDto(List<Order> orderList ){
		return orderList.stream().map(o->toDto(o)).collect(Collectors.toList());
	}


}
