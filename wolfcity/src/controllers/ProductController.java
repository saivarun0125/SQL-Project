package controllers;

import models.Product;
import utlities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductController {

    private static Connection connection;

    public ProductController(Connection connection) throws SQLException {
        ProductController.connection = connection;
    }

//    public void enterProductInformation(Product product) throws SQLException {
//        String query = "INSERT INTO Product (productID, percentOff, startDate, endDate) VALUES(?, ?, ?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, product.getProductID());
//        preparedStatement.setInt(2, product.getPercentOff());
//        preparedStatement.setTimestamp(3, product.getStartDate());
//        preparedStatement.setTimestamp(4, product.getEndDate());
//        preparedStatement.execute();
//    }
//
//    public void updateProductInformation(Product product) throws SQLException {
//        String query = "UPDATE Product set productID = ?, percentOff = ?, startDate = ?, endDate = ? WHERE productID = ?;";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, product.getProductID());
//        preparedStatement.setInt(2, product.getPercentOff());
//        preparedStatement.setTimestamp(3, product.getStartDate());
//        preparedStatement.setTimestamp(4, product.getEndDate());
//        preparedStatement.setInt(5, product.getProductID());
//        preparedStatement.execute();
//    }
//
//    public void deleteProductInformation(int productID) throws SQLException {
//        String query = "DELETE FROM Product WHERE productID = ?;";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, productID);
//        preparedStatement.execute();
//    }

    public void printProductList() throws SQLException {
        String query = "SELECT * FROM Product;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
