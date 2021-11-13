package controllers;

import models.Supplier;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierController {

    private static Connection connection;
    private static ProductController productController;

    public SupplierController(Connection connection) throws SQLException {
        SupplierController.connection = connection;
        productController = new ProductController(connection);
    }

    public void enterSupplierInformation(Supplier supplier) throws SQLException {
        String query = "INSERT INTO Supplier (email, phoneNumber, address, name) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, supplier.getEmail());
        preparedStatement.setString(2, supplier.getPhoneNumber());
        preparedStatement.setString(3, supplier.getAddress());
        preparedStatement.setString(4, supplier.getName());
        preparedStatement.execute();
    }

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

    public void printSupplierList() throws SQLException {
        String query = "SELECT * FROM Supplier;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
