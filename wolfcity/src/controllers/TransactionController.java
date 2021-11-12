package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import models.Member;
import models.Transaction;
import utlities.Utility;

public class TransactionController {

	private static Connection connection;

	public TransactionController(Connection connection) throws SQLException {
		TransactionController.connection = connection;
	}

	public void enterTransactionInformation(Transaction t) throws SQLException {
		String query = "INSERT INTO Transaction (totalPrice, storeID, memberID, staffID, purchaseDate) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, t.getTotalPrice());
		preparedStatement.setInt(2, t.getStoreID());
		preparedStatement.setInt(3, t.getMemberID());
		preparedStatement.setInt(4, t.getStaffID());
		preparedStatement.setTimestamp(5, t.getPurchaseDate());
		preparedStatement.execute();
	}

	public void printTransactionList() throws SQLException {
		String query = "SELECT * FROM Transaction;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet set = preparedStatement.executeQuery();
		Utility.printResultSet(set);

	}

	public void updateTransactionInformation(Transaction t) throws SQLException {
		String query = "UPDATE Transaction set totalPrice = ?, storeID = ?, memberID = ?, staffID = ?, purchaseDate = ? WHERE transactionID = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, t.getTotalPrice());
		preparedStatement.setInt(2, t.getStoreID());
		preparedStatement.setInt(3, t.getMemberID());
		preparedStatement.setInt(4, t.getStaffID());
		preparedStatement.setTimestamp(5, t.getPurchaseDate());
		preparedStatement.execute();
	}

	public void deleteTransactionInformation(int transactionID) throws SQLException {
		String query = "DELETE FROM Member WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, transactionID);
        preparedStatement.execute();
	}

}
