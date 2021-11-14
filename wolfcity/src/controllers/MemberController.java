package controllers;

import models.Member;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberController {

    private static Connection connection;

    public MemberController(Connection connection) throws SQLException {
        MemberController.connection = connection;
    }

    public void enterMemberInformation(Member member) throws SQLException {
        String query = "INSERT INTO Member (staffID, firstName, lastName, activeStatus, email, address, phoneNumber) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (member.getStaffID() <= 0) {
            preparedStatement.setNull(1,java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(1, member.getStaffID());
        }

        preparedStatement.setString(2, member.getFirstName());
        preparedStatement.setString(3, member.getLastName());
        preparedStatement.setString(4, member.getActiveStatus());
        preparedStatement.setString(5, member.getEmail());
        preparedStatement.setString(6, member.getAddress());
        preparedStatement.setString(7, member.getPhoneNumber());
        preparedStatement.execute();
    }

    public void updateMemberInformation(Member member) throws SQLException {
        String query = "UPDATE Member set firstName = ?, lastName = ?, activeStatus = ?, email = ?, address = ?, phoneNumber = ? WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, member.getFirstName());
        preparedStatement.setString(2, member.getLastName());
        preparedStatement.setString(3, member.getActiveStatus());
        preparedStatement.setString(4, member.getEmail());
        preparedStatement.setString(5, member.getAddress());
        preparedStatement.setString(6, member.getPhoneNumber());
        preparedStatement.setInt(7, member.getMemberID());
        preparedStatement.execute();
    }

    public void deleteMemberInformation(int memberID) throws SQLException {
        String query = "DELETE FROM Member WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();
    }

    public void printMemberList() throws SQLException {
        String query = "SELECT * FROM Member;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
