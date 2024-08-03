package com.jspider.ecommers.main;

import java.util.Scanner;

import com.jspider.ecommers.operation.UserOperation;

public class UserMain {

	private static UserOperation userOperation = new UserOperation();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.err.println(
					" ENTER 1 FOR ADD USER \n ENTER 2 FOR LOG IN \n Enter 3 to FIND ALL USERS \n ENTER 4 FOR UPDATE USER \n ENTER 5 FOR DELETE USER\n ENTER 6 FOR EXIT.");
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
				System.out.println("Invalid choice");
			}
		}
		scanner.close();

	}

}