package controllers;

import models.Shipment;
import utlities.Utility;

import java.sql.*;

public class ShipmentController {
    private static Connection connection;

    public ShipmentController(Connection connection) {
        ShipmentController.connection = connection;
    }

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

        for (Integer i : shipment.getProducts()) {
            query = "INSERT INTO ShipmentProducts (shipmentID, productID) VALUES (?, ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, shipment.getShipmentID());
            preparedStatement.setInt(2, i);
            preparedStatement.executeQuery();
        }
    }



    public void printShipmentList() throws SQLException {
        String query = "SELECT * FROM Shipment;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
