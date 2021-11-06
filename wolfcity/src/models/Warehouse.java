package models;

public class Warehouse {

    private int warehouseID;
    private String address;

    public Warehouse(int warehouseID, String address) {
        this.warehouseID = warehouseID;
        this.address = address;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
