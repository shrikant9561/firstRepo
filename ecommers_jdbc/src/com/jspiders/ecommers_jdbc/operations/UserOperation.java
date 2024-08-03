package com.jspiders.ecommers_jdbc.operations;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.jspiders.ecommers_jdbc.main.ProductMain;

public class UserOperation {

	private static Driver driver;
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public void addUser(Scanner scanner) {
		try {
			System.out.println("Enter your id");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter name:");
			String name = scanner.nextLine();

			System.out.println("Enter email:");
			String email = scanner.nextLine();
			System.out.println("Enter password:");
			String password = scanner.nextLine();
			System.out.println("Enter 1 for SELLER \nEnter 2 for BUYER");
			int choice = scanner.nextInt();
			String role = null;
			switch (choice) {
			case 1:
				role = "SELLER";
				break;
			case 2:
				role = "BUYER";
				break;
			default:
				System.out.println("Invalid choice");
				return;
			}

			openConnection();
			String query = "INSERT INTO user ( id ,name, email, password, role) VALUES (?,?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, role);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User added successfully!");
			} else {
				System.out.println("Failed to add user.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input type. Please try again.");
			scanner.nextLine();  // Clear the invalid input from the scanner
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void logIn(Scanner scanner) {
		try {
			System.out.println("Enter user email");
			scanner.nextLine();
			String email = scanner.nextLine();
			System.out.println("Enter user password");
			String password = scanner.nextLine();

			openConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String role = resultSet.getString("role");
				if ("SELLER".equalsIgnoreCase(role)) {
					System.out.println("************** Seller login successful **************");
					ProductMain.sallerLogin();

				} else if ("BUYER".equalsIgnoreCase(role)) {
					System.out.println(" *********** Buyer login successful **************");
					ProductMain.buyerLogin(scanner);

				} else {
					System.out.println("Unknown role");
				}
			} else {
				System.out.println("User not found");
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input type. Please try again.");
			scanner.nextLine();  
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void findAllUsers() {
		try {
			openConnection();
			String query = "SELECT id, name, email, role FROM users";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String role = resultSet.getString("role");
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Role: " + role);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void UpdateUsers(Scanner scanner) {
		try {
			System.out.println("Enter the ID of the user you want to update:");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("What do you want to update? ( \n 1:- name \n 2:- email \n 3:- password \n 4:- role");
			int choice = scanner.nextInt();
			scanner.nextLine();
			String newValue;
			switch (choice) {
			case 1:
				System.out.println("Enter new name:");
				newValue = scanner.nextLine();
				updateUserField("name", newValue, id);
				break;
			case 2:
				System.out.println("Enter new email:");
				newValue = scanner.nextLine();
				updateUserField("email", newValue, id);
				break;
			case 3:
				System.out.println("Enter new password:");
				newValue = scanner.nextLine();
				updateUserField("password", newValue, id);
				break;
			case 4:
				System.out.println("Enter new role:");
				newValue = scanner.nextLine();
				updateUserField("role", newValue, id);
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input type. Please try again.");
			scanner.nextLine();  // Clear the invalid input from the scanner
		}
	}

	public void deleteUsers(Scanner scanner) {
		try {
			System.out.println("Enter user ID to delete:");
			int id = scanner.nextInt();

			openConnection();
			String query = "DELETE FROM users WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User deleted successfully!");
			} else {
				System.out.println("Failed to delete user.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input type. Please try again.");
			scanner.nextLine();  // Clear the invalid input from the scanner
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateUserField(String field, String newValue, int id) {
		try {
			openConnection();
			String query = "UPDATE users SET " + field + " = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newValue);
			preparedStatement.setInt(2, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User updated successfully!");
			} else {
				System.out.println("Failed to update user.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecom_jdbc?user=root&password=root");
	}

	private static void closeConnection() throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
		if (driver != null) {
			DriverManager.deregisterDriver(driver);
		}
	}
}
