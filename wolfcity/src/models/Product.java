package models;

public class Product {
    private int productID;
    private String productName;
    private int supplierID;
    private float price;
    private float buyPrice;

    public Product(int productID, String productName, int supplierID, float price, float buyPrice) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.price = price;
        this.buyPrice = buyPrice;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
