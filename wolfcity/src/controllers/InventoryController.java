package controllers;

import models.Inventory;
import models.StoreInventory;
import models.Transfers;
import models.WarehouseInventory;
import utilities.Utility;

import java.sql.*;

/**
 * Class controls all inventory operations including adding, updating inventory. Also handles warehouse transfers
 */
public class InventoryController {
    /** Database connection */
    private static Connection connection;

    /**
     * Select all of the inventory entries
     */
    public static final String SELECT_ALL = "SELECT i.inventoryID, i.productID, i.amount, p.price, p.buyPrice, i.expirationDate, i.manufacturingDate, si.storeID, wi.warehouseID FROM Inventory i " +
            "LEFT JOIN StoreInventory si  ON i.inventoryID = si.inventoryID " +
            "LEFT JOIN Product p  ON i.productID = p.productID " +
            "LEFT JOIN  WarehouseInventory wi ON i.inventoryID = wi.inventoryID;";

    /**
     * Filter inventory entries by only warehouse ones
     */
    public static final String SELECT_WAREHOUSE_INVENTORY = "SELECT i.inventoryID, i.productID, i.amount, p.price, p.buyPrice, i.expirationDate, i.manufacturingDate, wi.warehouseID FROM Inventory i " +
            "INNER JOIN Product p  ON i.productID = p.productID " +
            "INNER JOIN  WarehouseInventory wi ON i.inventoryID = wi.inventoryID;";

    /**
     * Constructs an InventoryController object
     * @param connection connection
     * @throws SQLException e
     */
    public InventoryController(Connection connection) throws SQLException {
        InventoryController.connection = connection;
    }

    /**
     * Create a new inventory entry
     * @param inventory inventory
     * @throws SQLException e
     */
    public void enterInventoryInformation(Inventory inventory) throws SQLException {
    	
    	//prepare query
        String query = "INSERT INTO Inventory (amount, productID, expirationDate, manufacturingDate) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        //getting inventory information
        preparedStatement.setInt(1, inventory.getAmount());
        preparedStatement.setInt(2, inventory.getProductID());
        preparedStatement.setTimestamp(3, inventory.getExpirationDate());
        preparedStatement.setTimestamp(4, inventory.getManufacturingDate());
        preparedStatement.executeQuery();

        // Get inventoryID that was just created
        String select = "SELECT LAST_INSERT_ID();";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select);
        int inventoryID = -1;
        if (set.next()) {
            inventoryID = set.getInt("LAST_INSERT_ID()");
        }
        inventory.setInventoryID(inventoryID);
        System.out.println(inventoryID);
        // Create inventory in either a store or a warehouse
        if (inventory instanceof StoreInventory) {
            query = "INSERT INTO StoreInventory (inventoryID, storeID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            
            //get store inventory info
            preparedStatement.setInt(1, inventory.getInventoryID());
            preparedStatement.setInt(2, ((StoreInventory) inventory).getStoreID());
            
            //attempt to insert StoreInventory
            if(!preparedStatement.execute()) {
            	//rollback if it failed
            	System.out.println("Could not add the Store Inventory");
            	connection.rollback();
            }

        } else if (inventory instanceof WarehouseInventory) {
            query = "INSERT INTO WarehouseInventory (inventoryID, warehouseID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            
            //get warehouse inventory info
            preparedStatement.setInt(1, inventory.getInventoryID());
            preparedStatement.setInt(2, ((WarehouseInventory) inventory).getWarehouseID());
            
            //attempt to insert WarehouseInventory
            if(!preparedStatement.execute()) {
            	//rollback if it failed
            	System.out.println("Could not add the Warehouse Inventory");
            	connection.rollback();
            }
            
            preparedStatement.close();
        }

    }

    /**
     * Update an inventory entry
     * @param inventory inventory
     * @throws SQLException e
     */
    public void updateInventoryInformation(Inventory inventory) throws SQLException {
        String query = "UPDATE Inventory set amount = ?, productID = ?, expirationDate = ?, manufacturingDate = ? WHERE inventoryID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, inventory.getAmount());
        preparedStatement.setInt(2, inventory.getProductID());
        preparedStatement.setTimestamp(3, inventory.getExpirationDate());
        preparedStatement.setTimestamp(4, inventory.getManufacturingDate());
        preparedStatement.setInt(5, inventory.getInventoryID());
        preparedStatement.execute();
    }

    /**
     * Delete an inventory entry
     * @param inventoryID inventory ID
     * @throws SQLException e
     */
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

    /**
     * Transfer a specified amount of a product between two warehouses
     * @param transfer transfer
     * @throws SQLException e
     */
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

    /**
     * Print a list of warehouse inventory
     * @throws SQLException e
     */
    public void printWarehouseInventoryList() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WAREHOUSE_INVENTORY);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Print all inventory in the system
     * @throws SQLException e
     */
    public void printInventoryList() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
