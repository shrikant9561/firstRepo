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

public class ProductOperation {
    private static Driver driver;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public void addProduct(Scanner scanner) {
        try {
            System.out.println("Enter product name:");
            scanner.nextLine(); // Consume the newline
            String productName = scanner.nextLine();
            System.out.println("Enter product description:");
            String description = scanner.nextLine();
            System.out.println("Enter product price:");
            double price = scanner.nextDouble();
            System.out.println("Enter product quantity:");
            int quantity = scanner.nextInt();

            try {
                openConnection();
                String query = "INSERT INTO Product (ProductName, Description, Price, Quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, productName);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, price);
                preparedStatement.setInt(4, quantity);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Product added successfully!");
                } else {
                    System.out.println("Failed to add product.");
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
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.next(); // Clear the invalid input
        }
    }

    public void findAllProducts() {
        try {
            openConnection();
            String query = "SELECT * FROM Product";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Product List:");
            System.out.printf("%-5s \t %-20s %-50s %-10s %-10s%n", "ID", "ProductName", "Description", "Price", "Quantity");

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                System.out.printf("%-5d \t %-20s %-50s %-10.2f  \t %-10d%n", productID, productName, description, price, quantity);
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

    public void purchesProduct(Scanner scanner) {
        try {
            System.out.println("Enter product ID to purchase:");
            int productId = scanner.nextInt();
            System.out.println("Enter quantity to purchase:");
            int quantityToPurchase = scanner.nextInt();

            try {
                openConnection();

                // Check if the product exists and has enough quantity
                String checkQuery = "SELECT Quantity FROM Product WHERE ProductID = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
                checkStmt.setInt(1, productId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    int availableQuantity = rs.getInt("Quantity");

                    if (quantityToPurchase <= availableQuantity) {
                        // Update the quantity of the product in the database
                        String updateQuery = "UPDATE Product SET Quantity = Quantity - ? WHERE ProductID = ?";
                        PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                        updateStmt.setInt(1, quantityToPurchase);
                        updateStmt.setInt(2, productId);
                        int rowsAffected = updateStmt.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Purchase successful!");
                        } else {
                            System.out.println("Failed to complete the purchase.");
                        }
                    } else {
                        System.out.println("Not enough quantity available.");
                    }
                } else {
                    System.out.println("Product not found.");
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
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.next(); // Clear the invalid input
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
