public class Member {

    // Set to -1 if object has not been created yet
    private int memberID;
    private int staffID;
    private String firstName;
    private String lastName;
    private String activeStatus;
    private String email;
    private String address;
    private String phoneNumber;

    public Member(int memberID, int staffID, String firstName, String lastName, String activeStatus, String email, String address, String phoneNumber) {
        this.memberID = memberID;
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.activeStatus = activeStatus;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
        if (firstName != null && firstName.length() != 0) {
            this.firstName = firstName;
        }

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.length() != 0) {
            this.lastName = lastName;
        }

    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() != 0) {
            this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() != 0) {
            this.phoneNumber = phoneNumber;
        }
    }
}