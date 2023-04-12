package com.nagarro.SupermarketApp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.supermarket.model.Order;
import com.nagarro.supermarket.model.OrderItem;
import com.nagarro.supermarket.model.Product;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {

			Configuration configuration = new Configuration();
			configuration.configure().addAnnotatedClass(Order.class).addAnnotatedClass(OrderItem.class)
					.addAnnotatedClass(Product.class);
		
			sessionFactory =  configuration.buildSessionFactory();
		}
		return sessionFactory;
	}

}
