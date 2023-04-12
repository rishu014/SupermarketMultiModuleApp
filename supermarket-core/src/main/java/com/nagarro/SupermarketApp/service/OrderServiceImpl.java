package com.nagarro.SupermarketApp.service;

import java.util.List;

import com.nagarro.supermarket.dao.OrderDao;
import com.nagarro.supermarket.daoImpl.OrderDaoImpl;
import com.nagarro.supermarket.model.Order;

/**
 * @author rishabhgusain
 *
 */
public class OrderServiceImpl implements OrderService {
	
	private OrderDao od;
	public OrderServiceImpl() {
		this.od=new OrderDaoImpl();
	}

	@Override
	public void updateOrder(Order order) {
		od.updateOrder(order);

	}

	@Override
	public void deleteOrder(Long id) {
		od.deleteOrder(id);

	}

	@Override
	public void addOrder(Order order) {
		od.addOrder(order);

	}

	@Override
	public List<Order> getOrders() {
		return  od.getOrders();
	}

	@Override
	public Order getOrder(Long id) {
		return od.getOrder(id);
	}

}
