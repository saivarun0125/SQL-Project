import java.sql.Date;
import java.sql.Timestamp;

public class Staff {
    // Set to -1 if object has not been created yet
    public enum StaffType {
        CASHIER, WAREHOUSE_OPERATOR, BILLING_STAFF, ADMIN, REGISTRATION_OPERATOR
    }

    private int staffID;
    private String name;
    private int age;
    private int storeID;
    private String homeAddress;
    private String jobTitle;
    private String phoneNumber;
    private String email;
    private Timestamp employmentDate;
    private StaffType type;



    public Staff(int staffID, String name, int age, int storeID, String homeAddress, String jobTitle, String phoneNumber, String email, Timestamp employmentDate, StaffType type) {
        this.staffID = staffID;
        this.name = name;
        this.age = age;
        this.storeID = storeID;
        this.homeAddress = homeAddress;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentDate = employmentDate;
        this.type = type;
    }

    public StaffType getType() {
        return type;
    }

    public void setType(StaffType type) {
        this.type = type;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() != 0) {
            this.name = name;
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        if (homeAddress != null && homeAddress.length() != 0) {
            this.homeAddress = homeAddress;
        }
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() != 0) {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() != 0) {
            this.email = email;
        }
    }

    public Timestamp getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Timestamp employmentDate) {
        this.employmentDate = employmentDate;
    }
}
