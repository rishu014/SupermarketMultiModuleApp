package com.nagarro.supermarket.dao;

import java.util.List;

import com.nagarro.supermarket.model.Order;
public interface OrderDao {
	
	
	void addOrder(Order order);
	
	void updateOrder(Order order);

	void deleteOrder(Long id);
	List<Order> getOrders();
	Order getOrder(Long id);


}
