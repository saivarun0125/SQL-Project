package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Discount;
import models.Transaction;
import models.TransactionProduct;
import utilities.Utility;

public class TransactionController {

	private static Connection connection;
	private static DiscountController discountController;
	private static HashMap<Integer, Discount> discountsMap;

	public TransactionController(Connection connection) throws SQLException {
		TransactionController.connection = connection;
		TransactionController.discountController = new DiscountController(connection);
		TransactionController.discountsMap = new HashMap<>();
		getAllDiscounts();
	}

	public void enterTransactionInformation(Transaction t) throws SQLException {
		ArrayList<Discount> appliedDiscounts = (ArrayList<Discount>) calculateTotalPrice(t);
		String query = "INSERT INTO Transaction (totalPrice, storeID, memberID, staffID, purchaseDate) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setFloat(1, t.getTotalPrice());
		preparedStatement.setInt(2, t.getStoreID());
		preparedStatement.setInt(3, t.getMemberID());
		preparedStatement.setInt(4, t.getStaffID());
		preparedStatement.setTimestamp(5, t.getPurchaseDate());
		preparedStatement.execute();

		String select = "SELECT LAST_INSERT_ID();";
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery(select);
		int transactionID = -1;
		if (set.next()) {
			transactionID = set.getInt("LAST_INSERT_ID()");
		}
		t.setTransactionID(transactionID);

		for (TransactionProduct tp : t.getProducts()) {
			tp.setTransactionID(transactionID);
			query = "INSERT INTO TransactionProducts (transactionID, productID, quantity) VALUES(?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setFloat(1, tp.getTransactionID());
			preparedStatement.setInt(2, tp.getProductID());
			preparedStatement.setInt(3, tp.getQuantity());
			preparedStatement.execute();
		}

		for (Discount d : appliedDiscounts) {
			query = "INSERT INTO AppliesTo (discountID, productID, transactionID) VALUES(?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, d.getDiscountID());
			preparedStatement.setInt(2, d.getProductID());
			preparedStatement.setInt(3, transactionID);
			preparedStatement.execute();
		}


	}

	public void printTransactionList() throws SQLException {
		String query = "SELECT * FROM Transaction;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet set = preparedStatement.executeQuery();
		Utility.printResultSet(set);

	}

	public void updateTransactionInformation(Transaction t) throws SQLException {
		calculateTotalPrice(t);
		String query = "UPDATE Transaction set totalPrice = ?, storeID = ?, memberID = ?, staffID = ?, purchaseDate = ? WHERE transactionID = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setFloat(1, t.getTotalPrice());
		preparedStatement.setInt(2, t.getStoreID());
		preparedStatement.setInt(3, t.getMemberID());
		preparedStatement.setInt(4, t.getStaffID());
		preparedStatement.setTimestamp(5, t.getPurchaseDate());
		preparedStatement.setInt(6, t.getTransactionID());
		preparedStatement.execute();


		for (TransactionProduct tp : t.getProducts()) {
			query = "UPDATE TransactionProducts set quantity = ? WHERE transactionID = ? and productID = ?;";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setFloat(1, tp.getQuantity());
			preparedStatement.setInt(2, tp.getTransactionID());
			preparedStatement.setInt(3, tp.getProductID());
			preparedStatement.execute();
		}
	}

	public void deleteTransactionInformation(int transactionID) throws SQLException {
		String query = "DELETE FROM AppliesTo WHERE transactionID = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, transactionID);
		preparedStatement.execute();

		query = "DELETE FROM TransactionProducts WHERE transactionID = ?;";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, transactionID);
		preparedStatement.execute();

		query = "DELETE FROM Transaction WHERE transactionID = ?;";
		preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, transactionID);
        preparedStatement.execute();
	}

	public List<Discount> calculateTotalPrice(Transaction t) throws SQLException {
		float totalPrice = 0;
		List<Discount> discounts = new ArrayList<>();
		for (TransactionProduct tp : t.getProducts()) {
			float itemPrice = 0;
			String query = "SELECT *  FROM Product WHERE productID = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, tp.getProductID());
			ResultSet set = preparedStatement.executeQuery();
			if (set.next()) {
				itemPrice = tp.getQuantity() * set.getFloat("price");
			}
			if (discountsMap.get(tp.getProductID()) != null) {
				Discount discount = discountsMap.get(tp.getProductID());
				itemPrice *= ((double) (100 - discount.getPercentOff()) / 100);
				discounts.add(discount);
			}
			totalPrice += itemPrice;
		}
		t.setTotalPrice(totalPrice);
		return discounts;
	}

	public void getAllDiscounts() throws SQLException {
		String query = "SELECT * FROM Discount;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			int discountID = set.getInt("discountID");
			int productID = set.getInt("productID");
			int percentOff = set.getInt("percentOff");
			Timestamp startDate = set.getTimestamp("startDate");
			Timestamp endDate = set.getTimestamp("endDate");

			Discount discount = new Discount(discountID, productID, percentOff, startDate, endDate);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			if (startDate.before(currentTime) && endDate.after(currentTime)) {
				discountsMap.put(productID, discount);
			}
		}

	}

}
