package controllers;

import models.Member;
import models.Product;
import models.Store;
import utilities.Utility;

import java.sql.*;

public class ReportController {

    private static Connection connection;

    public ReportController(Connection connection) throws SQLException {
        ReportController.connection = connection;
    }


    public void getSalesReportByDate(Timestamp startDate, Timestamp endDate) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE purchaseDate BETWEEN ? AND LAST_DAY(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTimestamp(1, startDate);
        preparedStatement.setTimestamp(2, endDate);
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        System.out.println("Total sales in the date range: $" + set.getFloat("SUM(totalPrice)"));
    }

    public void getSalesReportByMonth(int year, int month) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE YEAR(purchaseDate) = ? AND MONTH(purchaseDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        preparedStatement.setInt(2, month);
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        System.out.println("Total sales in the month: $" + set.getFloat("SUM(totalPrice)"));
    }

    public void getSalesReportByYear(int year) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE YEAR(purchaseDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        System.out.println("Total sales in the year: $" + set.getFloat("SUM(totalPrice)"));

    }

    public void getMerchandiseStockReportByStore(int storeID) throws SQLException {
        String query = "select s.storeID as storeID, p.productName as productName, p.productID as productID, i.amount as amount, p.price as price, p.buyPrice as wholesalePrice from Inventory i " +
                "join Product p on i.productID = p.productID " +
                "join StoreInventory s on s.inventoryID = i.inventoryID and storeID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, storeID);
        ResultSet set = preparedStatement.executeQuery();
        System.out.println("Merchandise Report for Store #" + storeID);
        Utility.printResultSet(set);
    }

    public void getMerchandiseStockReportByProduct(int productID) throws SQLException {
        String query = "select s.storeID as storeID, p.productName as productName, p.productID as productID, i.amount as amount, p.price as price, p.buyPrice as wholesalePrice from Inventory i " +
                "join Product p on i.productID = p.productID " +
                "join StoreInventory s on s.inventoryID = i.inventoryID and p.productID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        ResultSet set = preparedStatement.executeQuery();
        System.out.println("Merchandise Report for Product #" + productID);
        Utility.printResultSet(set);
    }

    public void getCustomerGrowthByYear(int year) throws SQLException {
        String query = "SELECT COUNT(*) FROM Membership WHERE YEAR(startDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    public void getCustomerGrowthByMonth(int year, int month) throws SQLException {
        String query = "SELECT COUNT(*) FROM Membership WHERE MONTH(startDate) = ? and YEAR(startDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, month);
        preparedStatement.setInt(2, year);

        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    public void getCustomerTotalPurchaseAmount(int memberID) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

}
