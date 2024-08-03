package com.jspider.ecommers.operation;
import java.util.List;
import java.util.Scanner;

import com.jspider.ecommers.collection.ProductCollection;
import com.jspider.ecommers.entity.Product;

public class ProductOperation {
	private static ProductCollection productCollection = new ProductCollection();

	public void addProduct(Scanner scanner) {

		System.out.println("Enter product id");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter product title");
		String title = scanner.nextLine();
		System.out.println("Enter product description");
		String description = scanner.nextLine();
		System.out.println("Enter product price");
		double price = scanner.nextDouble();
		System.out.println("Enter product sale status");
		String status = scanner.next();
		boolean sold = false;
		if (status.equals("true")) {
			sold = true;
		}
		Product product = new Product(id, title, description, price, sold);
		List<Product> products = productCollection.getProducts();
		products.add(product);
		System.out.println("Product added");
	}

	public void findAllProducts() {

		List<Product> products = productCollection.getProducts();
		if (products.size() > 0) {
			for (Product product : products) {
				System.out.println(product);
			}
		} else {
			System.out.println("Products not found");
		}
	}
}
