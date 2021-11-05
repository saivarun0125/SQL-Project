package models;

import java.sql.Timestamp;

public class Discount {

    private int discountID;
    private int productID;
    private int percentOff;
    private Timestamp startDate;
    private Timestamp endDate;

    public Discount(int discountID, int productID, int percentOff, Timestamp startDate, Timestamp endDate) {
        this.discountID = discountID;
        this.productID = productID;
        this.percentOff = percentOff;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(int percentOff) {
        this.percentOff = percentOff;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
