package controllers;

import models.Discount;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountController {

    private static Connection connection;

    public DiscountController(Connection connection) throws SQLException {
        DiscountController.connection = connection;
    }

    public void enterDiscountInformation(Discount discount) throws SQLException {
        String query = "INSERT INTO Discount (productID, percentOff, startDate, endDate) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, discount.getProductID());
        preparedStatement.setInt(2, discount.getPercentOff());
        preparedStatement.setTimestamp(3, discount.getStartDate());
        preparedStatement.setTimestamp(4, discount.getEndDate());
        preparedStatement.execute();
    }

    public void updateDiscountInformation(Discount discount) throws SQLException {
        String query = "UPDATE Discount set productID = ?, percentOff = ?, startDate = ?, endDate = ? WHERE discountID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, discount.getProductID());
        preparedStatement.setInt(2, discount.getPercentOff());
        preparedStatement.setTimestamp(3, discount.getStartDate());
        preparedStatement.setTimestamp(4, discount.getEndDate());
        preparedStatement.setInt(5, discount.getDiscountID());
        preparedStatement.execute();
    }

    public void deleteDiscountInformation(int discountID) throws SQLException {
        String query = "DELETE FROM Discount WHERE discountID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, discountID);
        preparedStatement.execute();
    }

    public void printDiscountList() throws SQLException {
        String query = "SELECT * FROM Discount;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
