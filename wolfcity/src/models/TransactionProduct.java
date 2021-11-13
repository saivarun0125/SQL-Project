package models;

public class TransactionProduct {
    private int transactionID;
    private int productID;
    private int quantity;

    public TransactionProduct(int transactionID, int productID, int quantity) {
        this.transactionID = transactionID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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
