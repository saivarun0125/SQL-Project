package models;

/**
 * AppliesTo object represents a discount that is applied to a product in a transaction
 */
public class AppliesTo {
    /** Discount */
    private int discountID;
    /** Product */
    private int productID;
    /** Transaction */
    private int transactionID;

    /**
     * Instantiates an AppliesTo object
     * @param discountID discount
     * @param productID product
     * @param transactionID transaction
     */
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
