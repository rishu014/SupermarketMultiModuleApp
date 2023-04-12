package com.nagarro.SupermarketApp.console;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * this class will provide the console for orders and product.
 * 
 * @author rishabhgusain 
 */
public class AdminConsole {

	private ProductConsole pc;
	private OrderConsole oc;
	private Scanner sc;

	public AdminConsole() {

		this.pc = new ProductConsole();
		this.oc = new OrderConsole();
		this.sc = new Scanner(System.in);

	}
	

	
	/**
	 * AdminJohn can update the order status pending to confirm
	 * AdminJohn also can update the product details
	 */
	public void showAdminDashboard() {
		boolean isRun = true;
		while (isRun) {
			try {
				System.out.println("Enter your choice : ");
				System.out.println("1. Product");
				System.out.println("2. Order ");
				switch (sc.nextInt()) {
					case 1:
						pc.productConsole();
						break;
					case 2:
						oc.updateOrderStatus();
						break;
					case 3:
						isRun = false;
						break;
					default:
						System.out.println("Invalid choice, please try again.");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid choice.");
				sc.nextLine(); // clear the input stream
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}
	}
}
