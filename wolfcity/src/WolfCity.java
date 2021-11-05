import controllers.*;
import models.*;
import utlities.Utility;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class WolfCity {


    // Update your user info alone here
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/jjboike"; // Using SERVICE_NAME

// Update your user and password info here!

    private static final String user = "jjboike";
    private static final String password = "wolfpackjack";
    private static Scanner scan;
    private static Connection connection;
    private static MemberController memberController;
    private static StaffController staffController;
    private static StoreController storeController;
    private static DiscountController discountController;
    private static ProductController productController;

    public static void main(String[] args) {
        try {
// Loading the driver. This creates an instance of the driver
// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.
            Class.forName("org.mariadb.jdbc.Driver");
            connection = null;

            try {
                // Get a connection instance from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL
                connection = DriverManager.getConnection(jdbcURL, user, password);
                memberController = new MemberController(connection);
                staffController = new StaffController(connection);
                storeController = new StoreController(connection);
                discountController = new DiscountController(connection);
                productController = new ProductController(connection);

                while (true) {
                    System.out.println("What operation would you like to perform?");
                    System.out.println("1. Add a member to the system");
                    System.out.println("2. Edit a member");
                    System.out.println("3. Delete a member");
                    System.out.println("4. Add a staff member to the system");
                    System.out.println("5. Edit a staff member");
                    System.out.println("6. Delete a staff member");
                    System.out.println("7. Add a store to the system");
                    System.out.println("8. Edit a store");
                    System.out.println("9. Delete a store");
                    System.out.println("10. Add a discount to the system");
                    System.out.println("11. Edit a discount");
                    System.out.println("12. Delete a discount");
                    scan = new Scanner(System.in);
                    int num = scan.nextInt();
                    scan.nextLine();
                    if (num == 1) {
                        addMember();
                    } else if (num == 2) {
                        editMember();
                    } else if (num == 3) {
                        deleteMember();
                    } else if (num == 4) {
                        addStaff();
                    } else if (num == 5) {
                        editStaff();
                    } else if (num == 6) {
                        deleteStaff();
                    } else if (num == 7) {
                        addStore();
                    } else if (num == 8) {
                        editStore();
                    } else if (num == 9) {
                        deleteStore();
                    } else if (num == 10) {
                        addDiscount();
                    } else if (num == 11) {
                        editDiscount();
                    } else if (num == 12) {
                        deleteDiscount();
                    }
                }
            } finally {
                close(connection);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void addMember() throws SQLException {
        System.out.println("Enter staff ID");
        int staffID = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the member's first name");
        String firstName = scan.nextLine();
        System.out.println("Enter the member's last name");
        String lastName = scan.nextLine();
        System.out.println("Enter the member's email");
        String email = scan.nextLine();
        System.out.println("Enter the member's address");
        String address = scan.nextLine();
        System.out.println("Enter member's phone number");
        String phone = scan.nextLine();
        Member member = new Member(-1, staffID, firstName,
                lastName, "Active", email, address, phone);
        memberController.enterMemberInformation(member);
    }

    static void editMember() throws SQLException {
        System.out.println("Here are all of the Members in the system");

        memberController.printMemberList();
        System.out.println("Which member would you like to edit");

        int memberID = scan.nextInt();
        String query = "SELECT * FROM Member where memberID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, memberID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Member member = new Member(set.getInt("memberID"), set.getInt("staffID"),
                set.getString("firstName"), set.getString("lastName"),
                set.getString("activeStatus"), set.getString("email"),
                set.getString("address"), set.getString("phoneNumber"));
        scan.nextLine();
        System.out.println("Edit this member's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter models.Member's first name");
        String firstName = scan.nextLine();
        member.setFirstName(firstName);
        System.out.println("Enter models.Member's last name");
        String lastName = scan.nextLine();
        member.setLastName(lastName);
        System.out.println("Enter models.Member's email");
        String email = scan.nextLine();
        member.setEmail(email);
        System.out.println("Enter models.Member's address");
        String address = scan.nextLine();
        member.setAddress(address);
        System.out.println("Enter models.Member's phone number");
        String phone = scan.nextLine();
        member.setPhoneNumber(phone);
        System.out.println("Enter models.Member's active status (y/n)");
        String activeStatus = scan.nextLine();
        char ch = activeStatus.charAt(0);
        if (ch == 'y') {
            member.setActiveStatus("Active");
        } else {
            member.setActiveStatus("Inactive");
        }
        memberController.updateMemberInformation(member);
    }

    static void deleteMember() throws SQLException {
        System.out.println("Here are all of the Members in the system");
        memberController.printMemberList();
        System.out.println("Which member would you like to delete?");
        int memberID = scan.nextInt();
        scan.nextLine();
        memberController.deleteMemberInformation(memberID);
    }

    static void deleteStaff() throws SQLException {
        System.out.println("Here are all of the models.Staff members in the system");
        staffController.printStaffList();
        System.out.println("Which member would you like to delete?");
        int staffID = scan.nextInt();
        scan.nextLine();
        staffController.deleteStaffInformation(staffID);
    }

    static void editStaff() throws SQLException {
        System.out.println("Here are all of the models.Staff members in the system");

        staffController.printStaffList();
        System.out.println("Which staff member would you like to edit");

        int staffID = scan.nextInt();
        String query = "SELECT * FROM Staff where staffID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, staffID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        StaffType type = null;
        switch (set.getString("jobTitle")) {
            case "Admin" : type = StaffType.ADMIN;
            case "RegistrationOperator": type = StaffType.REGISTRATION_OPERATOR;
            case "WarehouseOperator": type = StaffType.WAREHOUSE_OPERATOR;
            case "Cashier": type = StaffType.CASHIER;
            case "BillingStaff": type =  StaffType.BILLING_STAFF;
            default: type = StaffType.CASHIER;
        };
        Staff staff = new Staff(set.getInt("staffID"), set.getString("name"),
                set.getInt("age"), set.getInt("storeID"),
                set.getString("homeAddress"), set.getString("jobTitle"),
                set.getString("phoneNumber"), set.getString("email"),
                set.getTimestamp("employmentDate"), type);
        scan.nextLine();
        System.out.println("Edit this member's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter staff member's name");
        String name = scan.nextLine();
        staff.setName(name);
        System.out.println("Enter staff member's age");
        int age = scan.nextInt();
        scan.nextLine();
        staff.setAge(age);
        System.out.println("Enter staff member's store");
        int storeID = scan.nextInt();
        scan.nextLine();
        staff.setStoreID(storeID);
        System.out.println("Enter staff member's address");
        String address = scan.nextLine();
        staff.setHomeAddress(address);
        System.out.println("Enter staff member's job phone number");
        String phone = scan.nextLine();
        staff.setPhoneNumber(phone);
        System.out.println("Enter staff member's email");
        String email = scan.nextLine();
        staff.setEmail(email);
        staffController.updateStaffInformation(staff);
    }

    private static void addStaff() throws SQLException {
        System.out.println("Enter the staff member's name");
        String name = scan.nextLine();
        System.out.println("Enter the staff member's age");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the storeID attached to the staff member");
        int storeID = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter staff member's address");
        String address = scan.nextLine();
        System.out.println("Enter staff member's job title (Admin, RegistrationOperator, WarehouseOperator, Cashier, BillingStaff");
        String jobTitle = scan.nextLine();
        System.out.println("Enter the staff member's phone number");
        String phone = scan.nextLine();
        System.out.println("Enter staff member's email");
        String email = scan.nextLine();

        StaffType type = null;
        switch (jobTitle) {
            case "Admin" : type = StaffType.ADMIN;
            case "RegistrationOperator": type = StaffType.REGISTRATION_OPERATOR;
            case "WarehouseOperator": type = StaffType.WAREHOUSE_OPERATOR;
            case "Cashier": type = StaffType.CASHIER;
            case "BillingStaff": type =  StaffType.BILLING_STAFF;
            default: type = StaffType.CASHIER;
        };

        Staff staff = new Staff(-1, name, age,
                storeID, address, jobTitle, phone, email, java.sql.Timestamp.valueOf(LocalDateTime.now()), type);
        staffController.enterStaffInformation(staff);
    }
    
    private static void addStore() throws SQLException {
        System.out.println("Enter the store's address");
        String address = scan.nextLine();
        System.out.println("Enter the store's phone number");
        String phone = scan.nextLine();
        Store store = new Store(-1, phone, address);
        storeController.enterStoreInformation(store);
    }

    static void editStore() throws SQLException {
        System.out.println("Here are all of the stores in the system");
        storeController.printStoreList();
        System.out.println("Which store would you like to edit");
        int storeID = scan.nextInt();
        String query = "SELECT * FROM Store where storeID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, storeID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Store store = new Store(set.getInt("storeID"), set.getString("phoneNumber"), set.getString("address"));
        scan.nextLine();
        System.out.println("Edit this store's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter the store's address");
        String address = scan.nextLine();
        store.setAddress(address);
        System.out.println("Enter the store's phone number");
        String phone = scan.nextLine();
        store.setPhone(phone);
        storeController.updateStoreInformation(store);
    }

    static void deleteStore() throws SQLException {
        System.out.println("Here are all of the stores in the system");
        storeController.printStoreList();
        System.out.println("Which store would you like to delete?");
        int storeID = scan.nextInt();
        scan.nextLine();
        storeController.deleteStoreInformation(storeID);
    }

    private static void addDiscount() throws SQLException {
        System.out.println("Here are all of the products in the system");
        productController.printProductList();
        System.out.println("Enter the product to which the discount is applied:");
        int productID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the percent off on the discount");
        int percentOff = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the discount's start date");
        System.out.print("Year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        int month =scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int day =scan.nextInt();
        scan.nextLine();
        Timestamp startDate = Utility.getTimestampObject(year, month, day);

        System.out.println("Enter the discount's end date");
        System.out.print("Year: ");
        int yearEnd = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        int monthEnd =scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int dayEnd =scan.nextInt();
        scan.nextLine();
        Timestamp endDate = Utility.getTimestampObject(yearEnd, monthEnd, dayEnd);

        Discount discount = new Discount(-1, productID, percentOff, startDate, endDate);
        discountController.enterDiscountInformation(discount);
    }

    static void editDiscount() throws SQLException {
        System.out.println("Here are all of the discounts in the system");
        discountController.printDiscountList();
        System.out.println("Which discount would you like to edit");
        int discountID = scan.nextInt();
        String query = "SELECT * FROM Discount where discountID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, discountID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Discount discount = new Discount(set.getInt("discountID"), set.getInt("storeID"),
                set.getInt("percentOff"), set.getTimestamp("startDate"),
                set.getTimestamp("endDate"));
        System.out.println("Enter the product to which the discount is applied:");
        int productID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the percent off on the discount");
        int percentOff = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the discount's start date");
        System.out.print("Year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        int month =scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int day =scan.nextInt();
        scan.nextLine();
        Timestamp startDate = Utility.getTimestampObject(year, month, day);

        System.out.println("Enter the discount's end date");
        System.out.print("Year: ");
        int yearEnd = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        int monthEnd =scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int dayEnd =scan.nextInt();
        scan.nextLine();
        Timestamp endDate = Utility.getTimestampObject(yearEnd, monthEnd, dayEnd);

        discount.setProductID(productID);
        discount.setEndDate(endDate);
        discount.setStartDate(startDate);
        discount.setPercentOff(percentOff);
        discountController.updateDiscountInformation(discount);
    }

    static void deleteDiscount() throws SQLException {
        System.out.println("Here are all of the discounts in the system");
        discountController.printDiscountList();
        System.out.println("Which discount would you like to delete?");
        int discountID = scan.nextInt();
        scan.nextLine();
        discountController.deleteDiscountInformation(discountID);
    }

    static void close(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch(Throwable whatever) {}
        }
    }
    static void close(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch(Throwable whatever) {}
        }
    }
    static void close(ResultSet result) {
        if(result != null) {
            try {
                result.close();
            } catch(Throwable whatever) {}
        }
    }
}