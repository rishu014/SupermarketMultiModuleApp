package com.nagarro.SupermarketApp.console;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.nagarro.SupermarketApp.controller.OrderController;
import com.nagarro.SupermarketApp.controller.ProductController;
import com.nagarro.supermarket.dto.OrderDto;
import com.nagarro.supermarket.dto.OrderItemDto;
import com.nagarro.supermarket.dto.ProductDto;

/**
 * @author rishabhgusain
 *
 */
public class OrderConsole {

	private OrderController oc;
	private ProductConsole pc;
	private ProductController prc;
	private Scanner sc;

	public OrderConsole() {
		this.oc = new OrderController();
		this.sc = new Scanner(System.in);
		this.pc = new ProductConsole();
		this.prc = new ProductController();
	}
	
	/**
	 * Order products console for Guest
	 */
	public void orderConsole() {
		boolean isRun = true;
		while (isRun) {
			try {
				System.out.println("Please Select a Option");
				System.out.println("1. Place an Order");
				System.out.println("2. Delete Order");
				System.out.println("3. Track all Orders");
				System.out.println("4. Track order by Id");
				System.out.println("5. Exit");

				int choice = sc.nextInt();
				if (choice == 5) {
					isRun = false;
				} else {
					chooseOrderOption(choice);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer option.");
				sc.nextLine(); // consume the invalid input
			}
		}
	}
	
	//performing tasks for order
	private void chooseOrderOption(int choice) {
		switch (choice) {
		case 1:
			inputOrderDetails();
			break;
		case 2:
			deleteOrderDetails();
			break;

		case 3:
			getOrderDetails();
			break;
		case 4:
			getOrderDetailsById();
			break;
		default:
			System.out.println("Invalid input. Please enter a valid integer option.");
			break;
		}

	}
	
	//to delete order 
	// status is already confirmed and user delete the order it will change product Quantity
	private void deleteOrderDetails() {
		try {
	        getOrderDetails();
	        System.out.println("Enter the Order id you want to delete");
	        OrderDto o = oc.getOrder(sc.nextLong());
	        if (o == null) {
	            System.out.println("Invalid Order Id");
	            return;
	        }

	        if (o.getStatus().equalsIgnoreCase("confirmed")) {
	            updateProductsOnDelete(o);
	        }

	        oc.deleteOrder(o.getId());
	        System.out.println("Order Deleted Successfully");
	    } catch (Exception e) {
	        System.out.println("An error occurred while deleting order: " + e.getMessage());
	    }

	}
	
	//to track the single order
	private void getOrderDetailsById() {
		try {
	        System.out.println("Enter the Order id you want to check : ");
	        Long id = sc.nextLong();
	        OrderDto order = oc.getOrder(id);
	        if (order == null) {
	            System.out.println("Invalid orderId");
	        } else {
	            showOrder(order);
	        }
	    } catch (Exception e) {
	        System.out.println("An error occurred while getting order details: " + e.getMessage());
	    }

	}
	
	//view all orders
	private void getOrderDetails() {
		try {
	        List<OrderDto> orders = oc.getOrders();
	        orders.forEach(order -> showOrder(order));
	    } catch (Exception e) {
	        System.out.println("An error occurred while getting order details: " + e.getMessage());
	    }
	}
	
	
	//print Order Details 
	private void showOrder(OrderDto order) {

		System.out.println("----------- Order:" + order.getId() + " -------------------");
		System.out.println("            Product Details ");
		System.out.println("Id\tProductName\tQty.\tPrice");
		order.getOrderItems().forEach(orderId -> {
			ProductDto product = orderId.getProductDto();
			System.out.println(product.getId() + "\t" + product.getName() + "\t\t" + orderId.getQuantity() + "\t"
					+ product.getPrice());
		});
		System.out.println("Total Amount: " + order.getTotal());
		System.out.println("Order Status: " + order.getStatus());
		System.out.println("----------------------------------------");

	}
	
	//to place an Order
	private void inputOrderDetails() {
	    OrderDto Or = new OrderDto();
	    List<OrderItemDto> listOfOrder = new ArrayList<>();
	    try {
	        System.out.println("List Of All Available Products:");
	        List<ProductDto> products = prc.getProductDtos();
	        pc.showProduct(products);
	        Boolean isRun = true;
	        while (isRun) {
	            OrderItemDto o = new OrderItemDto();
	            System.out.println("Enter the Product Id You want to add : ");
	            Long id = sc.nextLong();
	            ProductDto p = checkExist(id, products);
	            if (!Objects.isNull(p)) {
	                o.setProductDto(p);
	            } else {
	                System.out.println("Invalid ProductId");
	                continue;
	            }
	            System.out.println("Enter the Quantity : ");
	            Integer quantity = sc.nextInt();
	            if (quantity <= p.getQuantity()) {
	                o.setQuantity(quantity);
	            } else {
	                System.out.println("Unavailable Quantity Exceed");
	                continue;
	            }
	            listOfOrder.add(o);
	            System.out.println("Do you want to add other product y/n");
	            String option = sc.next();
	            if (option.equalsIgnoreCase("n")) {
	                isRun = false;
	            }
	        }

	        Or.setOrderItems(listOfOrder);
	        Double totalAmount = 0.0;
	        for (OrderItemDto orderItem : listOfOrder)
	            totalAmount += (orderItem.getProductDto().getPrice() * orderItem.getQuantity());
	        System.out.println("Total Amount of the order is : " + totalAmount + "\n Press y to Confirm, n to Cancel ");

	        if (sc.next().equalsIgnoreCase("n")) {
	            throw new Exception("Order cancelled by user");
	        } else {
	            Or.setTotal(totalAmount);
	            Or.setStatus("Pending");
	            oc.addOrder(Or);
	            System.out.println("order successfully done");
	            System.out.println("status will update soon");
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	ProductDto checkExist(Long id, List<ProductDto> products) throws Exception {
	    ProductDto product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	    if (Objects.isNull(product)) {
	        throw new Exception("Invalid ProductId");
	    }
	    return product;
	}

	//to update the order status confirmed or rejected by the user
	public void updateOrderStatus() {
		getOrderDetails();
		System.out.println("Enter order id to update order status : ");
		OrderDto order = oc.getOrder(sc.nextLong());
		if (Objects.isNull(order)) {
			System.out.println("Invalid Order Id");
		} else {
			System.out.println("Select status: ");
			System.out.println("1. Confirm");
			System.out.println("2. Reject");
			switch (sc.nextInt()) {
			case 1:
				if (!order.getStatus().equalsIgnoreCase("Confirmed")) {

					order.setStatus("Confirmed");
					updateProducts(order);
				} else {
					System.out.println("Order is Already Confirmed");
				}
				break;
			case 2:
				order.setStatus("Rejected");
				break;

			default:
				break;
			}
		}
	}
	
	//update product quantity on Order confirmation 

	private void updateProducts(OrderDto order) {
		List<ProductDto> list = prc.getProductDtos();
		order.getOrderItems().forEach(orderItem -> {
			Long productId = orderItem.getProductDto().getId();
			ProductDto pr = list.stream().filter(product -> product.getId() == productId).findFirst().orElse(null);
			Integer Quantity = pr.getQuantity() - orderItem.getQuantity();
			if (Quantity >= 0) {
				pr.setQuantity(Quantity);
				prc.updateProductDto(pr);
				oc.updateOrder(order);
			} else {
				System.out.println("Quantity is not available");
			}
		});
	}
	
	//changing product quantity according on Order deletion
	private void updateProductsOnDelete(OrderDto order) {
		List<ProductDto> list = prc.getProductDtos();
		order.getOrderItems().forEach(orderItem -> {
			Long productId = orderItem.getProductDto().getId();
			ProductDto pr = list.stream().filter(product -> product.getId() == productId).findFirst().orElse(null);
			Integer Quantity = pr.getQuantity() + orderItem.getQuantity();

			pr.setQuantity(Quantity);
			prc.updateProductDto(pr);

		});
	}

}
