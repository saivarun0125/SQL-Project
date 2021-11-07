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
    private static WarehouseController warehouseController;
    private static SupplierController supplierController;
    private static MembershipController membershipController;
    private static InventoryController inventoryController;

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
                warehouseController = new WarehouseController(connection);
                supplierController = new SupplierController(connection);
                memberController = new MemberController(connection);
                membershipController = new MembershipController(connection);
                inventoryController = new InventoryController(connection);


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
                    System.out.println("13. Add a product to the system");
                    System.out.println("14. Edit a product");
                    System.out.println("15. Delete a product");
                    System.out.println("16. Add a warehouse to the system");
                    System.out.println("17. Edit a warehouse");
                    System.out.println("18. Delete a warehouse");
                    System.out.println("19. Add a supplier to the system");
                    System.out.println("20. Edit a supplier");
                    System.out.println("21. Delete a supplier");
                    System.out.println("22. Add a membership to the system");
                    System.out.println("23. Edit a membership");
                    System.out.println("24. Delete a membership");
                    System.out.println("25. Add inventory");
                    System.out.println("26. Update inventory");

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
                    } else if (num == 13) {
                        addProduct();
                    } else if (num == 14) {
                        editProduct();
                    } else if (num == 15) {
                        deleteProduct();
                    } else if (num == 16) {
                        addWarehouse();
                    } else if (num == 17) {
                        editWarehouse();
                    } else if (num == 18) {
                        deleteWarehouse();
                    } else if (num == 19) {
                        addSupplier();
                    } else if (num == 20) {
                        editSupplier();
                    } else if (num == 21) {
                        deleteSupplier();
                    } else if (num == 22) {
                        addMembership();
                    } else if (num == 23) {
                        editMembership();
                    } else if (num == 24) {
                        deleteMembership();
                    } else if (num == 25) {
                        addInventory();
                    } else if (num == 26) {
                        updateInventory();
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
        Timestamp startDate = Utility.getTimestampObject(year, month - 1, day);

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
        Timestamp endDate = Utility.getTimestampObject(yearEnd, monthEnd - 1, dayEnd);

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
        Discount discount = new Discount(set.getInt("discountID"), set.getInt("productID"),
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
        int month = scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int day =scan.nextInt();
        scan.nextLine();
        Timestamp startDate = Utility.getTimestampObject(year, month - 1, day);
        System.out.print(startDate.toString());
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
        Timestamp endDate = Utility.getTimestampObject(yearEnd, monthEnd - 1, dayEnd);
        System.out.print(endDate.toString());
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


    private static void addProduct() throws SQLException {
        System.out.println("Enter the product's name");
        String productName = scan.nextLine();
        System.out.println("Enter the supplier's id:");
        int supplierID = scan.nextInt();
        scan.nextLine();
        Product product = new Product(-1, productName, supplierID);
        productController.enterProductInformation(product);
    }

    static void editProduct() throws SQLException {
        System.out.println("Here are all of the products in the system");
        productController.printProductList();
        System.out.println("Which product would you like to edit");
        int storeID = scan.nextInt();
        String query = "SELECT * FROM Product where productID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, storeID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Product product = new Product(set.getInt("productID"), set.getString("productName"), set.getInt("supplierID"));
        scan.nextLine();
        System.out.println("Edit this store's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter the product's name");
        String productName = scan.nextLine();
        product.setProductName(productName);
        System.out.println("Enter the supplier's id:");
        int supplierID = scan.nextInt();
        product.setSupplierID(supplierID);
        scan.nextLine();
        productController.updateProductInformation(product);
    }

    static void deleteProduct() throws SQLException {
        System.out.println("Here are all of the products in the system");
        productController.printProductList();
        System.out.println("Which product would you like to delete?");
        int productID = scan.nextInt();
        scan.nextLine();
        productController.deleteProductInformation(productID);
    }


    private static void addWarehouse() throws SQLException {
        System.out.println("Enter the warehouse's address");
        String address = scan.nextLine();
        Warehouse warehouse = new Warehouse(-1, address);
        warehouseController.enterWarehouseInformation(warehouse);
    }

    static void editWarehouse() throws SQLException {
        System.out.println("Here are all of the warehouses in the system");
        warehouseController.printWarehouseList();
        System.out.println("Which warehouse would you like to edit");
        int warehouseID = scan.nextInt();
        String query = "SELECT * FROM Warehouse where warehouseID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, warehouseID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Warehouse warehouse = new Warehouse(set.getInt("warehouseID"), set.getString("address"));
        scan.nextLine();
        System.out.println("Edit this warehouse's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter the warehouse's address");
        String address = scan.nextLine();
        warehouse.setAddress(address);
        warehouseController.updateWarehouseInformation(warehouse);
    }

    static void deleteWarehouse() throws SQLException {
        System.out.println("Here are all of the warehouses in the system");
        warehouseController.printWarehouseList();
        System.out.println("Which warehouse would you like to delete?");
        int warehouseID = scan.nextInt();
        scan.nextLine();
        warehouseController.deleteWarehouseInformation(warehouseID);
    }

    private static void addSupplier() throws SQLException {
        System.out.println("Enter the supplier's email");
        String email = scan.nextLine();
        System.out.println("Enter the supplier's phone number");
        String phoneNumber = scan.nextLine();

        System.out.println("Enter the supplier's address");
        String address = scan.nextLine();

        System.out.println("Enter the supplier's name");
        String name = scan.nextLine();
        Supplier supplier = new Supplier(-1, email, phoneNumber, address, name);
        supplierController.enterSupplierInformation(supplier);
    }

    static void editSupplier() throws SQLException {
        System.out.println("Here are all of the suppliers in the system");
        supplierController.printSupplierList();
        System.out.println("Which supplier would you like to edit");
        int supplierID = scan.nextInt();
        String query = "SELECT * FROM Supplier where supplierID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, supplierID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Supplier supplier = new Supplier(set.getInt("supplierID"), set.getString("email"),
                set.getString("phoneNumber"), set.getString("address"),
                set.getString("name"));
        scan.nextLine();
        System.out.println("Edit this supplier's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter the supplier's email");
        String email = scan.nextLine();
        supplier.setEmail(email);
        System.out.println("Enter the supplier's phone number");
        String phoneNumber = scan.nextLine();
        supplier.setPhoneNumber(phoneNumber);
        System.out.println("Enter the supplier's address");
        String address = scan.nextLine();
        supplier.setAddress(address);
        System.out.println("Enter the supplier's name");
        String name = scan.nextLine();
        supplier.setName(name);
        supplierController.updateSupplierInformation(supplier);
    }

    static void deleteSupplier() throws SQLException {
        System.out.println("Here are all of the suppliers in the system");
        supplierController.printSupplierList();
        System.out.println("Which supplier would you like to delete?");
        int supplierID = scan.nextInt();
        scan.nextLine();
        supplierController.deleteSupplierInformation(supplierID);
    }

    private static void addMembership() throws SQLException {

        System.out.println("Here are all of the registration operators");
        staffController.printRegistrationOperatorList();

        System.out.println("Here are all of the members in the system");
        memberController.printMemberList();


        System.out.println("Enter the memberID for the membership");
        int memberID = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the staffID of the registration operator");
        int staffID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the membership level:");
        System.out.println("Gold (G)");
        System.out.println("Platinum (P)");
        String levelString = scan.nextLine();

        System.out.println("Enter the membership's status");
        System.out.println("Active (1)");
        System.out.println("Inactive (2)");
        int statusInt = scan.nextInt();
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
        Timestamp startDate = Utility.getTimestampObject(year, month - 1, day);

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
        Timestamp endDate = Utility.getTimestampObject(yearEnd, monthEnd - 1, dayEnd);

        boolean status = statusInt == 1;

        Level level = Level.GOLD;
        if (levelString.charAt(0) == 'G') {
            level = Level.GOLD;
        } else if  (levelString.charAt(0) == 'P') {
            level = Level.PLATINUM;
        }

        Membership membership = new Membership(memberID, staffID, level, status, startDate, endDate);
        membershipController.enterMembershipInformation(membership);
    }

    static void editMembership() throws SQLException {
        System.out.println("Here are all of the memberships in the system");
        membershipController.printMembershipList();
        System.out.println("Which membership would you like to edit");
        int membershipID = scan.nextInt();
        String query = "SELECT * FROM Membership where memberID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, membershipID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);

        String levelString = set.getString("level");
        Level level = Level.GOLD;
        if (levelString.equals("Gold")) {
            level = Level.GOLD;
        } else if  (levelString.equals("Platinum")) {
            level = Level.PLATINUM;
        }


        Membership membership = new Membership(set.getInt("memberID"), set.getInt("staffID"),
                level, set.getBoolean("status"),
                set.getTimestamp("startDate"), set.getTimestamp("endDate"));

        scan.nextLine();
        System.out.println("Edit this membership's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();

        System.out.println("Here are all of the registration operators");
        staffController.printRegistrationOperatorList();

        System.out.println("Here are all of the members in the system");
        memberController.printMemberList();


        System.out.println("Enter the memberID for the membership");
        int memberID = scan.nextInt();
        membership.setMemberID(memberID);
        scan.nextLine();
        System.out.println("Enter the staffID of the registrationoperator");
        int staffID = scan.nextInt();
        membership.setStaffID(staffID);
        scan.nextLine();

        System.out.println("Enter the membership level:");
        System.out.println("Gold (G)");
        System.out.println("Platinum (P)");
        levelString = scan.nextLine();

        System.out.println("Enter the membership's status");
        System.out.println("Active (1)");
        System.out.println("Inactive (2)");
        int statusInt = scan.nextInt();
        boolean status = statusInt == 1;
        membership.setStatus(status);
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
        Timestamp startDate = Utility.getTimestampObject(year, month - 1, day);
        membership.setStartDate(startDate);

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
        Timestamp endDate = Utility.getTimestampObject(yearEnd, monthEnd - 1, dayEnd);
        membership.setEndDate(endDate);

        level = Level.GOLD;
        if (levelString.charAt(0) == 'G') {
            level = Level.GOLD;
        } else if  (levelString.charAt(0) == 'P') {
            level = Level.PLATINUM;
        }
        membership.setLevel(level);
        membershipController.updateMembershipInformation(membership);
    }

    static void deleteMembership() throws SQLException {
        System.out.println("Here are all of the memberships in the system");
        membershipController.printMembershipList();
        System.out.println("Which membership would you like to delete?");
        int membershipID = scan.nextInt();
        scan.nextLine();
        membershipController.deleteMembershipInformation(membershipID);
    }





    private static void addInventory() throws SQLException {
        System.out.println("Enter the amount of inventory for the product");
        int amount = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the price of the product");
        float price = scan.nextFloat();
        scan.nextLine();

        System.out.println("Here is a list of all products in the system:");
        productController.printProductList();

        System.out.println("Enter the productID of the product this inventory is for");
        int productID = scan.nextInt();
        scan.nextLine();


        System.out.println("Enter the inventory's expiration date");
        System.out.print("Year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        int month =scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int day = scan.nextInt();
        scan.nextLine();
        Timestamp expirationDate = Utility.getTimestampObject(year, month - 1, day);

        System.out.println("Enter the inventory's manufacturing date");
        System.out.print("Year: ");
        year = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        month =scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        day = scan.nextInt();
        scan.nextLine();
        Timestamp manufacturingDate = Utility.getTimestampObject(year, month - 1, day);

        System.out.println("Is this inventory for a store or a warehouse?");
        System.out.println("Store (s)");
        System.out.println("Warehouse (w)");
        String inventoryType = scan.nextLine();

        Inventory inventory = null;

        if (inventoryType.charAt(0) == 's') {
            System.out.println("Here are all of the stores in the system:");
            storeController.printStoreList();
            System.out.println("Enter the storeID for this inventory entry");
            int storeID = scan.nextInt();
            scan.nextLine();
            inventory = new StoreInventory(-1, amount, price, productID, expirationDate, manufacturingDate, storeID);
        } else if (inventoryType.charAt(0) == 'w') {
            System.out.println("Here are all of the warehouses in the system:");
            warehouseController.printWarehouseList();
            System.out.println("Enter the warehouseID for this inventory entry");
            int warehouseID = scan.nextInt();
            scan.nextLine();
            inventory = new WarehouseInventory(-1, amount, price, productID, expirationDate, manufacturingDate, warehouseID);
        }
        assert inventory != null;
        inventoryController.enterInventoryInformation(inventory);
    }

    static void updateInventory() throws SQLException {
        System.out.println("Here is all of the inventory in the system");
        inventoryController.printInventoryList();
        System.out.println("Which inventory would you like to edit");
        int inventoryID = scan.nextInt();

        PreparedStatement preparedStatement = connection.prepareStatement(InventoryController.SELECT_ALL);
        ResultSet set = preparedStatement.executeQuery();
        Inventory inventory = null;
        if (set.next()) {
            if (set.getInt("warehouseID") != 0) {
                inventory = new WarehouseInventory(inventoryID, set.getInt("amount"),
                        set.getFloat("price"), set.getInt("productID"),
                        set.getTimestamp("expirationDate"), set.getTimestamp("manufacturingDate"),
                        set.getInt("warehouseID"));
            } else if (set.getInt("storeID") != 0) {
                inventory = new StoreInventory(inventoryID, set.getInt("amount"),
                        set.getFloat("price"), set.getInt("productID"),
                        set.getTimestamp("expirationDate"), set.getTimestamp("manufacturingDate"),
                        set.getInt("storeID"));
            }


            System.out.println("Enter the amount of inventory for the product");
            int amount = scan.nextInt();
            scan.nextLine();
            assert inventory != null;
            inventory.setAmount(amount);
            System.out.println("Enter the price of the product");
            float price = scan.nextFloat();
            scan.nextLine();
            inventory.setPrice(price);

            System.out.println("Here is a list of all products in the system:");
            productController.printProductList();

            System.out.println("Enter the productID of the product this inventory is for");
            int productID = scan.nextInt();
            scan.nextLine();
            inventory.setProductID(productID);


            System.out.println("Enter the inventory's expiration date");
            System.out.print("Year: ");
            int year = scan.nextInt();
            scan.nextLine();
            System.out.print("Month: ");
            int month =scan.nextInt();
            scan.nextLine();
            System.out.print("Day: ");
            int day = scan.nextInt();
            scan.nextLine();
            Timestamp expirationDate = Utility.getTimestampObject(year, month - 1, day);
            inventory.setExpirationDate(expirationDate);
            System.out.println("Enter the inventory's manufacturing date");
            System.out.print("Year: ");
            year = scan.nextInt();
            scan.nextLine();
            System.out.print("Month: ");
            month =scan.nextInt();
            scan.nextLine();
            System.out.print("Day: ");
            day = scan.nextInt();
            scan.nextLine();
            Timestamp manufacturingDate = Utility.getTimestampObject(year, month - 1, day);
            inventory.setManufacturingDate(manufacturingDate);

            inventoryController.updateInventoryInformation(inventory);

        }





    }

//    static void deleteInventory() throws SQLException {
//        System.out.println("Here are all of the inventory in the system");
//        inventoryController.printInventoryList();
//        System.out.println("Which inventory would you like to delete?");
//        int inventoryID = scan.nextInt();
//        scan.nextLine();
//        supplierController.deleteSupplierInformation(inventoryID);
//    }













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