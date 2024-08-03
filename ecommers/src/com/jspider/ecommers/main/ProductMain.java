package com.jspider.ecommers.main;
import java.util.Scanner;

import com.jspider.ecommers.entity.User;
import com.jspider.ecommers.operation.ProductOperation;
public class ProductMain {
	private static ProductOperation productOperation = new ProductOperation();

	public static void main(User user) {

		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.err.println("======================================================================================");
			System.err.println("Enter 1 to add product \nEnter 2 to find all products \nEnter 6 to exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				if (user.getRole().equals("SELLER")) {
					productOperation.addProduct(scanner);
				} else {
					System.out.println("Unauthorized access");
				}
				break;
			case 2:
				productOperation.findAllProducts();
				break;
			case 6:
				flag = false;
				System.out.println("Thank you");
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
		scanner.close();
	}
}
