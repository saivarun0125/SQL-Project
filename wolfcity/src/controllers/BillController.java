package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Bill;
import models.RewardsCheck;
import utilities.Utility;

/**
 * Controls all the billing operations including adding, updating, and deleting.
 * Also generates rewards checks.
 */
public class BillController {
    /** Database connection */
    private static Connection connection;

    /**
     * Constructs a BillController object
     * @param connection connection
     * @throws SQLException e
     */
    public BillController ( final Connection connection ) throws SQLException {
        BillController.connection = connection;
    }

    /**
     * Create a new bill in the system
     * @param bill bill
     * @throws SQLException e
     */
    public void createBillInformation ( final Bill bill ) throws SQLException {
        final String query = "INSERT INTO Bill (staffID, supplierID, amount, issueDate, dueDate ) VALUES(?, ?, ?, ?, ?)";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        preparedStatement.setInt( 1, bill.getStaffID() );
        preparedStatement.setInt( 2, bill.getSupplierID() );
        preparedStatement.setFloat( 3, bill.getAmount() );
        preparedStatement.setTimestamp( 4, bill.getIssueDate() );
        preparedStatement.setTimestamp( 5, bill.getDueDate() );
        preparedStatement.execute();
    }

    /**
     * Update an existing bill
     * @param bill bill
     * @throws SQLException e
     */
    public void updateBillInformation ( final Bill bill ) throws SQLException {
        final String query = "UPDATE Bill set staffID = ?, supplierID = ?, amount = ?, issueDate = ?, dueDate = ? WHERE billID = ?;";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        preparedStatement.setInt( 1, bill.getStaffID() );
        preparedStatement.setInt( 2, bill.getSupplierID() );
        preparedStatement.setFloat( 3, bill.getAmount() );
        preparedStatement.setTimestamp( 4, bill.getIssueDate() );
        preparedStatement.setTimestamp( 5, bill.getDueDate() );
        preparedStatement.setInt( 6, bill.getBillID() );
        preparedStatement.execute();
    }

    /**
     * Delete a bill's information
     * @param billID bill id
     * @throws SQLException e
     */
    public void deleteBillInformation ( final int billID ) throws SQLException {
        final String query = "DELETE FROM Bill WHERE billID = ?;";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        preparedStatement.setInt( 1, billID );
        preparedStatement.execute();
    }

    /**
     * Print the list of bills in the system
     * @throws SQLException e
     */
    public void printBillList () throws SQLException {
        final String query = "SELECT * FROM Bill;";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        final ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet( set );
    }

    /**
     * Generate a rewards check
     * @param rewardsCheck check
     * @throws SQLException e
     */
    public void generateRewardsCheck ( final RewardsCheck rewardsCheck ) throws SQLException {
        final String query = "INSERT INTO RewardsCheck (amount, staffID, memberID ) VALUES (?, ?, ?)";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        preparedStatement.setFloat( 1, rewardsCheck.getAmount() );
        preparedStatement.setInt( 2, rewardsCheck.getStaffID() );
        preparedStatement.setInt( 3, rewardsCheck.getMemberID() );
        preparedStatement.execute();
    }
}
