package controllers;

import models.Staff;
import models.StaffType;
import utilities.Utility;

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
            createRegistrationOperator(staffID);
        } else if (type == StaffType.CASHIER) {
            createCashier(staffID);
        } else if (type == StaffType.ADMIN) {
            createAdmin(staffID);
            createWarehouseOperator(staffID);
            createBillingStaff(staffID);
            createCashier(staffID);
            createRegistrationOperator(staffID);
        } else if (type == StaffType.WAREHOUSE_OPERATOR) {
            createWarehouseOperator(staffID);
        } else if (type == StaffType.BILLING_STAFF) {
            createBillingStaff(staffID);
        }

    }

    public void createBillingStaff(int staffID) throws SQLException {
        String query = "INSERT INTO BillingStaff (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    public void createWarehouseOperator(int staffID) throws SQLException {
        String query = "INSERT INTO WarehouseOperator (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    public void createAdmin(int staffID) throws SQLException {
        String query = "INSERT INTO Admin (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    public void createRegistrationOperator(int staffID) throws SQLException {
        String query = "INSERT INTO RegistrationOperator (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
        preparedStatement.execute();
    }

    public void createCashier(int staffID) throws SQLException {
        String query = "INSERT INTO Cashier (staffID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, staffID);
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

    public void printWarehouseOperatorList() throws SQLException {
        String query = "SELECT * FROM WarehouseOperator;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    public void printBillingStaffList() throws SQLException {
        String query = "SELECT * FROM BillingStaff;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    public void printCashierList() throws SQLException {
        String query = "SELECT * FROM Cashier;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    public void printRegistrationOperatorList() throws SQLException {
        String query = "SELECT * FROM RegistrationOperator;";
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
