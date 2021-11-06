package controllers;

import models.Warehouse;
import utlities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WarehouseController {

    private static Connection connection;

    public WarehouseController(Connection connection) throws SQLException {
        WarehouseController.connection = connection;
    }

    public void enterWarehouseInformation(Warehouse warehouse) throws SQLException {
        String query = "INSERT INTO Warehouse (address) VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, warehouse.getAddress());
        preparedStatement.execute();
    }

    public void updateWarehouseInformation(Warehouse warehouse) throws SQLException {
        String query = "UPDATE Warehouse set address = ? WHERE warehouseID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, warehouse.getAddress());
        preparedStatement.setInt(2, warehouse.getWarehouseID());
        preparedStatement.execute();
    }

    public void deleteWarehouseInformation(int warehouseID) throws SQLException {
//        String query = "DELETE FROM WarehouseInventory WHERE inventoryID IN (SELECT inventoryID FROM Inventory WHERE warehouseID = ?);";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, warehouseID);
//        preparedStatement.execute();

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

        query = "DELETE FROM Warehouse WHERE warehouseID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, warehouseID);
        preparedStatement.execute();
    }

    public void printWarehouseList() throws SQLException {
        String query = "SELECT * FROM Warehouse;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
