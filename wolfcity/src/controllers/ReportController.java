package controllers;

import models.Member;
import models.Product;
import models.Store;
import utilities.Utility;

import java.sql.*;

/**
 * Class controls reports that are chosen by the user
 */
public class ReportController {
    /** Database connection */
    private static Connection connection;

    public ReportController(Connection connection) throws SQLException {
        ReportController.connection = connection;
    }


    /**
     * Gets a sales report for all transactions in a given date range
     * The total dollar amount in sales is printed in the console
     * @param startDate start date
     * @param endDate end date
     * @throws SQLException e
     */
    public void getSalesReportByDate(Timestamp startDate, Timestamp endDate) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE purchaseDate BETWEEN ? AND LAST_DAY(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTimestamp(1, startDate);
        preparedStatement.setTimestamp(2, endDate);
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        System.out.println("Total sales in the date range: $" + set.getFloat("SUM(totalPrice)"));
    }

    /**
     * Gets a sales report for a given month in a year
     * The total dollar amount in sales is printed in the console
     * @param year year
     * @param month month
     * @throws SQLException e
     */
    public void getSalesReportByMonth(int year, int month) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE YEAR(purchaseDate) = ? AND MONTH(purchaseDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        preparedStatement.setInt(2, month);
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        System.out.println("Total sales in the month: $" + set.getFloat("SUM(totalPrice)"));
    }

    /**
     * Gets a sales report for a given year
     * The total dollar amount in sales is printed in the console
     * @param year year
     * @throws SQLException e
     */
    public void getSalesReportByYear(int year) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE YEAR(purchaseDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        System.out.println("Total sales in the year: $" + set.getFloat("SUM(totalPrice)"));

    }

    /**
     * Gets a stock report for a given store
     * All the products in the inventory in this store are shown and along with their quantity and price
     * @param storeID id of the store
     * @throws SQLException e
     */
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

    /**
     * Gets a stock report for a given product
     * All of the inventory in each store is displayed for this product
     * @param productID id of the product
     * @throws SQLException e
     */
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

    /**
     * Gets the amount of customers that are new in the system in a given year
     * @param year year
     * @throws SQLException e
     */
    public void getCustomerGrowthByYear(int year) throws SQLException {
        String query = "SELECT COUNT(*) FROM Membership WHERE YEAR(startDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Gets the amount of customers that are new in the system in a given month in a year
     * @param year year
     * @param month month
     * @throws SQLException e
     */
    public void getCustomerGrowthByMonth(int year, int month) throws SQLException {
        String query = "SELECT COUNT(*) FROM Membership WHERE MONTH(startDate) = ? and YEAR(startDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, month);
        preparedStatement.setInt(2, year);

        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

    /**
     * Gets the total amount of sales for a given customer in a date range
     * @param memberID id of the member
     * @throws SQLException e
     */
    public void getCustomerTotalPurchaseAmount(int memberID) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        ResultSet set = preparedStatement.executeQuery();
        Utility.printResultSet(set);
    }

}
