package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Bill;
import models.RewardsCheck;
import utilities.Utility;

public class BillController {

    private static Connection connection;

    public BillController ( final Connection connection ) throws SQLException {
        BillController.connection = connection;
    }

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

    public void deleteBillInformation ( final int billID ) throws SQLException {
        final String query = "DELETE FROM Bill WHERE billID = ?;";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        preparedStatement.setInt( 1, billID );
        preparedStatement.execute();
    }

    public void printBillList () throws SQLException {
        final String query = "SELECT * FROM Bill;";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        final ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet( set );
    }

    public void generateRewardsCheck ( final RewardsCheck rewardsCheck ) throws SQLException {
        final String query = "INSERT INTO RewardsCheck (amount, staffID, memberID ) VALUES (?, ?, ?)";
        final PreparedStatement preparedStatement = connection.prepareStatement( query );
        preparedStatement.setFloat( 1, rewardsCheck.getAmount() );
        preparedStatement.setInt( 2, rewardsCheck.getStaffID() );
        preparedStatement.setInt( 3, rewardsCheck.getMemberID() );
        preparedStatement.execute();
    }
}
