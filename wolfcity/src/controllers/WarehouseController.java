package controllers;

import models.Warehouse;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controls adding, modifying and deleting warehouses in the system
 */
public class WarehouseController {

    /** Database connection */
    private static Connection connection;

    /**
     * Constructs a WarehouseController object
     * @param connection connection
     * @throws SQLException e
     */
    public WarehouseController(Connection connection) throws SQLException {
        WarehouseController.connection = connection;
    }

    /**
     * Create a new warehouse in the system
     * @param warehouse warehouse
     * @throws SQLException e
     */
    public void enterWarehouseInformation(Warehouse warehouse) throws SQLException {
        String query = "INSERT INTO Warehouse (address) VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, warehouse.getAddress());
        preparedStatement.execute();
    }

    /**
     * Update an existing warehouse in the system
     * @param warehouse warehouse
     * @throws SQLException e
     */
    public void updateWarehouseInformation(Warehouse warehouse) throws SQLException {
        String query = "UPDATE Warehouse set address = ? WHERE warehouseID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, warehouse.getAddress());
        preparedStatement.setInt(2, warehouse.getWarehouseID());
        preparedStatement.execute();
    }

    /**
     * Delete a warehouse's information from the database
     * @param warehouseID warehouse id
     * @throws SQLException e
     */
    public void deleteWarehouseInformation(int warehouseID) throws SQLException {

        // Delete all of the database entries associated with this warehouse
        String query = "DELETE wi, i, t\n" +
                "FROM Warehouse w\n" +
                "  LEFT JOIN WarehouseInventory wi  ON  wi.warehouseID = w.warehouseID\n" +
                "  LEFT JOIN Inventory i ON  i.inventoryID = wi.inventoryID\n" +
                "  LEFT JOIN Shipment s ON  s.originWarehouseID = w.warehouseID\n" +
                "  LEFT JOIN ShipmentProducts sp ON  s.shipmentID = sp.shipmentID\n" +
                "  LEFT JOIN Transfers t ON  t.originWarehouseID = w.warehouseID OR t.destinationWarehouseID = w.warehouseID\n" +
                "WHERE\n" +
                "    w.warehouseID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, warehouseID);
        preparedStatement.execute();

        // Delete the warehouse
        query = "DELETE FROM Warehouse WHERE warehouseID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, warehouseID);
        preparedStatement.execute();
    }

    /**
     * Print the list of existing warehouses in the system
     * @throws SQLException e
     */
    public void printWarehouseList() throws SQLException {
        String query = "SELECT * FROM Warehouse;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
