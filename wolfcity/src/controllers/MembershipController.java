package controllers;

import models.Membership;
import utlities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MembershipController {

    private static Connection connection;
    private static ProductController productController;

    public MembershipController(Connection connection) throws SQLException {
        MembershipController.connection = connection;
        productController = new ProductController(connection);
    }

    public void enterMembershipInformation(Membership membership) throws SQLException {
        String query = "INSERT INTO Membership (memberID, staffID, level, status, startDate, endDate) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, membership.getMemberID());
        preparedStatement.setInt(2, membership.getStaffID());
        preparedStatement.setString(3, membership.getLevel().toString());
        preparedStatement.setBoolean(4, membership.isStatus());
        preparedStatement.setTimestamp(5, membership.getStartDate());
        preparedStatement.setTimestamp(6, membership.getEndDate());
        preparedStatement.execute();
    }

    public void updateMembershipInformation(Membership membership) throws SQLException {
        String query = "UPDATE Membership set staffID = ?, level = ?, status = ?, startDate = ?, endDate = ? WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, membership.getStaffID());
        preparedStatement.setString(2, membership.getLevel().toString());
        preparedStatement.setBoolean(3, membership.isStatus());
        preparedStatement.setTimestamp(4, membership.getStartDate());
        preparedStatement.setTimestamp(5, membership.getEndDate());
        preparedStatement.setInt(6, membership.getMemberID());
        preparedStatement.execute();
    }

    public void deleteMembershipInformation(int memberID) throws SQLException {
        String query = "SELECT * FROM Membership WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();
    }

    public void printMembershipList() throws SQLException {
        String query = "SELECT * FROM Membership;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
