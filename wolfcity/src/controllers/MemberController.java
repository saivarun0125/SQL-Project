package controllers;

import models.Member;
import utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles everything pertaining to adding, editing and deleting a member
 */
public class MemberController {

    /** Database connection */
    private static Connection connection;

    /**
     * Constructs a member controller from a database connection
     * @param connection connection
     * @throws SQLException e
     */
    public MemberController(Connection connection) throws SQLException {
        MemberController.connection = connection;
    }

    /**
     * Add a new member into the SQL database
     * @param member member
     * @throws SQLException e
     */
    public void enterMemberInformation(Member member) throws SQLException {
        String query = "INSERT INTO Member (staffID, firstName, lastName, activeStatus, email, address, phoneNumber) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        // See whether registration operator is null
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

    /**
     * Update a member's information in the database
     * @param member member
     * @throws SQLException e
     */
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

    /**
     * Delete a member's information
     * @param memberID memberID
     * @throws SQLException e
     */
    public void deleteMemberInformation(int memberID) throws SQLException {
        String query = "DELETE FROM Member WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();
    }

    /**
     * Prints out all existing members
     * @throws SQLException e
     */
    public void printMemberList() throws SQLException {
        String query = "SELECT * FROM Member;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }
}
