package com.nagarro.SupermarketApp.console;

import java.util.List;
import java.util.Scanner;

import com.nagarro.SupermarketApp.controller.ProductController;
import com.nagarro.supermarket.dto.ProductDto;

/**
 * @author rishabhgusain
 * This class provides a console interface for managing products
 */
public class ProductConsole {
	private Scanner sc;

	private ProductController pc;
	

	public ProductConsole() {
		this.pc = new ProductController();
		this.sc = new Scanner(System.in);
	}

	/**
	 * Displays a menu of options and accepts user input to perform various operations on products.
	 */
	public void productConsole() {
		boolean isRun = true;
		while (isRun) {

			System.out.println("Please Select a Option:");
			System.out.println("1) Add Product");
			System.out.println("2) Delete Product");
			System.out.println("3) Update Product");
			System.out.println("4) Get Product ");
			System.out.println("5) Get Product by Id");
			System.out.println("6) Update Product stock ");
			System.out.println("7) Filter Product ");
			System.out.println("8) Exit");

			int choice = sc.nextInt();
			if (choice == 8) {
				isRun = false;
				
			} else {
				chooseProductOption(choice);
			}

		}

	}
	
	/**
	 * Handles user input for different product management options.
	 * @param choice The user's choice of operation.
	 */
	private void chooseProductOption(int choice) {
		switch (choice) {
		case 1:
			inputProductDetails();
			break;
		case 2:
			deleteProductDetails();
			break;
		case 3:
			updateProductDetails();
			break;
		case 4:
			getProductDetails();
			break;
		case 5:
			getProductDetailsById();
			break;
		case 6:
			updateProductStock();
			break;
		case 7:
			sortProduct();
			break;
		default:
			
			break;
		}

	}
	
	/**
	 * Giving options for filtering
	 */
	private void sortProduct() {
		System.out.println(" Select options to filter:  ");
		System.out.println("1.Category");
		System.out.println("2.Description");
		System.out.println("3.Name");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			sortProductByCategory();
			break;
		case 2:
			sortProductByDesc();
			break;
		case 3:
			sortProductByName();
			break;
		default:
			break;
		}
	}

	/**
	 * filter product by category
	 */
	private void sortProductByCategory() {

		System.out.println("Enter the Product Category to filter:  ");
		System.out.println("1.Health");
		System.out.println("2.Food");
		System.out.println("3.Cosmetic");
		System.out.println("4.Households");
		System.out.println("5.others");
		String category = sc.next();
		List<ProductDto> list = pc.sortProductDtoByCategory(category);
		showProduct(list);

	}
	
	/**
	 * filter product by name
	 */
	private void sortProductByName() {

		System.out.println("Enter the Product Name to filter:  ");
		String name = sc.next();
		List<ProductDto> list = pc.sortProductDtoByName(name);
		showProduct(list);

	}
	
	/**
	 * filter product by details
	 */
	private void sortProductByDesc() {

		System.out.println("Enter the Product Desc to filter:  ");
		String description = sc.next();
		List<ProductDto> list = pc.sortProductDtoByDescription(description);
		showProduct(list);

	}
	
	/**
	 * update a product stock with quantity
	 */
	private void updateProductStock() {

		System.out.println("Enter Product id to update:");
		Long id = sc.nextLong();
		showProduct(pc.getProductDto(id));

		ProductDto p = new ProductDto();
		p.setId(id);
		Boolean isRun = true;
		while (isRun) {
			System.out.println("Enter Product Quantity to update: ");

			System.out.println("1) Quantity");
			System.out.println("2) Confirm");
			System.out.println("3) Cancel");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println("Enter Product Quantity: ");
				p.setQuantity(sc.nextInt());
				break;
			case 2:
				isRun = false;
				pc.updateProductDto(p);
				break;
			case 3:
				isRun = false;
				break;
			default:
				break;
			}
		}

	}

	/**
	 * it will show the product details with id
	 */
	private void getProductDetailsById() {
		System.out.println("Enter Product Id to View:");
		showProduct(pc.getProductDto(sc.nextLong()));
	}

	/**
	 * update a product
	 */
	private void updateProductDetails() {
		System.out.println("Enter Product id to update:");
		Long id = sc.nextLong();
		showProduct(pc.getProductDto(id));

		ProductDto p = new ProductDto();
		p.setId(id);
		Boolean isRun = true;
		while (isRun) {
			System.out.println("Enter Product Details to update: ");
			System.out.println("1) Name");
			System.out.println("2) Description");
			System.out.println("3) Category");
			System.out.println("4) Quantity");
			System.out.println("5) Price");
			System.out.println("6) Confirm");
			System.out.println("7) Cancel");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter Product Name: ");
				p.setName(sc.next());
				break;
			case 2:
				System.out.println("Enter Product Description: ");
				p.setDescription(sc.next());
				break;
			case 3:
				System.out.println("Enter Product Category: ");
				System.out.println("1.Health");
				System.out.println("2.Food");
				System.out.println("3.Digital");
				System.out.println("4.Households");
				System.out.println("5.others");
				p.setCategory(sc.next());
				break;
			case 4:
				System.out.println("Enter Product Quantity: ");
				p.setQuantity(sc.nextInt());
				break;
			case 5:
				System.out.println("Enter Product Price: ");
				p.setPrice(sc.nextDouble());
				break;
			case 6:
				isRun = false;
				pc.updateProductDto(p);
				break;
			case 7:
				isRun = false;
				break;
			default:
				break;
			}
		}

	}

	/**
	 * delete a product
	 */
	private void deleteProductDetails() {
		System.out.println("Enter Product Id to delete: ");
		Long delId = sc.nextLong();
		pc.deleteProductDto(delId);
	}
	
	
	/**
	 * to get all products
	 */
	public void getProductDetails() {
		List<ProductDto> productList = pc.getProductDtos();
		showProduct(productList);
	}

	
	/**
	 * to add a product
	 */
	private void inputProductDetails() {
	    ProductDto p = new ProductDto();
	    try {
	        System.out.println("Enter Product Details: ");
	        System.out.println("Enter Product Name: ");
	        p.setName(sc.next());

	        System.out.println("Enter Product Description: ");
	        p.setDescription(sc.next());

	        System.out.println("Enter Product Category: ");
	        System.out.println("1.Health");
	        System.out.println("2.Food");
	        System.out.println("3.Digital");
	        System.out.println("4.Households");
	        System.out.println("5.others");
	        String category = sc.next();

	        if (category.matches("\\d+")) {
	            int categoryNumber = Integer.parseInt(category);
	            switch (categoryNumber) {
	                case 1:
	                    p.setCategory("Health");
	                    break;
	                case 2:
	                    p.setCategory("Food");
	                    break;
	                case 3:
	                    p.setCategory("Digital");
	                    break;
	                case 4:
	                    p.setCategory("Households");
	                    break;
	                case 5:
	                    p.setCategory("Others");
	                    break;
	                default:
	                    throw new Exception("Invalid category number entered!");
	            }
	        } else {
	            throw new Exception("Invalid category entered!");
	        }

	        System.out.println("Enter Product Quantity: ");
	        p.setQuantity(sc.nextInt());

	        System.out.println("Enter Product Price: ");
	        p.setPrice(sc.nextDouble());

	        pc.addProductDto(p);
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}
	
	//
	/**
	 * to print single product
	 * @param p
	 */
	public void showProduct(ProductDto p) {
		System.out.println("----------------------------");
		System.out.println("Id\tName\tDescription\tCategory\tQty.\tPrice\tTimeStamp");
		System.out
				.println(p.getId() + "\t" + p.getName() + "\t" + p.getDescription() + "\t\t" + p.getCategory() + "\t\t"
						+ p.getQuantity() + "\t" + p.getPrice()+"\t"+ p.getTimeStamp() );


	}
	
	/**
	 * to iterate and get each product and show list
	 * @param products
	 */
	public void showProduct(List<ProductDto> products) {
		System.out.println("Id\tName\tDescription\tCategory\tQty.\tPrice\tTimestamp");
		products.forEach(p -> {
			System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getDescription() + "\t\t" + p.getCategory()
					+ "\t\t" + p.getQuantity() + "\t" + p.getPrice()  + "\t"
					+ p.getTimeStamp());
		});
	}

}
