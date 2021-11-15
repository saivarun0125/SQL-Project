package controllers;

import models.Staff;
import models.StaffType;
import utilities.Utility;

import java.sql.*;
import java.util.Scanner;

/**
 * Handles adding, updating and deleting staff members;
 */
public class StaffController {
    /** Database connection */
    private static Connection connection;

    /**
     * Constructs a StaffController object from a database connection
     * @param connection connection
     * @throws SQLException e
     */
    public StaffController(Connection connection) throws SQLException {
        StaffController.connection = connection;
    }

    /**
     * Create a new staff member
     * @param staff staff
     * @throws SQLException e
     */
    public boolean enterStaffInformation(Staff staff) throws SQLException {
//        String query = "INSERT INTO Staff (name, age, storeID, homeAddress, jobTitle, phoneNumber, email, employmentDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, staff.getName());
//        preparedStatement.setInt(2, staff.getAge());
//        preparedStatement.setInt(3, staff.getStoreID());
//        preparedStatement.setString(4, staff.getHomeAddress());
//        preparedStatement.setString(5, staff.getJobTitle());
//        preparedStatement.setString(6, staff.getPhoneNumber());
//        preparedStatement.setString(7, staff.getEmail());
//        preparedStatement.setTimestamp(8, staff.getEmploymentDate());
//         preparedStatement.executeQuery();
//
//        String select = "SELECT LAST_INSERT_ID();";
//        Statement statement = connection.createStatement();
//        ResultSet set = statement.executeQuery(select);
//        int staffID = -1;
//        if (set.next()) {
//            staffID = set.getInt("LAST_INSERT_ID()");
//        }
//        staff.setStaffID(staffID);
//        System.out.println(staffID);
//        StaffType type = staff.getType();
//
//        if (type == StaffType.REGISTRATION_OPERATOR) {
//            createRegistrationOperator(staffID);
//        } else if (type == StaffType.CASHIER) {
//            createCashier(staffID);
//        } else if (type == StaffType.ADMIN) {
//            createAdmin(staffID);
//            createWarehouseOperator(staffID);
//            createBillingStaff(staffID);
//            createCashier(staffID);
//            createRegistrationOperator(staffID);
//        } else if (type == StaffType.WAREHOUSE_OPERATOR) {
//            createWarehouseOperator(staffID);
//        } else if (type == StaffType.BILLING_STAFF) {
//            createBillingStaff(staffID);
//        }
    
    	
    	connection.beginRequest(); //start the Transaction

        //attempt to insert staff member into Staff table
        String sqlStaff = "INSERT INTO Staff (staffID, name, age, storeID, homeAddress, jobTitle, phoneNumber, email, employmentDate) "
                  + "VALUES (%d, '%s', %d, %d, '%s', '%s', '%s', '%s', NOW())";
        sqlStaff = String.format(sqlStaff, staff.getStaffID(), staff.getName(), staff.getAge(), staff.getStoreID(), staff.getHomeAddress(), staff.getJobTitle(), staff.getPhoneNumber(), staff.getEmail());

        PreparedStatement preparedStatement = connection.prepareStatement(sqlStaff);

        if(!preparedStatement.execute(sqlStaff)) {
          //rollback if failed
          System.out.println("Couldn't add staff member to Staff table");
          connection.rollback();
          return false;
        }

        System.out.println(sqlStaff);

        String sqlJobTitle = "INSERT INTO (staffID)" + staff.getJobTitle() + " VALUES ( %d )";
        sqlJobTitle = String.format(sqlJobTitle, staff.getStaffID());

        //attempt to insert staff member into specific job table
        //Cashier, RegistrationOperator, Admin, BillingStaff, Cashier
        if (staff.getJobTitle() != null) {
          if (!preparedStatement.execute(sqlJobTitle)) {
            //rollback if railed
            System.out.println("Couldn't add " + staff.getJobTitle() + " to " + staff.getJobTitle() + "table.");
            connection.rollback();
            return false;
          }
        }


        connection.commit(); //commit transaction
        return true;

    }

    /**
     * Createa a billing staff member
     * @param staffID staff id
     * @throws SQLException e
     */
    public void createBillingStaff(int staffID) throws SQLException {
        String query = "INSERT INTO BillingStaff (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Create a warehouse operator
     * @param staffID staff id
     * @throws SQLException e
     */
    public void createWarehouseOperator(int staffID) throws SQLException {
        String query = "INSERT INTO WarehouseOperator (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Create an admin in the system
     * @param staffID staff id
     * @throws SQLException e
     */
    public void createAdmin(int staffID) throws SQLException {
        String query = "INSERT INTO Admin (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Create a registration operator in the system
     * @param staffID staff id
     * @throws SQLException e
     */
    public void createRegistrationOperator(int staffID) throws SQLException {
        String query = "INSERT INTO RegistrationOperator (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Create a cashier in the system
     * @param staffID staff id
     * @throws SQLException e
     */
    public void createCashier(int staffID) throws SQLException {
        String query = "INSERT INTO Cashier (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Update a member of the staff
     * @param staff staff
     * @throws SQLException e
     */
    public void updateStaffInformation(Staff staff) throws SQLException {
        String query = "UPDATE Staff set name = ?, age = ?, storeID = ?, homeAddress = ?, phoneNumber = ?, email = ?, employmentDate = ? WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, staff.getName());
        preparedStatement.setInt(2, staff.getAge());
        preparedStatement.setInt(3, staff.getStoreID());
        preparedStatement.setString(4, staff.getHomeAddress());
        preparedStatement.setString(5, staff.getPhoneNumber());
        preparedStatement.setString(6, staff.getEmail());
        preparedStatement.setTimestamp(7, staff.getEmploymentDate());
        preparedStatement.setInt(8, staff.getStaffID());
        preparedStatement.execute();
    }

    /**
     * Delete a staff member
     * @param staffID staff id
     * @throws SQLException e
     */
    public void deleteStaffInformation(int staffID) throws SQLException {

        String query = "SELECT * FROM Staff where staffID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, staffID);
        ResultSet set = st.executeQuery();
        set.next();
        switch (set.getString("jobTitle")) {
            case "Admin" : deleteAdmin(staffID);
            case "RegistrationOperator": deleteRegistrationOperator(staffID);
            case "WarehouseOperator": deleteWarehouseOperator(staffID);
            case "Cashier": deleteCashier(staffID);
            case "BillingStaff": deleteBillingStaff(staffID);
        };

        query = "DELETE FROM Staff WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Print the existing staff in the system
     * @throws SQLException e
     */
    public void printStaffList() throws SQLException {
        String query = "SELECT * FROM Staff;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Print the list of existing warehouse operators
     * @throws SQLException e
     */
    public void printWarehouseOperatorList() throws SQLException {
        String query = "SELECT * FROM WarehouseOperator;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Print the existing billing staff in the system
     * @throws SQLException e
     */
    public void printBillingStaffList() throws SQLException {
        String query = "SELECT * FROM BillingStaff;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Print the existing cashiers in the system
     * @throws SQLException e
     */
    public void printCashierList() throws SQLException {
        String query = "SELECT * FROM Cashier;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Print the existing registration operators in the system
     * @throws SQLException e
     */
    public void printRegistrationOperatorList() throws SQLException {
        String query = "SELECT * FROM RegistrationOperator;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Delete an admin from the system
     * @param staffID staff id
     * @throws SQLException e
     */
    private void deleteAdmin(int staffID) throws SQLException {
        String query = "DELETE FROM BillingStaff WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();

        query = "DELETE FROM Cashier WHERE staffID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();

        query = "DELETE FROM RegistrationOperator WHERE staffID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();

        query = "DELETE FROM WarehouseOperator WHERE staffID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();

        query = "DELETE FROM Admin WHERE staffID = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Delete a registration operator from the system
     * @param staffID staff id
     * @throws SQLException e
     */
    private void deleteRegistrationOperator(int staffID) throws SQLException {
        String query = "DELETE FROM RegistrationOperator WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Delete a billing staff from the system
     * @param staffID staff id
     * @throws SQLException e
     */
    private void deleteBillingStaff(int staffID) throws SQLException {
        String query = "DELETE FROM BillingStaff WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Delete a warehouse operator from the system
     * @param staffID staff id
     * @throws SQLException e
     */
    private void deleteWarehouseOperator(int staffID) throws SQLException {
        String query = "DELETE FROM WarehouseOperator WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    /**
     * Delete a cashier from the system
     * @param staffID staff id
     * @throws SQLException e
     */
    private void deleteCashier(int staffID) throws SQLException {
        String query = "DELETE FROM Cashier WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }
}
