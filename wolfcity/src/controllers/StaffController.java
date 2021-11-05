package controllers;

import models.Staff;
import models.StaffType;
import utlities.Utility;

import java.sql.*;

public class StaffController {

    private static Connection connection;

    public StaffController(Connection connection) throws SQLException {
        StaffController.connection = connection;
    }

    public void enterStaffInformation(Staff staff) throws SQLException {
        String query = "INSERT INTO Staff (name, age, storeID, homeAddress, jobTitle, phoneNumber, email, employmentDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, staff.getName());
        preparedStatement.setInt(2, staff.getAge());
        preparedStatement.setInt(3, staff.getStoreID());
        preparedStatement.setString(4, staff.getHomeAddress());
        preparedStatement.setString(5, staff.getJobTitle());
        preparedStatement.setString(6, staff.getPhoneNumber());
        preparedStatement.setString(7, staff.getEmail());
        preparedStatement.setTimestamp(8, staff.getEmploymentDate());
         preparedStatement.executeQuery();

        String select = "SELECT LAST_INSERT_ID();";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select);
        int staffID = -1;
        if (set.next()) {
            staffID = set.getInt("LAST_INSERT_ID()");
        }
        staff.setStaffID(staffID);
        System.out.println(staffID);
        StaffType type = staff.getType();

        if (type == StaffType.REGISTRATION_OPERATOR) {
            query = "INSERT INTO RegistrationOperator (staffID) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff.getStaffID());
        } else if (type == StaffType.CASHIER) {
            query = "INSERT INTO Cashier (staffID) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff.getStaffID());
        } else if (type == StaffType.ADMIN) {
            query = "INSERT INTO Admin (staffID) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff.getStaffID());
        } else if (type == StaffType.WAREHOUSE_OPERATOR) {
            query = "INSERT INTO WarehouseOperator (staffID) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff.getStaffID());
        } else if (type == StaffType.BILLING_STAFF) {
            query = "INSERT INTO BillingStaff (staffID) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff.getStaffID());
        }
        preparedStatement.execute();
    }

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

    public void printStaffList() throws SQLException {
        String query = "SELECT * FROM Staff;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    private void deleteAdmin(int staffID) throws SQLException {
        String query = "DELETE FROM Admin WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    private void deleteRegistrationOperator(int staffID) throws SQLException {
        String query = "DELETE FROM RegistrationOperator WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    private void deleteBillingStaff(int staffID) throws SQLException {
        String query = "DELETE FROM BillingStaff WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    private void deleteWarehouseOperator(int staffID) throws SQLException {
        String query = "DELETE FROM WarehouseOperator WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    private void deleteCashier(int staffID) throws SQLException {
        String query = "DELETE FROM Cashier WHERE staffID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }
}
