package models;

public class Transfers {

    private int transferID;
    private int staffID;
    private int productID;
    private int quantity;
    private int originWarehouseID;
    private int destinationWarehouseID;

    public int getDestinationWarehouseID() {
        return destinationWarehouseID;
    }

    public void setDestinationWarehouseID(int destinationWarehouseID) {
        this.destinationWarehouseID = destinationWarehouseID;
    }

    public Transfers (final int transferID, final int staffID, final int productID, final int quantity,
                      final int originWarehouseID, final int destinationWarehouseID ) {
        this.transferID = transferID;
        this.staffID = staffID;
        this.productID = productID;
        this.quantity = quantity;
        this.originWarehouseID = originWarehouseID;
        this.destinationWarehouseID = destinationWarehouseID;
    }

    public int getTransferID () {
        return transferID;
    }

    public void setTransferID ( final int transferID ) {
        this.transferID = transferID;
    }

    public int getStaffID () {
        return staffID;
    }

    public void setStaffID ( final int staffID ) {
        this.staffID = staffID;
    }

    public int getProductID () {
        return productID;
    }

    public void setProductID ( final int productID ) {
        this.productID = productID;
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity ( final int quantity ) {
        this.quantity = quantity;
    }

    public int getOriginWarehouseID () {
        return originWarehouseID;
    }

    public void setOriginWarehouseID ( final int originWarehouseID ) {
        this.originWarehouseID = originWarehouseID;
    }

}
