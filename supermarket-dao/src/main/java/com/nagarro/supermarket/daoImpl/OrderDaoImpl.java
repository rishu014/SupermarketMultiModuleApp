package com.nagarro.supermarket.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.SupermarketApp.util.HibernateUtil;
import com.nagarro.supermarket.dao.OrderDao;
import com.nagarro.supermarket.model.Order;

/**
 * @author rishabhgusain
 *
 */
public class OrderDaoImpl implements OrderDao {
	
	private Session session;
	
	public OrderDaoImpl() {
		this.session= HibernateUtil.getSessionFactory().openSession();
	}


	@Override
	public void addOrder(Order order) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.persist(order);
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void updateOrder(Order order) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.merge(order);
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteOrder(Long id) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Order o = session.get(Order.class, id);
			session.remove(o);
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Order> getOrders() {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			List<Order> orderList = session.createQuery("from Order", Order.class).getResultList(); 
			t.commit();
			return orderList;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Order getOrder(Long id) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Order o = session.get(Order.class, id);
			t.commit();
			return o;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

}
