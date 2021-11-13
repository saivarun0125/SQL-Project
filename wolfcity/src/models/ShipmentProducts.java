package models;

import java.util.List;

public class ShipmentProducts extends Shipment {

    private int productID;
    private int quantity;

    public ShipmentProducts(int shipmentID, int staffID, Type type, int originWarehouseID, int destinationStoreID, List<Integer> products, int productID, int quantity) {
        super(shipmentID, staffID, type, originWarehouseID, destinationStoreID, products);
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
