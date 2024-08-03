package com.jspider.ecommers.operation;

import java.util.List;
import java.util.Scanner;

import com.jspider.ecommers.collection.UserCollection;
import com.jspider.ecommers.entity.User;

public class UserOperation {

	private static UserCollection userCollection = new UserCollection();

	public void addUser(Scanner scanner) {

		System.out.println("Enter user id");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter user name");
		String name = scanner.nextLine();
		System.out.println("Enter user email");
		String email = scanner.nextLine();
		System.out.println("Enter user mobile number");
		long mobile = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Enter user password");
		String password = scanner.nextLine();
		System.out.println("Enter user Role");
		String Role=scanner.nextLine();
		User user = new User(id, name, email, mobile, password,Role );
		List<User> users = userCollection.getUsers();
		users.add(user);
		System.out.println("User added");

	}

	public void findAllUsers() {
		List<User> users = userCollection.getUsers();
		if (users.size() > 0) {
			for (User user : users) {
				System.out.println(user);
			}
		} else {
			System.out.println("Users not found");
		}
	}

	public void logIn(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Enter user email");
		String email = scanner.nextLine();
		System.out.println("Enter user password");
		String password = scanner.nextLine();
		List<User> users = userCollection.getUsers();
		boolean flag = false;
		for (User user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("User found");
		} else {
			System.out.println("User not found");
		}
	}

	public void deleteUsers(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter user id ");
		int id = scanner.nextInt();
		List<User> users = userCollection.getUsers();
		// User UserToDelete=null;
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				System.err.println("User Deleted");
				break;
			}
		}

	}

	public void UpdateUsers(Scanner scanner) {

		System.out.println("Enter id to edit");
		int id = scanner.nextInt();
		List<User> users = userCollection.getUsers();
		//User userToBeUpdated = null;
		for (User user : users) {
			if (user.getId() == id) {
				System.out.println("Enter what you Want to edit:\n" + "1.First Name\n" + "2.Email\n" + "3.Mobile No\n"
						+ "4.Password");
				Scanner sc = new Scanner(System.in);
				int option = sc.nextInt();
				scanner.nextLine();
				switch (option) {
				case 1:
					System.out.println("Enter new First Name: ");
					String newFirstName = scanner.nextLine();
					user.setName(newFirstName);
					System.out.println("Contact Edited Successfully....");

					break;
				case 2:
					System.out.println("Enter new Email: ");
					String newEmail = scanner.nextLine();
					user.setEmail(newEmail);
					System.out.println("Contact Edited Successfully....");
					break;
				case 3:
					System.out.println("Enter                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          zznew Contact No: ");
					long newContact = scanner.nextLong();
					user.setMobile(newContact);
					System.out.println("Contact Edited Successfully....");

					break;
				case 4:
					System.out.println("Enter new Password: ");
					long newPassword = scanner.nextLong();
					user.setMobile(newPassword);
					System.out.println("Contact Edited Successfully....");
					break;
				default:
					System.out.println("Invalid Input");

					break;
				}
			}
		}

	}

}