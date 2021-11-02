public class Member {

    // Set to -1 if object has not been created yet
    private int memberID;
    private int staffID;
    private String firstName;
    private String lastName;
    private Boolean activeStatus;
    private String email;
    private String address;
    private String phone;

    public Member(int memberID, int staffID, String firstName, String lastName, Boolean activeStatus, String email, String address, String phone) {
        this.memberID = memberID;
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.activeStatus = activeStatus;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}