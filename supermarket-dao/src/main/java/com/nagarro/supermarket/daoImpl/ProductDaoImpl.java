package com.nagarro.supermarket.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.SupermarketApp.util.HibernateUtil;
import com.nagarro.supermarket.dao.ProductDao;
import com.nagarro.supermarket.model.Product;

public class ProductDaoImpl implements ProductDao {

	private Session session;

	public ProductDaoImpl() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void addProduct(Product product) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.persist(product);
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw new RuntimeException("Error adding product", e);
		}
	}

	@Override
	public void updateProduct(Product product) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Product p = session.get(Product.class, product.getId());
			if (p != null) {
				if (product.getName() != null) {
					p.setName(product.getName());
				}
				if (product.getDescription() != null) {
					p.setDescription(product.getDescription());
				}
				if (product.getCategory() != null) {
					p.setCategory(product.getCategory());
				}
				if (product.getQuantity() != null) {
					p.setQuantity(product.getQuantity());
				}
				if (product.getPrice() != null) {
					p.setPrice(product.getPrice());
				}
			}
			session.merge(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error updating product", e);
		}
	}

	@Override
	public void deleteProduct(Long id) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Product p = session.get(Product.class, id);
			session.remove(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error deleting product", e);
		}
	}

	@Override
	public List<Product> getProducts() {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Product> productList = session.createQuery("from Product order by timeStamp desc", Product.class)
					.getResultList();
			tx.commit();
			return productList;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error getting products", e);
		}
	}

	@Override
	public Product getProduct(Long id) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Product p = session.get(Product.class, id);
			tx.commit();
			return p;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error getting product", e);
		}
	}

	@Override
	public List<Product> sortProductByCategory(String category) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Product> productList = session
					.createQuery("from Product where category='" + category + "' order by timeStamp desc",
							Product.class)
					.getResultList();
			tx.commit();
			return productList;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error sorting products by category", e);
		}
	}

	@Override
	public List<Product> sortProductByName(String name) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Product> productList = session
					.createQuery("from Product where name='" + name + "' order by timeStamp desc", Product.class)
					.getResultList();
			tx.commit();
			return productList;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error sorting products by name", e);
		}

	}

	@Override
	public List<Product> sortProductByDescription(String description) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Product> productList = session
					.createQuery("from Product where description like '%" + description + "%' order by timeStamp desc",
							Product.class)
					.getResultList();
			tx.commit();
			return productList;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error sorting products by description", e);
		}

	}
}
