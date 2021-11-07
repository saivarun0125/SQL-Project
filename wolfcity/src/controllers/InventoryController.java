package controllers;

import models.Inventory;
import models.StoreInventory;
import models.WarehouseInventory;
import utlities.Utility;

import java.sql.*;

public class InventoryController {
    private static Connection connection;

    public static final String SELECT_ALL = "SELECT i.inventoryID, i.productID, i.amount, i.price, i.expirationDate, i.manufacturingDate, si.storeID, wi.warehouseID FROM Inventory i " +
            "LEFT JOIN StoreInventory si  ON i.inventoryID = si.inventoryID " +
            "LEFT JOIN  WarehouseInventory wi ON i.inventoryID = wi.inventoryID;";

    public InventoryController(Connection connection) throws SQLException {
        InventoryController.connection = connection;
    }

    public void enterInventoryInformation(Inventory inventory) throws SQLException {



        String query = "INSERT INTO Inventory (amount, price, productID, expirationDate, manufacturingDate) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, inventory.getAmount());
        preparedStatement.setFloat(2, inventory.getPrice());

        preparedStatement.setInt(3, inventory.getProductID());
        preparedStatement.setTimestamp(4, inventory.getExpirationDate());
        preparedStatement.setTimestamp(5, inventory.getManufacturingDate());
        preparedStatement.executeQuery();

        String select = "SELECT LAST_INSERT_ID();";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select);
        int inventoryID = -1;
        if (set.next()) {
            inventoryID = set.getInt("LAST_INSERT_ID()");
        }
        inventory.setInventoryID(inventoryID);
        System.out.println(inventoryID);
        if (inventory instanceof StoreInventory) {
            query = "INSERT INTO StoreInventory (inventoryID, storeID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inventory.getInventoryID());
            preparedStatement.setInt(2, ((StoreInventory) inventory).getStoreID());
            preparedStatement.execute();

        } else if (inventory instanceof WarehouseInventory) {
            query = "INSERT INTO WarehouseInventory (inventoryID, warehouseID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inventory.getInventoryID());
            preparedStatement.setInt(2, ((WarehouseInventory) inventory).getWarehouseID());
            preparedStatement.execute();
        }

    }

    public void updateInventoryInformation(Inventory inventory) throws SQLException {
        String query = "UPDATE Inventory set amount = ?, price = ?, productID = ?, expirationDate = ?, manufacturingDate = ? WHERE inventoryID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, inventory.getAmount());
        preparedStatement.setFloat(2, inventory.getPrice());
        preparedStatement.setInt(3, inventory.getProductID());
        preparedStatement.setTimestamp(4, inventory.getExpirationDate());
        preparedStatement.setTimestamp(5, inventory.getManufacturingDate());
        preparedStatement.setInt(6, inventory.getInventoryID());
        preparedStatement.execute();
    }

    public void deleteInventoryInformation(int inventoryID) throws SQLException {
        String query = "SELECT * FROM WarehouseInventory where inventoryID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, inventoryID);
        ResultSet set = st.executeQuery();
        if (set.next()) {
            query = "DELETE FROM WarehouseInventory where inventoryID = ?";
            st = connection.prepareStatement(query);
            st.setInt(1, inventoryID);
        }

        query = "SELECT * FROM StoreInventory where inventoryID = ?";
        st = connection.prepareStatement(query);
        st.setInt(1, inventoryID);
        set = st.executeQuery();
        if (set.next()) {
            query = "DELETE FROM StoreInventory where inventoryID = ?";
            st = connection.prepareStatement(query);
            st.setInt(1, inventoryID);
        }

        query = "DELETE FROM Inventory WHERE inventoryID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, inventoryID);
        preparedStatement.execute();
    }


    public void printInventoryList() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
