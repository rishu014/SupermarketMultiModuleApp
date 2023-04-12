package com.nagarro.supermarket;

import java.util.Scanner;

import com.nagarro.SupermarketApp.console.LoginConsole;
import com.nagarro.SupermarketApp.console.OrderConsole;


public class App {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in)) {
			OrderConsole oc = new OrderConsole();
			LoginConsole lc = new LoginConsole();
			//Console for Login as Admin or Guest
			System.out.println("Please select your profile");
			System.out.println("1) Login as Admin");
			System.out.println("2) Login as Guest");

			switch (sc.nextInt()) {
			case 1:
				lc.showAdminConsole();
				break;
			case 2:
				oc.orderConsole();
				break;

			default:
				break;

			}
		}
	}
}
