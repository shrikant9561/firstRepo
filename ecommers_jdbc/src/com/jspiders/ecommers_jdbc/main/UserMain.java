package com.jspiders.ecommers_jdbc.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.jspiders.ecommers_jdbc.operations.UserOperation;

public class UserMain {

    private static UserOperation userOperation = new UserOperation();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println(
                    "ENTER 1 FOR ADD USER \nENTER 2 FOR LOG IN \nEnter 3 to FIND ALL USERS \nENTER 4 FOR UPDATE USER \nENTER 5 FOR DELETE USER\nENTER 6 FOR EXIT.");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        userOperation.addUser(scanner);
                        break;
                    case 2:
                        userOperation.logIn(scanner);
                        break;
                    case 3:
                        userOperation.findAllUsers();
                        break;
                    case 4:
                        userOperation.UpdateUsers(scanner);
                        break;
                    case 5:
                        userOperation.deleteUsers(scanner);
                        break;
                    case 6:
                        System.out.println("Thank you");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
        scanner.close();
    }
}
