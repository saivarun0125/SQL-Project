package controllers;

import models.Inventory;
import models.StoreInventory;
import models.Transfers;
import models.WarehouseInventory;
import utlities.Utility;

import java.sql.*;

public class InventoryController {
    private static Connection connection;

    public static final String SELECT_ALL = "SELECT i.inventoryID, i.productID, i.amount, i.price, i.expirationDate, i.manufacturingDate, si.storeID, wi.warehouseID FROM Inventory i " +
            "LEFT JOIN StoreInventory si  ON i.inventoryID = si.inventoryID " +
            "LEFT JOIN  WarehouseInventory wi ON i.inventoryID = wi.inventoryID;";

    public static final String SELECT_WAREHOUSE_INVENTORY = "SELECT i.inventoryID, i.productID, i.amount, i.price, i.expirationDate, i.manufacturingDate, wi.warehouseID FROM Inventory i " +
            "INNER JOIN  WarehouseInventory wi ON i.inventoryID = wi.inventoryID;";

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

    public void transferProduct(Transfers transfer) throws SQLException {


        String query = "UPDATE Inventory i INNER JOIN WarehouseInventory wi ON (wi.inventoryID = i.inventoryID and wi.warehouseID = ? and i.productID = ?) SET i.amount = i.amount - ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, transfer.getOriginWarehouseID());
        preparedStatement.setInt(2, transfer.getProductID());
        preparedStatement.setInt(3, transfer.getQuantity());
        preparedStatement.executeQuery();
        System.out.println(preparedStatement);


        query = "UPDATE Inventory i INNER JOIN WarehouseInventory wi ON (wi.inventoryID = i.inventoryID and wi.warehouseID = ? and i.productID = ?) SET i.amount = i.amount + ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, transfer.getDestinationWarehouseID());
        preparedStatement.setInt(2, transfer.getProductID());
        preparedStatement.setInt(3, transfer.getQuantity());
        preparedStatement.executeQuery();
        System.out.println(preparedStatement);



        query = "INSERT INTO Transfers (staffID, productID, quantity, originWarehouseID, destinationWarehouseID) VALUES (?, ?, ?, ?, ?);";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, transfer.getStaffID());
        preparedStatement.setInt(2, transfer.getProductID());
        preparedStatement.setInt(3, transfer.getQuantity());
        preparedStatement.setInt(4, transfer.getOriginWarehouseID());
        preparedStatement.setInt(5, transfer.getDestinationWarehouseID());
        preparedStatement.executeQuery();

    }



//
//    public void createShipment(Transfers transfer) throws SQLException {
//        String query = "INSERT INTO Transfers (staffID, productID, quantity, originWarehouseID, destinationWarehouseID) VALUES (?, ?, ?, ?, ?);";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, transfer.getStaffID());
//        preparedStatement.setInt(2, transfer.getProductID());
//
//        preparedStatement.setInt(3, transfer.getQuantity());
//        preparedStatement.setInt(4, transfer.getOriginWarehouseID());
//        preparedStatement.setInt(5, transfer.getDestinationWarehouseID());
//        preparedStatement.executeQuery();
//
//        String select = "SELECT LAST_INSERT_ID();";
//    }

    public void printWarehouseInventoryList() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WAREHOUSE_INVENTORY);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }



    public void printInventoryList() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
