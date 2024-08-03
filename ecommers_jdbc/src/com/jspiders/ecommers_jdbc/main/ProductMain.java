package com.jspiders.ecommers_jdbc.main;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.jspiders.ecommers_jdbc.operations.ProductOperation;

public class ProductMain {
    private static ProductOperation productOperation = new ProductOperation();

    public static void main(String[] args) {
        sallerLogin();
    }

    public static void sallerLogin() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("======================================================================================");
            System.out.println("Enter 1 to add product \nEnter 2 to find all products \nEnter 3 to exit");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        productOperation.addProduct(scanner);
                        break;
                    case 2:
                        productOperation.findAllProducts();
                        break;
                    case 3:
                        flag = false;
                        System.out.println("Thank you");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }
        
    }

    public static void buyerLogin(Scanner scanner2) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("======================================================================================");
            System.out.println("Enter 1 to find all products \nEnter 2 to purchase product \nEnter 3 to exit");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        productOperation.findAllProducts();
                        break;
                    case 2:
                        productOperation.purchesProduct(scanner);
                        break;
                    case 3:
                        flag = false;
                        System.out.println("Thank you");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }
       
    }
}
