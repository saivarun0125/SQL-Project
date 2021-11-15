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

	/** Database connection */
	private static Connection connection;
	/** Controls discount operations */
	private static DiscountController discountController;
	/** Map of discounts. The key is the productID and the value is a Discount object */
	private static HashMap<Integer, Discount> discountsMap;

	/**
	 * Constructs a TransactionController object from a database connection
	 * Instantiates the dictionary of active discounts
	 * @param connection connection
	 * @throws SQLException e
	 */
	public TransactionController(Connection connection) throws SQLException {
		TransactionController.connection = connection;
		TransactionController.discountController = new DiscountController(connection);
		TransactionController.discountsMap = new HashMap<>();
		getAllDiscounts();
	}

	/**
	 * Create a transaction in the system
	 * @param t transaction
	 * @throws SQLException e
	 */
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

			query = "UPDATE Inventory i INNER JOIN StoreInventory si ON si.inventoryID = i.inventoryID AND si.storeID = ? SET i.amount = i.amount - ? WHERE i.productID = ?; ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, t.getStoreID());
			preparedStatement.setInt(2, tp.getQuantity());
			preparedStatement.setInt(3, tp.getProductID());
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

		preparedStatement.close();
		set.close();
	}

	/**
	 * Print a list of transactions
	 * @throws SQLException e
	 */
	public void printTransactionList() throws SQLException {
		String query = "SELECT * FROM Transaction;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet set = preparedStatement.executeQuery();
		Utility.printResultSet(set);

	}

	/**
	 * Update a transaction with new product quantities
	 * @param t transaction
	 * @throws SQLException e
	 */
	public void updateTransactionInformation(Transaction t) throws SQLException {
		calculateTotalPrice(t);
		String query = "UPDATE Transaction set totalPrice = %d, storeID = %d, memberID = %d, staffID = %d, purchaseDate = %s WHERE transactionID = %d;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setFloat(1, t.getTotalPrice());
		preparedStatement.setInt(2, t.getStoreID());
		preparedStatement.setInt(3, t.getMemberID());
		preparedStatement.setInt(4, t.getStaffID());
		preparedStatement.setTimestamp(5, t.getPurchaseDate());
		preparedStatement.setInt(6, t.getTransactionID());
		
		
		//attempt to update the transaction information
		if(!preparedStatement.execute()) {
			//rollback if it failed
			System.out.println("Couldn't edit Transaction with ID " + t.getTransactionID());
			connection.rollback();
		}


		//editing products in a transaction
		for (TransactionProduct tp : t.getProducts()) {
			query = "UPDATE TransactionProducts set quantity = ? WHERE transactionID = ? and productID = ?;";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setFloat(1, tp.getQuantity());
			preparedStatement.setInt(2, tp.getTransactionID());
			preparedStatement.setInt(3, tp.getProductID());
			
			//attempt to update a single product
			if(!preparedStatement.execute()) {
				//rollback if it failed
				System.out.println("Couldn't edit product in Transaction " + tp.getProductID());
				connection.rollback();
			}
		}
		preparedStatement.close();
	}

	/**
	 * Delete a transaction from the system
	 * @param transactionID transaction id
	 * @throws SQLException e
	 */
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

		preparedStatement.close();
	}

	/**
	 * Calculate the total price for a given transaction
	 * @param t transaction
	 * @return The list of discounts that are applied to the transaction
	 * @throws SQLException e
	 */
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
			// Check if a discount for a given product is active
			if (discountsMap.get(tp.getProductID()) != null) {
				// Apply the discount to the transaction
				Discount discount = discountsMap.get(tp.getProductID());
				itemPrice *= ((double) (100 - discount.getPercentOff()) / 100);
				// Add the current discount to the list of discounts added to the current transaction
				discounts.add(discount);
			}
			// Add the item's cost to the total cost
			totalPrice += itemPrice;
			preparedStatement.close();
			set.close();
		}
		// Set the total price of the transaction
		t.setTotalPrice(totalPrice);
		return discounts;
	}

	/**
	 * Get all of the discounts in the system that are active
	 * @throws SQLException e
	 */
	public void getAllDiscounts() throws SQLException {
		String query = "SELECT * FROM Discount;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet set = preparedStatement.executeQuery();
		// Iterate through all of the discounts in the sytem
		while (set.next()) {
			int discountID = set.getInt("discountID");
			int productID = set.getInt("productID");
			int percentOff = set.getInt("percentOff");
			Timestamp startDate = set.getTimestamp("startDate");
			Timestamp endDate = set.getTimestamp("endDate");

			Discount discount = new Discount(discountID, productID, percentOff, startDate, endDate);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			// Check if the discount is active by looking at start and end date
			if (startDate.before(currentTime) && endDate.after(currentTime)) {
				// Add the discount to the map of discounts
				discountsMap.put(productID, discount);
			}
		}
		preparedStatement.close();
		set.close();

	}

}
