package controllers;

import models.Member;
import models.Product;
import models.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReportController {

    private static Connection connection;

    public ReportController(Connection connection) throws SQLException {
        ReportController.connection = connection;
    }


    public void getSalesReportByDate(Timestamp startDate, Timestamp endDate) throws SQLException {
        String query = "SELECT * FROM Transaction WHERE purchaseDate BETWEEN ? AND LAST_DAY(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTimestamp(1, startDate);
        preparedStatement.setTimestamp(2, endDate);
        preparedStatement.execute();
    }

    public void getSalesReportByMonth(int year, int month) throws SQLException {
        String query = "SELECT * FROM Transaction WHERE YEAR(purchaseDate) = ? AND MONTH(purchaseDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        preparedStatement.setInt(2, month);
        preparedStatement.execute();
    }

    public void getSalesReportByYear(int year) throws SQLException {
        String query = "SELECT * FROM Transaction WHERE YEAR(purchaseDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        preparedStatement.execute();

    }

    public void getMerchandiseStockReportByStore(Store store) throws SQLException {
        String query = "select s.storeID, p.productName, p.productID, i.amount, i.price from Inventory i " +
                "join Product p on i.productID = p.productID " +
                "join StoreInventory s on s.inventoryID = i.inventoryID and storeID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, store.getStoreID());
        preparedStatement.execute();
    }

    public void getMerchandiseStockReportByProduct(Product product) throws SQLException {
        String query = "select s.storeID, p.productName, p.productID, i.amount, i.price from Inventory i " +
                "join Product p on i.productID = p.productID " +
                "join StoreInventory s on s.inventoryID = i.inventoryID and p.productID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, product.getProductID());
        preparedStatement.execute();

    }

    public void getCustomerGrowthByYear(int year) throws SQLException {
        String query = "SELECT COUNT(*) FROM Membership WHERE YEAR(startDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        preparedStatement.execute();
    }

    public void getCustomerGrowthByMonth(int year, int month) throws SQLException {
        String query = "SELECT COUNT(*) FROM Membership WHERE MONTH(startDate) = ? and YEAR(startDate) = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, month);
        preparedStatement.setInt(2, year);

        preparedStatement.execute();
    }

    public void getCustomerTotalPurchaseAmount(Member member) throws SQLException {
        String query = "SELECT SUM(totalPrice) FROM Transaction WHERE memberID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, member.getMemberID());
        preparedStatement.execute();
    }

}
