package controllers;

import models.Store;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreController {

    private static Connection connection;

    public StoreController(Connection connection) throws SQLException {
        StoreController.connection = connection;
    }

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

    public void deleteStoreInformation(int storeID) throws SQLException {
        String query = "DELETE FROM Store WHERE storeID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, storeID);
        preparedStatement.execute();
    }

    public void printStoreList() throws SQLException {
        String query = "SELECT * FROM Store;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
