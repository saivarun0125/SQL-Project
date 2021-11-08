package models;

public class AppliesTo {
    private int discountID;
    private int productID;
    private int transactionID;

    public AppliesTo ( final int discountID, final int productID, final int transactionID ) {
        this.discountID = discountID;
        this.productID = productID;
        this.transactionID = transactionID;
    }

    public int getDiscountID () {
        return discountID;
    }

    public void setDiscountID ( final int discountID ) {
        this.discountID = discountID;
    }

    public int getProductID () {
        return productID;
    }

    public void setProductID ( final int productID ) {
        this.productID = productID;
    }

    public int getTransactionID () {
        return transactionID;
    }

    public void setTransactionID ( final int transactionID ) {
        this.transactionID = transactionID;
    }

}
