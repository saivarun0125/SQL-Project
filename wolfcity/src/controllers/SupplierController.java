package controllers;

import models.Supplier;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controls adding, updating and deleting supplier information in the system
 */
public class SupplierController {
    /** Database connection */
    private static Connection connection;
    /** Controls product operations */
    private static ProductController productController;

    /**
     * Constructs a SupplierController object from a database connection
     * @param connection connection
     * @throws SQLException e
     */
    public SupplierController(Connection connection) throws SQLException {
        SupplierController.connection = connection;
        productController = new ProductController(connection);
    }

    /**
     * Adds a new supplier into the system from user input
     * @param supplier supplier information
     * @throws SQLException e
     */
    public void enterSupplierInformation(Supplier supplier) throws SQLException {
        String query = "INSERT INTO Supplier (email, phoneNumber, address, name) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, supplier.getEmail());
        preparedStatement.setString(2, supplier.getPhoneNumber());
        preparedStatement.setString(3, supplier.getAddress());
        preparedStatement.setString(4, supplier.getName());
        preparedStatement.execute();
    }

    /**
     * Update a supplier's attributes
     * @param supplier supplier
     * @throws SQLException e
     */
    public void updateSupplierInformation(Supplier supplier) throws SQLException {
        String query = "UPDATE Supplier set email = ?, phoneNumber = ?, address = ?, name = ? WHERE supplierID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, supplier.getEmail());
        preparedStatement.setString(2, supplier.getPhoneNumber());
        preparedStatement.setString(3, supplier.getAddress());
        preparedStatement.setString(4, supplier.getName());
        preparedStatement.setInt(5, supplier.getSupplierID());
        preparedStatement.execute();
    }

    /**
     * Delete a supplier's information from the system
     * @param supplierID supplier id
     * @throws SQLException e
     */
    public void deleteSupplierInformation(int supplierID) throws SQLException {
        String query = "SELECT * FROM Product WHERE supplierID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, supplierID);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            int productID = set.getInt("productID");
            productController.deleteProductInformation(productID);
        }

        query = "DELETE FROM Bill WHERE supplierID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, supplierID);
        preparedStatement.execute();

        query = "DELETE FROM Supplier WHERE supplierID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, supplierID);
        preparedStatement.execute();
    }

    /**
     * Prints a list of all the suppliers currently in the system
     * @throws SQLException e
     */
    public void printSupplierList() throws SQLException {
        String query = "SELECT * FROM Supplier;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
