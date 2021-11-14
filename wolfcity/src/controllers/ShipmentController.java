package controllers;

import models.Product;
import models.Shipment;
import models.Type;
import utilities.Utility;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Controls creating shipments in the system
 */
public class ShipmentController {
    /** Database connection */
    private static Connection connection;

    /**
     * Constructs a ShipmentController object
     * @param connection connection
     */
    public ShipmentController(Connection connection) {
        ShipmentController.connection = connection;
    }

    /**
     * Create a new shipment
     * @param shipment shipment
     * @throws SQLException e
     */
    public void enterShipmentInformation(Shipment shipment) throws SQLException {
        String query = "INSERT INTO Shipment (staffID, type, originWarehouseID, destinationStoreID) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, shipment.getStaffID());
        preparedStatement.setString(2, shipment.getType().toString());
        preparedStatement.setInt(3, shipment.getOriginWarehouseID());
        preparedStatement.setInt(4, shipment.getDestinationStoreID());
        preparedStatement.executeQuery();

        String select = "SELECT LAST_INSERT_ID();";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select);
        int shipmentID = -1;
        if (set.next()) {
            shipmentID = set.getInt("LAST_INSERT_ID()");
        }
        shipment.setShipmentID(shipmentID);


        // Check if the shipment type is new or a return
        if (shipment.getType() == Type.NEW_SHIPMENT) {
            // Shipment is a return
            // Subtract inventory from warehouse and add it to the store
            for (Map.Entry<Integer, Integer> product : shipment.getProducts().entrySet()) {
                query = "UPDATE Inventory i INNER JOIN WarehouseInventory wi ON (wi.inventoryID = i.inventoryID and wi.warehouseID = ? and i.productID = ?) SET i.amount = i.amount - ?;";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, shipment.getOriginWarehouseID());
                preparedStatement.setInt(2, product.getKey());
                preparedStatement.setInt(3, product.getValue());
                preparedStatement.executeQuery();
                System.out.println(preparedStatement);


                query = "UPDATE Inventory i INNER JOIN StoreInventory si ON (si.inventoryID = i.inventoryID and si.storeID = ? and i.productID = ?) SET i.amount = i.amount + ?;";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, shipment.getDestinationStoreID());
                preparedStatement.setInt(2, product.getKey());
                preparedStatement.setInt(3, product.getValue());
                preparedStatement.executeQuery();
                System.out.println(preparedStatement);
            }
        } else {
            // Shipment is a return
            // Subtract inventory from the store and add to the warehouse
            for (Map.Entry<Integer, Integer> product : shipment.getProducts().entrySet()) {
                query = "UPDATE Inventory i INNER JOIN WarehouseInventory wi ON (wi.inventoryID = i.inventoryID and wi.warehouseID = ? and i.productID = ?) SET i.amount = i.amount + ?;";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, shipment.getOriginWarehouseID());
                preparedStatement.setInt(2, product.getKey());
                preparedStatement.setInt(3, product.getValue());
                preparedStatement.executeQuery();
                System.out.println(preparedStatement);


                query = "UPDATE Inventory i INNER JOIN StoreInventory si ON (si.inventoryID = i.inventoryID and si.storeID = ? and i.productID = ?) SET i.amount = i.amount - ?;";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, shipment.getDestinationStoreID());
                preparedStatement.setInt(2, product.getKey());
                preparedStatement.setInt(3, product.getValue());
                preparedStatement.executeQuery();
                System.out.println(preparedStatement);
            }

        }


        // Add the products in the shipment to the ShipmentProducts table
        for (Map.Entry<Integer, Integer> product: shipment.getProducts().entrySet()) {
            query = "INSERT INTO ShipmentProducts (shipmentID, productID, quantity) VALUES (?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, shipment.getShipmentID());
            preparedStatement.setInt(2, product.getKey());
            preparedStatement.setInt(3, product.getValue());
            preparedStatement.executeQuery();
        }
    }


    /**
     * Print all of the shipments in the system
     * @throws SQLException e
     */
    public void printShipmentList() throws SQLException {
        String query = "SELECT * FROM Shipment;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
