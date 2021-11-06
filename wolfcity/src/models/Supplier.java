package models;

import controllers.ProductController;

public class Supplier {
    private int supplierID;
    private String email;
    private String phoneNumber;
    private String address;
    private String name;


    public Supplier(int supplierID, String email, String phoneNumber, String address, String name) {
        this.supplierID = supplierID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.name = name;

    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
