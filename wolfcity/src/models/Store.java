package models;

/**
 * Store that is managed by a manager
 */
public class Store {

    private String phone;

    private String address;

    private int storeID;

    private int staffID;

    public Store(int storeID, String phone, String address, int staffID) {
        this.storeID = storeID;
        this.phone = phone;
        this.address = address;
        this.staffID = Math.max(staffID, 0);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.length() != 0) {
            this.phone = phone;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.length() != 0) {
            this.address = address;
        }
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
