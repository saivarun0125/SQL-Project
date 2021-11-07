package models;

import java.sql.Timestamp;

public class WarehouseInventory extends Inventory {
    private int warehouseID;

    public WarehouseInventory(int inventoryID, int amount, float price, int productID, Timestamp expirationDate, Timestamp manufacturingDate, int warehouseID) {
        super(inventoryID, amount, price, productID, expirationDate, manufacturingDate);
        this.warehouseID = warehouseID;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }
}
