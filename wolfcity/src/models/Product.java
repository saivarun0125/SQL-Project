package models;

public class Product {
    private int productID;
    private String productName;
    private int supplierID;

    public Product(int productID, String productName, int supplierID) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName != null && productName.length() != 0) {
            this.productName = productName;
        }
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
}
