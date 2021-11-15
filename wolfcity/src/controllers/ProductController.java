package controllers;

import models.Product;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles adding, modifying, and deleting products in the system
 */
public class ProductController {
    /** Database connection */
    private static Connection connection;

    /**
     * Creates a ProductController object
     * @param connection connection
     * @throws SQLException e
     */
    public ProductController(Connection connection) throws SQLException {
        ProductController.connection = connection;
    }

    /**
     * Creates a new product in the system
     * @param product product
     * @throws SQLException e
     */
    public void enterProductInformation(Product product) throws SQLException {
        String query = "INSERT INTO Product (productName, supplierID, price, buyPrice) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getSupplierID());
        preparedStatement.setFloat(3, product.getPrice());
        preparedStatement.setFloat(4, product.getBuyPrice());
        preparedStatement.execute();
    }

    /**
     * Update a product's information in the system
     * @param product product
     * @throws SQLException e
     */
    public void updateProductInformation(Product product) throws SQLException {
        String query = "UPDATE Product set productName = ?, supplierID = ?, price = ?, buyPrice = ? WHERE productID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getSupplierID());
        preparedStatement.setFloat(3, product.getPrice());
        preparedStatement.setFloat(4, product.getBuyPrice());
        preparedStatement.setInt(5, product.getProductID());
        preparedStatement.execute();
    }

    /**
     * Delete a product's entry in the system
     * @param productID product id
     * @throws SQLException e
     */
    public void deleteProductInformation(int productID) throws SQLException {
        String query = "DELETE FROM StoreInventory WHERE inventoryID IN (SELECT inventoryID FROM Inventory WHERE productID = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();

        query = "DELETE FROM WarehouseInventory WHERE inventoryID IN (SELECT inventoryID FROM Inventory WHERE productID = ?);";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();

        query = "DELETE FROM Inventory WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();

        query = "DELETE FROM ShipmentProducts WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();


        query = "DELETE FROM TransactionProducts WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();

        query = "DELETE FROM AppliesTo WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();

        query = "DELETE FROM Discount WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();



        query = "DELETE FROM Transfers WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();


        query = "DELETE FROM Product WHERE productID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        preparedStatement.execute();
    }

    /**
     * Print the list of products in the system
     * @throws SQLException e
     */
    public void printProductList() throws SQLException {
        String query = "SELECT * FROM Product;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
