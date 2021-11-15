package controllers;

import models.Member;
import utilities.Utility;

import java.sql.*;

/**
 * Handles everything pertaining to adding, editing and deleting a member
 */
public class MemberController {

    /** Database connection */
    private static Connection connection;
    /** Instance of a TransactionController */
    private static TransactionController transactionController;

    /**
     * Constructs a member controller from a database connection
     * @param connection connection
     * @throws SQLException e
     */
    public MemberController(Connection connection) throws SQLException {
        MemberController.connection = connection;
        transactionController = new TransactionController(connection);
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
    public void updateMemberInformation(Member member, int staffID) throws SQLException {


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

        String notes = "Status is: " + member.getActiveStatus();
        String query2 = "INSERT INTO Modified (staffID, memberID, notes) VALUES(?, ?, ?);";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setInt(1, staffID);
        preparedStatement2.setInt(2, member.getMemberID());
        preparedStatement2.setString(3, notes);
        preparedStatement2.execute();
    }

    /**
     * Delete a member's information
     * @param memberID memberID
     * @throws SQLException e
     */
    public void deleteMemberInformation(int memberID) throws SQLException {
        String query = "DELETE FROM Membership WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();

        query = "DELETE FROM RewardsCheck WHERE memberID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();



        query = "SELECT * FROM Transaction WHERE memberID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            transactionController.deleteTransactionInformation(set.getInt("transactionID"));
        }

        query = "DELETE FROM Cancels WHERE memberID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();

        query = "DELETE FROM Modified WHERE memberID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.execute();

        query = "DELETE FROM Member WHERE memberID = ?;";
        preparedStatement = connection.prepareStatement(query);
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
