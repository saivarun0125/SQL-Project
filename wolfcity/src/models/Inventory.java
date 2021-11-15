package models;

import java.sql.Timestamp;

/**
 * The inventory stores an amount, which product it is, and expiration and manufacturing dates
 */
public class Inventory {
    private int inventoryID;
    private int amount;
    private int productID;
    private Timestamp expirationDate;
    private Timestamp manufacturingDate;

    public Inventory(int inventoryID, int amount, int productID, Timestamp expirationDate, Timestamp manufacturingDate) {
        this.inventoryID = inventoryID;
        this.amount = amount;
        this.productID = productID;
        this.expirationDate = expirationDate;
        this.manufacturingDate = manufacturingDate;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Timestamp getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Timestamp manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
}
