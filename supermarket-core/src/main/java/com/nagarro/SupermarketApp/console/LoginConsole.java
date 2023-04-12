package com.nagarro.SupermarketApp.console;

import java.util.Scanner;

/**
 * 
 * this class consist check login details
 *  
 * @author rishabhgusain
 *
 */

public class LoginConsole {

	private AdminConsole ac;
	private Scanner sc;

	
	public LoginConsole() {
		this.ac = new AdminConsole();
		this.sc = new Scanner(System.in);

	}
	
	
	/**
	 * Login credentials if successful it will show the admin console
	 */
	public void showAdminConsole() {
		try {
			System.out.println("Enter Username : ");
			String uname = sc.next();
			System.out.println("Enter Password : ");
			String pass = sc.next();

			if (uname.equals("Admin") && pass.equals("Admin")) {
				System.out.println("Logged in successfully");
				ac.showAdminDashboard();
			} else {
				System.out.println("Invalid Credentials");
				showAdminConsole();
			}
		} catch (Exception e) {
			System.out.println("An error occurred while trying to log in: " + e.getMessage());
		}
	}

}
