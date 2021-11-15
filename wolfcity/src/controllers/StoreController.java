package controllers;

import models.Store;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles store operations
 */
public class StoreController {
    /** Database connection */
    private static Connection connection;

    /**
     * Constructs a StoreController object
     * @param connection connection
     * @throws SQLException e
     */
    public StoreController(Connection connection) throws SQLException {
        StoreController.connection = connection;
    }

    /**
     * Create a new store
     * @param store store
     * @throws SQLException e
     */
    public void enterStoreInformation(Store store) throws SQLException {
        String query = "INSERT INTO Store (phoneNumber, address, staffID) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, store.getPhone());
        preparedStatement.setString(2, store.getAddress());
        if (store.getStaffID() <= 0) {
            preparedStatement.setNull(3,java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(3, store.getStaffID());
        }
        preparedStatement.execute();
    }

    /**
     * Update an existing store
     * @param store store
     * @throws SQLException e
     */
    public void updateStoreInformation(Store store) throws SQLException {
        String query = "UPDATE Store set phoneNumber = ?, address = ?, staffID = ? WHERE storeID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, store.getPhone());
        preparedStatement.setString(2, store.getAddress());
        if (store.getStaffID() <= 0) {
            preparedStatement.setNull(3,java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(3, store.getStaffID());
        }
        preparedStatement.setInt(4, store.getStoreID());
        preparedStatement.execute();
    }

    /**
     * Delete a store from the system
     * @param storeID store id
     * @throws SQLException e
     */
    public void deleteStoreInformation(int storeID) throws SQLException {
        String query = "DELETE si, i, sh\n" +
                "FROM Store s \n" +
                "  LEFT JOIN StoreInventory si  ON  si.storeID = s.storeID\n" +
                "  LEFT JOIN Inventory i ON  i.inventoryID = si.inventoryID\n" +
                "  LEFT JOIN Shipment sh ON  sh.destinationStoreID = s.storeID\n" +
                "WHERE\n" +
                "    s.storeID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, storeID);
        preparedStatement.execute();

        query = "UPDATE Staff set storeID = ? WHERE storeID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setNull(1,java.sql.Types.INTEGER);
        preparedStatement.setInt(2, storeID);
        preparedStatement.execute();


        query = "DELETE FROM Store WHERE storeID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, storeID);
        preparedStatement.execute();
    }

    /**
     * Print a list of all the stores in the system
     * @throws SQLException e
     */
    public void printStoreList() throws SQLException {
        String query = "SELECT * FROM Store;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
