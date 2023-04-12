package com.nagarro.SupermarketApp.service;

import java.util.List;

import com.nagarro.supermarket.model.Order;

public interface OrderService {
	
	void updateOrder(Order order);
	void deleteOrder(Long id);
	void addOrder(Order order);
	List<Order> getOrders();
	Order getOrder(Long id);


}
