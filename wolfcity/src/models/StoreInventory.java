package models;

import java.sql.Timestamp;

public class StoreInventory extends Inventory {

    private int storeID;

    public StoreInventory(int inventoryID, int amount, float price, int productID, Timestamp expirationDate, Timestamp manufacturingDate, int storeID) {
        super(inventoryID, amount, price, productID, expirationDate, manufacturingDate);
        this.storeID = storeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
