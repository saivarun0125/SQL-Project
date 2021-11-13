package models;

import java.sql.Timestamp;

public class StoreInventory extends Inventory {

    private int storeID;

    public StoreInventory(int inventoryID, int amount, int productID, Timestamp expirationDate, Timestamp manufacturingDate, int storeID) {
        super(inventoryID, amount, productID, expirationDate, manufacturingDate);
        this.storeID = storeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
