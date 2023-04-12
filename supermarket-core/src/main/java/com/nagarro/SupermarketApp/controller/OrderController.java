package com.nagarro.SupermarketApp.controller;

import java.util.List;

import com.nagarro.SupermarketApp.service.OrderService;
import com.nagarro.SupermarketApp.service.OrderServiceImpl;
import com.nagarro.supermarket.dto.OrderDto;
import com.nagarro.supermarket.mapper.OrderMapper;

/**
 * @author rishabhgusain
 *
 */
public class OrderController {
	
	private OrderService os;
	private OrderMapper om;
	
	public OrderController(){
		this.os= new OrderServiceImpl();
		this.om=new OrderMapper();
	}
	
	/**
	 * 
	 * @param orderDto
	 */
	public void updateOrder(OrderDto orderDto) {
		
		os.updateOrder(om.toModel(orderDto));
	}

	/**
	 * @param orderDto
	 */
	public void addOrder(OrderDto orderDto) {
		os.addOrder(om.toModel(orderDto));
	}

	/**
	 * @param id
	 */
	public void deleteOrder(Long id) {
		os.deleteOrder(id);
	}

	/**
	 * @return
	 */
	public List<OrderDto> getOrders() {
		return om.toDto(os.getOrders());
	}

	/**
	 * @param id
	 * @return
	 */
	public OrderDto getOrder(Long id) {
		return om.toDto(os.getOrder(id));
	}

}
