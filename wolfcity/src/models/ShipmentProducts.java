package models;

public class ShipmentProducts extends Shipment {

    private int productID;

    public ShipmentProducts ( final int shipmentID, final int staffID, final Type type, final int originWarehouseID,
            final int destinationWarehouseID, final int productID ) {
        super( shipmentID, staffID, type, originWarehouseID, destinationWarehouseID );
        this.productID = productID;
    }

    public int getProductID () {
        return productID;
    }

    public void setProductID ( final int productID ) {
        this.productID = productID;
    }

}
