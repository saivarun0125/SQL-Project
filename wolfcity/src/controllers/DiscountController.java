package controllers;

import models.Discount;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controls all discount operations including adding, updating, and deleting.
 */
public class DiscountController {
    /** Database connection */
    private static Connection connection;

    /**
     * Construct a discount controller object
     * @param connection connection
     * @throws SQLException e
     */
    public DiscountController(Connection connection) throws SQLException {
        DiscountController.connection = connection;
    }

    /**
     * Enter a discount's information
     * @param discount discount
     * @throws SQLException e
     */
    public void enterDiscountInformation(Discount discount) throws SQLException {
        String query = "INSERT INTO Discount (productID, percentOff, startDate, endDate) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, discount.getProductID());
        preparedStatement.setInt(2, discount.getPercentOff());
        preparedStatement.setTimestamp(3, discount.getStartDate());
        preparedStatement.setTimestamp(4, discount.getEndDate());
        preparedStatement.execute();
    }

    /**
     * Update an existing discount's information
     * @param discount discount
     * @throws SQLException e
     */
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

    /**
     * Delete a discount's information
     * @param discountID id of the discount
     * @throws SQLException e
     */
    public void deleteDiscountInformation(int discountID) throws SQLException {
        String query = "DELETE FROM Discount WHERE discountID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, discountID);
        preparedStatement.execute();
    }

    /**
     * Print the existing discounts in the system
     * @throws SQLException e
     */
    public void printDiscountList() throws SQLException {
        String query = "SELECT * FROM Discount;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
