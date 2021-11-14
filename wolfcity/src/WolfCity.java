import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import controllers.*;
import models.*;
import utilities.Utility;

/**
 * This is the main class of the program.
 * Its purpose is to prompt the user for input to complete the required operations
 * When prompted for input that requires the referencing of an existing key from another relation
 * a list of the elements in said relation will be printed so the user has a choice.
 */
public class WolfCity {

    // Update your user info alone here
    private static final String         jdbcURL  = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/srmaale"; // Using
                                                                                                        // SERVICE_NAME

    // Update your user and password info here!

    private static final String         user     = "srmaale";
    private static final String         password = "wolfpacksai";
    /** Scanner for user input */
    private static Scanner              scan;
    /** Connection to the MariaDB Database */
    private static Connection           connection;
    /** Static instance of classes for SQL operations */
    private static MemberController     memberController;
    private static StaffController      staffController;
    private static StoreController      storeController;
    private static DiscountController   discountController;
    private static ProductController    productController;
    private static WarehouseController  warehouseController;
    private static SupplierController   supplierController;
    private static MembershipController membershipController;
    private static InventoryController inventoryController;
    private static TransactionController transactionController;
    private static ShipmentController shipmentController;
    private static BillController billController;
    private static ReportController reportController;

    public static void main ( final String[] args ) {
        try {
            // Loading the driver. This creates an instance of the driver
            // and calls the registerDriver method to make MySql(MariaDB) Thin
            // available to clients.
            Class.forName( "org.mariadb.jdbc.Driver" );
            connection = null;

            try {
                // Get a connection instance from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL
                connection = DriverManager.getConnection( jdbcURL, user, password );
                memberController = new MemberController( connection );
                staffController = new StaffController( connection );
                storeController = new StoreController( connection );
                discountController = new DiscountController( connection );
                productController = new ProductController( connection );
                warehouseController = new WarehouseController( connection );
                supplierController = new SupplierController( connection );
                memberController = new MemberController( connection );
                membershipController = new MembershipController( connection );
                inventoryController = new InventoryController( connection );
                shipmentController = new ShipmentController( connection );
                transactionController = new TransactionController( connection );
                billController = new BillController(connection);
                reportController = new ReportController(connection);

                while ( true ) {
                    System.out.println( "What operation would you like to perform?" );
                    System.out.println( "1. Add a member to the system" );
                    System.out.println( "2. Edit a member" );
                    System.out.println( "3. Delete a member" );
                    System.out.println( "4. Add a staff member to the system" );
                    System.out.println( "5. Edit a staff member" );
                    System.out.println( "6. Delete a staff member" );
                    System.out.println( "7. Add a store to the system" );
                    System.out.println( "8. Edit a store" );
                    System.out.println( "9. Delete a store" );
                    System.out.println( "10. Add a discount to the system" );
                    System.out.println( "11. Edit a discount" );
                    System.out.println( "12. Delete a discount" );
                    System.out.println( "13. Add a product to the system" );
                    System.out.println( "14. Edit a product" );
                    System.out.println( "15. Delete a product" );
                    System.out.println( "16. Add a warehouse to the system" );
                    System.out.println( "17. Edit a warehouse" );
                    System.out.println( "18. Delete a warehouse" );
                    System.out.println( "19. Add a supplier to the system" );
                    System.out.println( "20. Edit a supplier" );
                    System.out.println( "21. Delete a supplier" );
                    System.out.println( "22. Add a membership to the system" );
                    System.out.println( "23. Edit a membership" );
                    System.out.println( "24. Delete a membership" );
                    System.out.println( "25. Add inventory" );
                    System.out.println( "26. Update inventory" );
                    System.out.println( "27. Add a transaction to the system" );
                    System.out.println( "28. Edit a transaction" );
                    System.out.println( "29. Delete a transaction" );
                    System.out.println( "30. Add a bill to the system" );
                    System.out.println( "31. Edit a bill" );
                    System.out.println( "32. Delete a bill" );
                    System.out.println( "33. Generate RewardsCheck" );
                    System.out.println( "34. Make a transfer" );
                    System.out.println( "35. Create a shipment" );
                    System.out.println( "36. Generate a Sales Report" );
                    System.out.println( "37. Generate a Merchandise Report" );
                    System.out.println( "38. Generate a Customer Report" );

                    scan = new Scanner(System.in);
                    int num = scan.nextInt();
                    scan.nextLine();
                    // Choices for the user input
                    if ( num == 1 ) {
                        addMember();
                    }
                    else if ( num == 2 ) {
                        editMember();
                    }
                    else if ( num == 3 ) {
                        deleteMember();
                    }
                    else if ( num == 4 ) {
                        addStaff();
                    }
                    else if ( num == 5 ) {
                        editStaff();
                    }
                    else if ( num == 6 ) {
                        deleteStaff();
                    }
                    else if ( num == 7 ) {
                        addStore();
                    }
                    else if ( num == 8 ) {
                        editStore();
                    }
                    else if ( num == 9 ) {
                        deleteStore();
                    }
                    else if ( num == 10 ) {
                        addDiscount();
                    }
                    else if ( num == 11 ) {
                        editDiscount();
                    }
                    else if ( num == 12 ) {
                        deleteDiscount();
                    }
                    else if ( num == 13 ) {
                        addProduct();
                    }
                    else if ( num == 14 ) {
                        editProduct();
                    }
                    else if ( num == 15 ) {
                        deleteProduct();
                    }
                    else if ( num == 16 ) {
                        addWarehouse();
                    }
                    else if ( num == 17 ) {
                        editWarehouse();
                    }
                    else if ( num == 18 ) {
                        deleteWarehouse();
                    }
                    else if ( num == 19 ) {
                        addSupplier();
                    }
                    else if ( num == 20 ) {
                        editSupplier();
                    }
                    else if ( num == 21 ) {
                        deleteSupplier();
                    }
                    else if ( num == 22 ) {
                        addMembership();
                    }
                    else if ( num == 23 ) {
                        editMembership();
                    }
                    else if ( num == 24 ) {
                        deleteMembership();
                    }
                    else if ( num == 25 ) {
                        addInventory();
                    }
                    else if ( num == 26 ) {
                        updateInventory();
                    }
                    else if ( num == 27 ) {
                    	addTransaction();
                    }
                    else if ( num == 28 ) {
                    	editTransaction();
                    }
                    else if ( num == 29 ) {
                    	deleteTransaction();
                    }
                    else if ( num == 30 ) {
                        addBill();
                    }
                    else if ( num == 31 ) {
                        editBill();
                    }
                    else if ( num == 32 ) {
                        deleteBill();
                    }
                    else if ( num == 33 ) {
                        addRewardsCheck();
                    }
                    else if (num == 34) {
                        addTransfer();
                    }
                    else if (num == 35) {
                        addShipment();
                    }
                    else if ( num == 36 ) {
                        getSalesReport();
                    }
                    else if (num == 37) {
                        getMerchandiseReport();
                    }
                    else if (num == 38) {
                        getCustomerReport();
                    }
                }
            }
            finally {
                close( connection );
            }
        }
        catch ( final Throwable oops ) {
            oops.printStackTrace();
        }
    }

    /**
     * Gets a sales report for a given date range, month or year
     * @throws SQLException e
     */
    static void getSalesReport() throws SQLException {
        System.out.println( "Date Range, Month or Year Report?" );
        System.out.println( "Date (d)");
        System.out.println( "Month: (m)" );
        System.out.println( "Year: (y)" );
        String choice = scan.next();
        char choiceChar = choice.charAt(0);
        if (choiceChar == 'd') {
            System.out.println( "Enter the start date" );
            System.out.print( "Year: " );
            final int year = scan.nextInt();
            scan.nextLine();
            System.out.print( "Month: " );
            final int month = scan.nextInt();
            scan.nextLine();
            System.out.print( "Day: " );
            final int day = scan.nextInt();
            scan.nextLine();
            final Timestamp startDate = Utility.getTimestampObject( year, month - 1, day );

            System.out.println( "Enter the end date" );
            System.out.print( "Year: " );
            final int yearEnd = scan.nextInt();
            scan.nextLine();
            System.out.print( "Month: " );
            final int monthEnd = scan.nextInt();
            scan.nextLine();
            System.out.print( "Day: " );
            final int dayEnd = scan.nextInt();
            scan.nextLine();
            final Timestamp endDate = Utility.getTimestampObject( yearEnd, monthEnd - 1, dayEnd );

            reportController.getSalesReportByDate(startDate, endDate);

        } else if (choiceChar == 'm') {
            System.out.println("Print the month (1-12)");
            int month = scan.nextInt();
            scan.nextLine();

            System.out.println("Print the year");
            int year = scan.nextInt();
            scan.nextLine();

            reportController.getSalesReportByMonth(year, month);

        } else if (choiceChar == 'y') {
            System.out.println("Print the year");
            int year = scan.nextInt();
            scan.nextLine();

            reportController.getSalesReportByYear(year);
        }
    }

    /**
     * Gets a merchandise report for a given product or store
     * @throws SQLException e
     */
    static void getMerchandiseReport() throws SQLException {
        System.out.println( "Get report for a product or a store?" );
        System.out.println( "Product (p)");
        System.out.println( "Store: (s)" );
        String choice = scan.next();
        char choiceChar = choice.charAt(0);

        if (choiceChar == 'p') {
            System.out.println( "Here are all of the products in the system" );
            productController.printProductList();
            int productID = scan.nextInt();
            scan.nextLine();
            reportController.getMerchandiseStockReportByProduct(productID);
        } else if (choiceChar == 's') {
            System.out.println( "Here are all of the stores in the system" );
            storeController.printStoreList();
            System.out.println("Which store should the report be for?");
            int storeID = scan.nextInt();
            scan.nextLine();
            reportController.getMerchandiseStockReportByStore(storeID);
        }
    }

    /**
     * Gets a customer report for monthly or yearly growth, or gets a total purchase amount for a given customer
     * @throws SQLException e
     */
    static void getCustomerReport() throws SQLException {
        System.out.println( "Monthly or Yearly Customer Growth Report, or Total Purchase Amount for Customer Report?" );
        System.out.println( "Month: (m)" );
        System.out.println( "Year: (y)" );
        System.out.println( "Total Purchase Amount for a customer: (t)" );
        String choice = scan.next();
        char choiceChar = choice.charAt(0);

        if (choiceChar == 't') {
            System.out.println( "Here are all of the members in the system:" );
            memberController.printMemberList();
            System.out.println( "Which member should the report be for?" );
            int memberID = scan.nextInt();
            scan.nextLine();
            reportController.getCustomerTotalPurchaseAmount(memberID);
        } else if (choiceChar == 'm') {
            System.out.println("Print the month (1-12)");
            int month = scan.nextInt();
            scan.nextLine();
            System.out.println("Print the year");
            int year = scan.nextInt();
            scan.nextLine();
            reportController.getCustomerGrowthByMonth(year, month);
        } else if (choiceChar == 'y') {
            System.out.println("Print the year");
            int year = scan.nextInt();
            scan.nextLine();
            reportController.getCustomerGrowthByYear(year);
        }
    }




    /**
     * Delete a transaction
     * @throws SQLException e
     */
    static void deleteTransaction() throws SQLException {
    	System.out.println("Here are all of the Transactions in the system");
        transactionController.printTransactionList();
        System.out.println("Which transaction would you like to delete?");
        int transactionID = scan.nextInt();
        scan.nextLine();
        transactionController.deleteTransactionInformation(transactionID);
	}

    /**
     * Edit a transaction
     * @throws SQLException e
     */
	static void editTransaction() throws SQLException {
		System.out.println("Here are a list of all of the transactions in the system");
		transactionController.printTransactionList();
		
		int transactionID = scan.nextInt();
        String query = "SELECT * FROM Transaction where transactionID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, transactionID);
        ResultSet set = st.executeQuery();
        set.next();

        transactionID = set.getInt("transactionID");
        float totalPrice = set.getFloat("totalPrice");
        int storeID = set.getInt("storeID");
        int memberID = set.getInt("memberID");
        int staffID = set.getInt("staffID");
        Timestamp purchaseDate = set.getTimestamp("purchaseDate");

        query = "SELECT * FROM TransactionProducts where transactionID = ?";
        st = connection.prepareStatement(query);
        st.setInt(1, transactionID);
        set = st.executeQuery();
        List<TransactionProduct> products = new ArrayList<>();
        while (set.next()) {
            transactionID = set.getInt("transactionID");
            int productID = set.getInt("productID");
            int quantity = set.getInt("quantity");
            TransactionProduct tp = new TransactionProduct(transactionID,productID, quantity);
            products.add(tp);
        }


        Transaction t = new Transaction(transactionID, totalPrice, storeID, memberID, staffID, purchaseDate, products);
        scan.nextLine();
        System.out.println("Here are all of the products in the system:");
        productController.printProductList();
        System.out.println("Edit the products in this transaction:");
        while (1 == 1) {

            System.out.println("Enter ID of the quantity you would like to change");
            int productID = scan.nextInt();
            scan.nextLine();
            if (productID == -1) {
                break;
            }
            System.out.println("Enter ID of the quantity you would like to change");
            int quantity = scan.nextInt();
            scan.nextLine();
            if (quantity == -1) {
                break;
            }

            for (TransactionProduct tp : t.getProducts()) {
                if (productID == tp.getProductID()) {
                    tp.setQuantity(quantity);
                }
            }
        }

        transactionController.updateTransactionInformation(t);
		
	}

    /**
     * Add a transaction to the system
     * @throws SQLException e
     */
	static void addTransaction() throws SQLException {
        System.out.println("Here are a list of all of the stores in the system");
        storeController.printStoreList();
		System.out.println("Enter store ID");
		int storeID = scan.nextInt();
        System.out.println("Here are a list of all of the members in the system");
        memberController.printMemberList();
		System.out.println("Enter member ID");
		int memberID = scan.nextInt();
        System.out.println("Here are a list of all of the cashiers in the system");
        staffController.printCashierList();
		System.out.println("Enter staff ID");
		int staffID = scan.nextInt();

        List<TransactionProduct> products = new ArrayList<>();
        System.out.println("Here are a list of all of the products in the system");
        productController.printProductList();
        while (1 == 1) {
            System.out.println("Enter -1 to stop entering products");
            System.out.println("Enter product ID");
            int productID = scan.nextInt();
            if (productID == -1)
                break;
            scan.nextLine();
            System.out.println("Enter -1 to stop entering products");
            System.out.println("Enter quantity");
            int quantity = scan.nextInt();
            if (quantity == -1)
                break;
            scan.nextLine();
            TransactionProduct tp = new TransactionProduct(-1,  productID, quantity);
            products.add(tp);
        }

		Transaction t = new Transaction(-1, -1, storeID, memberID, staffID, new Timestamp(System.currentTimeMillis()), products);
		transactionController.enterTransactionInformation(t);
	}

    /**
     * Add member
     * @throws SQLException e
     */
    static void addMember () throws SQLException {
        System.out.println( "Here are all of the registration operators in the system" );
        staffController.printRegistrationOperatorList();
        System.out.println( "Enter the registration operator's staffID" );
        final int staffID = scan.nextInt();
        scan.nextLine();
        System.out.println( "Enter the member's first name" );
        final String firstName = scan.nextLine();
        System.out.println( "Enter the member's last name" );
        final String lastName = scan.nextLine();
        System.out.println( "Enter the member's email" );
        final String email = scan.nextLine();
        System.out.println( "Enter the member's address" );
        final String address = scan.nextLine();
        System.out.println( "Enter member's phone number" );
        final String phone = scan.nextLine();
        final Member member = new Member( -1, staffID, firstName, lastName, "Active", email, address, phone );
        memberController.enterMemberInformation( member );
    }

    /**
     * Edit a member
     * @throws SQLException e
     */
    static void editMember () throws SQLException {
        System.out.println( "Here are all of the Members in the system" );

        memberController.printMemberList();
        System.out.println( "Which member would you like to edit" );

        final int memberID = scan.nextInt();
        final String query = "SELECT * FROM Member where memberID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, memberID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        final Member member = new Member( set.getInt( "memberID" ), set.getInt( "staffID" ),
                set.getString( "firstName" ), set.getString( "lastName" ), set.getString( "activeStatus" ),
                set.getString( "email" ), set.getString( "address" ), set.getString( "phoneNumber" ) );
        scan.nextLine();
        System.out.println( "Edit this member's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter the member's first name" );
        final String firstName = scan.nextLine();
        member.setFirstName( firstName );
        System.out.println( "Enter member's last name" );
        final String lastName = scan.nextLine();
        member.setLastName( lastName );
        System.out.println( "Enter the member's email" );
        final String email = scan.nextLine();
        member.setEmail( email );
        System.out.println( "Enter the member's address" );
        final String address = scan.nextLine();
        member.setAddress( address );
        System.out.println( "Enter member's phone number" );
        final String phone = scan.nextLine();
        member.setPhoneNumber( phone );
        System.out.println( "Enter the member's active status (y/n)" );
        final String activeStatus = scan.nextLine();
        final char ch = activeStatus.charAt( 0 );
        if ( ch == 'y' ) {
            member.setActiveStatus( "Active" );
        }
        else {
            member.setActiveStatus( "Inactive" );
        }
        memberController.updateMemberInformation( member );
    }

    /**
     * Delete a member
     * @throws SQLException e
     */
    static void deleteMember () throws SQLException {
        System.out.println( "Here are all of the Members in the system" );
        memberController.printMemberList();
        System.out.println( "Which member would you like to delete?" );
        final int memberID = scan.nextInt();
        scan.nextLine();
        memberController.deleteMemberInformation( memberID );
    }

    /**
     * Delete a staff member from the system
     * @throws SQLException e
     */
    static void deleteStaff () throws SQLException {
        System.out.println( "Here are all of the staff members in the system" );
        staffController.printStaffList();
        System.out.println( "Which member would you like to delete?" );
        final int staffID = scan.nextInt();
        scan.nextLine();
        staffController.deleteStaffInformation( staffID );
    }

    /**
     * Edit staff
     * @throws SQLException e
     */
    static void editStaff () throws SQLException {
        System.out.println( "Here are all of the staff members in the system" );
        staffController.printStaffList();
        System.out.println( "Which staff member would you like to edit" );
        final int staffID = scan.nextInt();
        scan.nextLine();
        final String query = "SELECT * FROM Staff where staffID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, staffID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        StaffType type = null;
        String jobTitle = set.getString("jobTitle");

        if (jobTitle.equals("Admin")) {
            type = StaffType.ADMIN;
        } else if (jobTitle.equals("RegistrationOperator")) {
            type = StaffType.REGISTRATION_OPERATOR;
        } else if (jobTitle.equals("WarehouseOperator")) {
            type = StaffType.WAREHOUSE_OPERATOR;
        } else if (jobTitle.equals("BillingStaff")) {
            type = StaffType.BILLING_STAFF;
        } else if (jobTitle.equals("Cashier")) {
            type = StaffType.CASHIER;
        }

        final Staff staff = new Staff( set.getInt( "staffID" ), set.getString( "name" ), set.getInt( "age" ),
                set.getInt( "storeID" ), set.getString( "homeAddress" ), set.getString( "jobTitle" ),
                set.getString( "phoneNumber" ), set.getString( "email" ), set.getTimestamp( "employmentDate" ), type );

        System.out.println( "Edit this member's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter staff member's name" );
        final String name = scan.nextLine();
        staff.setName( name );
        System.out.println( "Enter staff member's age" );
        final int age = scan.nextInt();
        scan.nextLine();
        staff.setAge( age );
        System.out.println( "Enter staff member's store" );
        final int storeID = scan.nextInt();
        scan.nextLine();
        staff.setStoreID( storeID );
        System.out.println( "Enter staff member's address" );
        final String address = scan.nextLine();
        staff.setHomeAddress( address );
        System.out.println( "Enter staff member's job phone number" );
        final String phone = scan.nextLine();
        staff.setPhoneNumber( phone );
        System.out.println( "Enter staff member's email" );
        final String email = scan.nextLine();
        staff.setEmail( email );
        staffController.updateStaffInformation( staff );
    }

    /**
     * Add staff of all kinds to the system
     * @throws SQLException e
     */
    private static void addStaff () throws SQLException {
        System.out.println( "Enter the staff member's name" );
        final String name = scan.nextLine();
        System.out.println( "Enter the staff member's age" );
        final int age = scan.nextInt();
        scan.nextLine();

        System.out.println( "Here are all of the stores in the system" );
        storeController.printStoreList();

        System.out.println( "Enter the storeID attached to the staff member" );
        final int storeID = scan.nextInt();
        scan.nextLine();
        System.out.println( "Enter staff member's address" );
        final String address = scan.nextLine();
        System.out.println(
                "Enter staff member's job title (Admin, RegistrationOperator, WarehouseOperator, Cashier, BillingStaff" );
        final String jobTitle = scan.nextLine();
        System.out.println( "Enter the staff member's phone number" );
        final String phone = scan.nextLine();
        System.out.println( "Enter staff member's email" );
        final String email = scan.nextLine();

        StaffType type = null;

        if (jobTitle.equals("Admin")) {
            type = StaffType.ADMIN;
        } else if (jobTitle.equals("RegistrationOperator")) {
            type = StaffType.REGISTRATION_OPERATOR;
        } else if (jobTitle.equals("WarehouseOperator")) {
            type = StaffType.WAREHOUSE_OPERATOR;
        } else if (jobTitle.equals("BillingStaff")) {
            type = StaffType.BILLING_STAFF;
        } else if (jobTitle.equals("Cashier")) {
            type = StaffType.CASHIER;
        }

        final Staff staff = new Staff( -1, name, age, storeID, address, jobTitle, phone, email,
                java.sql.Timestamp.valueOf( LocalDateTime.now() ), type );
        staffController.enterStaffInformation( staff );
    }

    /**
     * Add a store from the system
     * @throws SQLException e
     */
    private static void addStore () throws SQLException {
        System.out.println( "Enter the store's address" );
        final String address = scan.nextLine();
        System.out.println( "Enter the store's phone number" );
        final String phone = scan.nextLine();

        System.out.println("Enter the store's manager (-1 for none)");
        int managerID = scan.nextInt();
        scan.nextLine();

        final Store store = new Store( -1, phone, address, managerID );
        storeController.enterStoreInformation( store );
    }

    /**
     * Edit a store
     * @throws SQLException e
     */
    static void editStore () throws SQLException {
        System.out.println( "Here are all of the stores in the system" );
        storeController.printStoreList();
        System.out.println( "Which store would you like to edit" );
        final int storeID = scan.nextInt();
        final String query = "SELECT * FROM Store where storeID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, storeID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );


        final Store store = new Store( set.getInt( "storeID" ), set.getString( "phoneNumber" ),
                set.getString( "address" ), set.getInt("staffID") );
        scan.nextLine();
        System.out.println( "Edit this store's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter the store's address" );
        final String address = scan.nextLine();
        store.setAddress( address );
        System.out.println( "Enter the store's phone number" );
        final String phone = scan.nextLine();
        store.setPhone( phone );
        System.out.println( "Enter the store's manager" );
        final int staffID = scan.nextInt();
        scan.nextLine();
        store.setStaffID( staffID );
        storeController.updateStoreInformation( store );
    }

    /**
     * Delete a store from the system
     * @throws SQLException e
     */
    static void deleteStore () throws SQLException {
        System.out.println( "Here are all of the stores in the system" );
        storeController.printStoreList();
        System.out.println( "Which store would you like to delete?" );
        final int storeID = scan.nextInt();
        scan.nextLine();
        storeController.deleteStoreInformation( storeID );
    }


    /**
     * Add a bill
     * @throws SQLException e
     */
    private static void addBill () throws SQLException {
        System.out.println( "Here are all of the billing staff in the system" );
        staffController.printBillingStaffList();
        System.out.println( "Enter the billing staff's ID" );
        final int staffID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Here are all of the suppliers in the system" );
        supplierController.printSupplierList();
        System.out.println( "Enter the supplier's ID" );
        final int supplierID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the cost of the bill" );
        final float amount = scan.nextFloat();
        scan.nextLine();

        System.out.println( "Enter the bill's issue date" );
        System.out.print( "Year: " );
        final int year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int day = scan.nextInt();
        scan.nextLine();
        final Timestamp issueDate = Utility.getTimestampObject( year, month - 1, day );


        System.out.println( "Enter the bill's due date" );
        System.out.print( "Year: " );
        final int yearDue = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int monthDue = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int dayDue = scan.nextInt();
        scan.nextLine();
        final Timestamp dueDate = Utility.getTimestampObject( yearDue, monthDue - 1, dayDue );

        Bill bill = new Bill(-1, staffID, supplierID, amount, issueDate, dueDate);
        billController.createBillInformation(bill);
    }

    /**
     * Edit a bill
     * @throws SQLException e
     */
    static void editBill () throws SQLException {
        System.out.println( "Here are all of the bills in the system" );
        billController.printBillList();
        System.out.println( "Which bill would you like to edit" );
        final int billID = scan.nextInt();
        final String query = "SELECT * FROM Bill where billID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, billID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        Bill bill = new Bill( set.getInt( "billID" ), set.getInt( "staffID" ),
                set.getInt( "supplierID" ), set.getFloat( "amount" ),
                set.getTimestamp("issueDate"),set.getTimestamp("dueDate") );
        scan.nextLine();
        System.out.println( "Edit this bill's amount" );
        float amount = scan.nextInt();
        scan.nextLine();
        bill.setAmount(amount);

        billController.updateBillInformation( bill );
    }

    /**
     * Delete a bill
     * @throws SQLException e
     */
    static void deleteBill () throws SQLException {
        System.out.println( "Here are all of the bills in the system" );
        billController.printBillList();
        System.out.println( "Which bill would you like to delete?" );
        final int billID = scan.nextInt();
        scan.nextLine();
        billController.deleteBillInformation( billID );
    }


    /**
     * Create a rewards check
     * @throws SQLException e
     */
    private static void addRewardsCheck () throws SQLException {
        System.out.println( "Here are all of the billing staff in the system" );
        staffController.printBillingStaffList();
        System.out.println( "Enter the billing staff's ID" );
        final int staffID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Here are all of the members in the system" );
        staffController.printBillingStaffList();
        System.out.println( "Enter the member's ID" );
        final int memberID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the amount on the check" );
        final float amount = scan.nextFloat();
        scan.nextLine();

        RewardsCheck check = new RewardsCheck(-1, staffID, memberID, amount);
        billController.generateRewardsCheck(check);
    }


    /**
     * Add a discount
     * @throws SQLException e
     */
    private static void addDiscount () throws SQLException {
        System.out.println( "Here are all of the products in the system" );
        productController.printProductList();
        System.out.println( "Enter the product to which the discount is applied:" );
        final int productID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the percent off on the discount" );
        final int percentOff = scan.nextInt();
        scan.nextLine();
        System.out.println( "Enter the discount's start date" );
        System.out.print( "Year: " );
        final int year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int day = scan.nextInt();
        scan.nextLine();
        final Timestamp startDate = Utility.getTimestampObject( year, month - 1, day );

        System.out.println( "Enter the discount's end date" );
        System.out.print( "Year: " );
        final int yearEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int monthEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int dayEnd = scan.nextInt();
        scan.nextLine();
        final Timestamp endDate = Utility.getTimestampObject( yearEnd, monthEnd - 1, dayEnd );

        final Discount discount = new Discount( -1, productID, percentOff, startDate, endDate );
        discountController.enterDiscountInformation( discount );
    }

    /**
     * Edit a discount from the system
     * @throws SQLException e
     */
    static void editDiscount () throws SQLException {
        System.out.println( "Here are all of the discounts in the system" );
        discountController.printDiscountList();
        System.out.println( "Which discount would you like to edit" );
        final int discountID = scan.nextInt();
        final String query = "SELECT * FROM Discount where discountID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, discountID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        final Discount discount = new Discount( set.getInt( "discountID" ), set.getInt( "productID" ),
                set.getInt( "percentOff" ), set.getTimestamp( "startDate" ), set.getTimestamp( "endDate" ) );
        System.out.println( "Enter the product to which the discount is applied:" );
        final int productID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the percent off on the discount" );
        final int percentOff = scan.nextInt();
        scan.nextLine();
        System.out.println( "Enter the discount's start date" );
        System.out.print( "Year: " );
        final int year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int day = scan.nextInt();
        scan.nextLine();
        final Timestamp startDate = Utility.getTimestampObject( year, month - 1, day );
        System.out.print( startDate.toString() );
        System.out.println( "Enter the discount's end date" );
        System.out.print( "Year: " );
        final int yearEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int monthEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int dayEnd = scan.nextInt();
        scan.nextLine();
        final Timestamp endDate = Utility.getTimestampObject( yearEnd, monthEnd - 1, dayEnd );
        System.out.print( endDate.toString() );
        discount.setProductID( productID );
        discount.setEndDate( endDate );
        discount.setStartDate( startDate );
        discount.setPercentOff( percentOff );
        discountController.updateDiscountInformation( discount );
    }

    /**
     * Delete a discount from the system
     * @throws SQLException e
     */
    static void deleteDiscount () throws SQLException {
        System.out.println( "Here are all of the discounts in the system" );
        discountController.printDiscountList();
        System.out.println( "Which discount would you like to delete?" );
        final int discountID = scan.nextInt();
        scan.nextLine();
        discountController.deleteDiscountInformation( discountID );
    }

    /**
     * Add a product to the system
     * @throws SQLException e
     */
    private static void addProduct () throws SQLException {
        System.out.println( "Enter the product's name" );
        final String productName = scan.nextLine();
        System.out.println( "Here are all of the suppliers in the system:" );
        supplierController.printSupplierList();
        System.out.println( "Enter the supplier's id:" );
        final int supplierID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the product's store price:" );
        final float price = scan.nextFloat();
        scan.nextLine();

        System.out.println( "Enter the product's wholesale price:" );
        final float buyPrice = scan.nextFloat();
        scan.nextLine();
        final Product product = new Product( -1, productName, supplierID, price, buyPrice );
        productController.enterProductInformation( product );
    }

    /**
     * Edit a product from the system
     * @throws SQLException e
     */
    static void editProduct () throws SQLException {
        System.out.println( "Here are all of the products in the system" );
        productController.printProductList();
        System.out.println( "Which product would you like to edit" );
        final int storeID = scan.nextInt();
        final String query = "SELECT * FROM Product where productID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, storeID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        final Product product = new Product( set.getInt( "productID" ), set.getString( "productName" ),
                set.getInt( "supplierID" ), set.getFloat("price") , set.getFloat("buyPrice"));
        scan.nextLine();
        System.out.println( "Edit this store's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter the product's name" );
        final String productName = scan.nextLine();
        product.setProductName( productName );
        System.out.println( "Enter the product's store price:" );
        final float price = scan.nextInt();
        product.setPrice( price );
        scan.nextLine();
        System.out.println( "Enter the product's wholesale price:" );
        final float buyPrice = scan.nextInt();
        product.setBuyPrice( buyPrice );
        scan.nextLine();
        productController.updateProductInformation( product );
    }

    /**
     * Delete a product from the system
     * @throws SQLException e
     */
    static void deleteProduct () throws SQLException {
        System.out.println( "Here are all of the products in the system" );
        productController.printProductList();
        System.out.println( "Which product would you like to delete?" );
        final int productID = scan.nextInt();
        scan.nextLine();
        productController.deleteProductInformation( productID );
    }

    /**
     * Add a warehouse to the system
     * @throws SQLException e
     */
    private static void addWarehouse () throws SQLException {
        System.out.println( "Enter the warehouse's address" );
        final String address = scan.nextLine();
        final Warehouse warehouse = new Warehouse( -1, address );
        warehouseController.enterWarehouseInformation( warehouse );
    }

    /**
     * Edit a warehouse
     * @throws SQLException e
     */
    static void editWarehouse () throws SQLException {
        System.out.println( "Here are all of the warehouses in the system" );
        warehouseController.printWarehouseList();
        System.out.println( "Which warehouse would you like to edit" );
        final int warehouseID = scan.nextInt();
        final String query = "SELECT * FROM Warehouse where warehouseID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, warehouseID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        final Warehouse warehouse = new Warehouse( set.getInt( "warehouseID" ), set.getString( "address" ) );
        scan.nextLine();
        System.out.println( "Edit this warehouse's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter the warehouse's address" );
        final String address = scan.nextLine();
        warehouse.setAddress( address );
        warehouseController.updateWarehouseInformation( warehouse );
    }

    /**
     * Delete a warehouse from the system
     * @throws SQLException e
     */
    static void deleteWarehouse () throws SQLException {
        System.out.println( "Here are all of the warehouses in the system" );
        warehouseController.printWarehouseList();
        System.out.println( "Which warehouse would you like to delete?" );
        final int warehouseID = scan.nextInt();
        scan.nextLine();
        warehouseController.deleteWarehouseInformation( warehouseID );
    }

    /**
     * Add a supplier to the system
     * @throws SQLException e
     */
    private static void addSupplier () throws SQLException {
        System.out.println( "Enter the supplier's email" );
        final String email = scan.nextLine();
        System.out.println( "Enter the supplier's phone number" );
        final String phoneNumber = scan.nextLine();

        System.out.println( "Enter the supplier's address" );
        final String address = scan.nextLine();

        System.out.println( "Enter the supplier's name" );
        final String name = scan.nextLine();
        final Supplier supplier = new Supplier( -1, email, phoneNumber, address, name );
        supplierController.enterSupplierInformation( supplier );
    }

    /**
     * Edit a supplier
     * @throws SQLException e
     */
    static void editSupplier () throws SQLException {
        System.out.println( "Here are all of the suppliers in the system" );
        supplierController.printSupplierList();
        System.out.println( "Which supplier would you like to edit" );
        final int supplierID = scan.nextInt();
        final String query = "SELECT * FROM Supplier where supplierID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, supplierID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        final Supplier supplier = new Supplier( set.getInt( "supplierID" ), set.getString( "email" ),
                set.getString( "phoneNumber" ), set.getString( "address" ), set.getString( "name" ) );
        scan.nextLine();
        System.out.println( "Edit this supplier's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter the supplier's email" );
        final String email = scan.nextLine();
        supplier.setEmail( email );
        System.out.println( "Enter the supplier's phone number" );
        final String phoneNumber = scan.nextLine();
        supplier.setPhoneNumber( phoneNumber );
        System.out.println( "Enter the supplier's address" );
        final String address = scan.nextLine();
        supplier.setAddress( address );
        System.out.println( "Enter the supplier's name" );
        final String name = scan.nextLine();
        supplier.setName( name );
        supplierController.updateSupplierInformation( supplier );
    }

    /**
     * Delete a supplier from the system
     * @throws SQLException e
     */
    static void deleteSupplier () throws SQLException {
        System.out.println( "Here are all of the suppliers in the system" );
        supplierController.printSupplierList();
        System.out.println( "Which supplier would you like to delete?" );
        final int supplierID = scan.nextInt();
        scan.nextLine();
        supplierController.deleteSupplierInformation( supplierID );
    }

    /**
     * Add a membership for a user
     * @throws SQLException e
     */
    private static void addMembership () throws SQLException {

        System.out.println( "Here are all of the registration operators" );
        staffController.printRegistrationOperatorList();

        System.out.println( "Here are all of the members in the system" );
        memberController.printMemberList();

        System.out.println( "Enter the memberID for the membership" );
        final int memberID = scan.nextInt();
        scan.nextLine();
        System.out.println( "Enter the staffID of the registration operator" );
        final int staffID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the membership level:" );
        System.out.println( "Gold (G)" );
        System.out.println( "Platinum (P)" );
        final String levelString = scan.nextLine();

        System.out.println( "Enter the membership's status" );
        System.out.println( "Active (1)" );
        System.out.println( "Inactive (2)" );
        final int statusInt = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the membership's start date" );
        System.out.print( "Year: " );
        final int year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int day = scan.nextInt();
        scan.nextLine();
        final Timestamp startDate = Utility.getTimestampObject( year, month - 1, day );

        System.out.println( "Enter the membership's end date" );
        System.out.print( "Year: " );
        final int yearEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int monthEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int dayEnd = scan.nextInt();
        scan.nextLine();
        final Timestamp endDate = Utility.getTimestampObject( yearEnd, monthEnd - 1, dayEnd );

        final boolean status = statusInt == 1;

        Level level = Level.GOLD;
        if ( levelString.charAt( 0 ) == 'G' ) {
            level = Level.GOLD;
        }
        else if ( levelString.charAt( 0 ) == 'P' ) {
            level = Level.PLATINUM;
        }

        final Membership membership = new Membership( memberID, staffID, level, status, startDate, endDate );
        membershipController.enterMembershipInformation( membership );
    }

    /**
     * Edit a membership
     * @throws SQLException e
     */
    static void editMembership () throws SQLException {
        System.out.println( "Here are all of the memberships in the system" );
        membershipController.printMembershipList();
        System.out.println( "Which membership would you like to edit" );
        final int membershipID = scan.nextInt();
        final String query = "SELECT * FROM Membership where memberID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, membershipID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );

        String levelString = set.getString( "level" );
        Level level = Level.GOLD;
        if ( levelString.equals( "Gold" ) ) {
            level = Level.GOLD;
        }
        else if ( levelString.equals( "Platinum" ) ) {
            level = Level.PLATINUM;
        }

        final Membership membership = new Membership( set.getInt( "memberID" ), set.getInt( "staffID" ), level,
                set.getBoolean( "status" ), set.getTimestamp( "startDate" ), set.getTimestamp( "endDate" ) );

        scan.nextLine();
        System.out.println( "Edit this membership's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();

        System.out.println( "Here are all of the registration operators" );
        staffController.printRegistrationOperatorList();

        System.out.println( "Here are all of the members in the system" );
        memberController.printMemberList();

        System.out.println( "Enter the memberID for the membership" );
        final int memberID = scan.nextInt();
        membership.setMemberID( memberID );
        scan.nextLine();
        System.out.println( "Enter the staffID of the registrationoperator" );
        final int staffID = scan.nextInt();
        membership.setStaffID( staffID );
        scan.nextLine();

        System.out.println( "Enter the membership level:" );
        System.out.println( "Gold (G)" );
        System.out.println( "Platinum (P)" );
        levelString = scan.nextLine();

        System.out.println( "Enter the membership's status" );
        System.out.println( "Active (1)" );
        System.out.println( "Inactive (2)" );
        final int statusInt = scan.nextInt();
        final boolean status = statusInt == 1;
        membership.setStatus( status );
        scan.nextLine();

        System.out.println( "Enter the discount's start date" );
        System.out.print( "Year: " );
        final int year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int day = scan.nextInt();
        scan.nextLine();
        final Timestamp startDate = Utility.getTimestampObject( year, month - 1, day );
        membership.setStartDate( startDate );

        System.out.println( "Enter the discount's end date" );
        System.out.print( "Year: " );
        final int yearEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        final int monthEnd = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        final int dayEnd = scan.nextInt();
        scan.nextLine();
        final Timestamp endDate = Utility.getTimestampObject( yearEnd, monthEnd - 1, dayEnd );
        membership.setEndDate( endDate );

        level = Level.GOLD;
        if ( levelString.charAt( 0 ) == 'G' ) {
            level = Level.GOLD;
        }
        else if ( levelString.charAt( 0 ) == 'P' ) {
            level = Level.PLATINUM;
        }
        membership.setLevel( level );
        membershipController.updateMembershipInformation( membership );
    }

    /**
     * Delete a membership
     * @throws SQLException e
     */
    static void deleteMembership () throws SQLException {
        System.out.println( "Here are all of the memberships in the system" );
        membershipController.printMembershipList();
        System.out.println( "Which membership would you like to delete?" );
        final int membershipID = scan.nextInt();
        scan.nextLine();
        membershipController.deleteMembershipInformation( membershipID );
    }

    /**
     * Add inventory at a store or warehouse
     * @throws SQLException e
     */
    private static void addInventory () throws SQLException {
        System.out.println( "Enter the amount of inventory for the product" );
        final int amount = scan.nextInt();
        scan.nextLine();

        System.out.println( "Here is a list of all products in the system:" );
        productController.printProductList();

        System.out.println( "Enter the productID of the product this inventory is for" );
        final int productID = scan.nextInt();
        scan.nextLine();

        System.out.println( "Enter the inventory's expiration date" );
        System.out.print( "Year: " );
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        int month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        int day = scan.nextInt();
        scan.nextLine();
        final Timestamp expirationDate = Utility.getTimestampObject( year, month - 1, day );

        System.out.println( "Enter the inventory's manufacturing date" );
        System.out.print( "Year: " );
        year = scan.nextInt();
        scan.nextLine();
        System.out.print( "Month: " );
        month = scan.nextInt();
        scan.nextLine();
        System.out.print( "Day: " );
        day = scan.nextInt();
        scan.nextLine();
        final Timestamp manufacturingDate = Utility.getTimestampObject( year, month - 1, day );

        System.out.println( "Is this inventory for a store or a warehouse?" );
        System.out.println( "Store (s)" );
        System.out.println( "Warehouse (w)" );
        final String inventoryType = scan.nextLine();

        Inventory inventory = null;

        if ( inventoryType.charAt( 0 ) == 's' ) {
            System.out.println( "Here are all of the stores in the system:" );
            storeController.printStoreList();
            System.out.println( "Enter the storeID for this inventory entry" );
            final int storeID = scan.nextInt();
            scan.nextLine();
            inventory = new StoreInventory( -1, amount, productID, expirationDate, manufacturingDate, storeID );
        }
        else if ( inventoryType.charAt( 0 ) == 'w' ) {
            System.out.println( "Here are all of the warehouses in the system:" );
            warehouseController.printWarehouseList();
            System.out.println( "Enter the warehouseID for this inventory entry" );
            final int warehouseID = scan.nextInt();
            scan.nextLine();
            inventory = new WarehouseInventory( -1, amount, productID, expirationDate, manufacturingDate,
                    warehouseID );
        }
        assert inventory != null;
        inventoryController.enterInventoryInformation( inventory );
    }

    /**
     * Update inventory at a store or warehouse
     * @throws SQLException e
     */
    static void updateInventory () throws SQLException {
        System.out.println( "Here is all of the inventory in the system" );
        inventoryController.printInventoryList();
        System.out.println( "Which inventory would you like to edit" );
        final int inventoryID = scan.nextInt();

        final PreparedStatement preparedStatement = connection.prepareStatement( InventoryController.SELECT_ALL );
        final ResultSet set = preparedStatement.executeQuery();
        Inventory inventory = null;
        if ( set.next() ) {
            if ( set.getInt( "warehouseID" ) != 0 ) {
                inventory = new WarehouseInventory( inventoryID, set.getInt( "amount" ),
                        set.getInt( "productID" ), set.getTimestamp( "expirationDate" ),
                        set.getTimestamp( "manufacturingDate" ), set.getInt( "warehouseID" ) );
            }
            else if ( set.getInt( "storeID" ) != 0 ) {
                inventory = new StoreInventory( inventoryID, set.getInt( "amount" ),
                        set.getInt( "productID" ), set.getTimestamp( "expirationDate" ),
                        set.getTimestamp( "manufacturingDate" ), set.getInt( "storeID" ) );
            }

            System.out.println( "Enter the amount of inventory for the product" );
            final int amount = scan.nextInt();
            scan.nextLine();
            assert inventory != null;
            inventory.setAmount( amount );

            System.out.println( "Here is a list of all products in the system:" );
            productController.printProductList();

            System.out.println( "Enter the productID of the product this inventory is for" );
            final int productID = scan.nextInt();
            scan.nextLine();
            inventory.setProductID( productID );

            System.out.println( "Enter the inventory's expiration date" );
            System.out.print( "Year: " );
            int year = scan.nextInt();
            scan.nextLine();
            System.out.print( "Month: " );
            int month = scan.nextInt();
            scan.nextLine();
            System.out.print( "Day: " );
            int day = scan.nextInt();
            scan.nextLine();
            final Timestamp expirationDate = Utility.getTimestampObject( year, month - 1, day );
            inventory.setExpirationDate( expirationDate );
            System.out.println( "Enter the inventory's manufacturing date" );
            System.out.print( "Year: " );
            year = scan.nextInt();
            scan.nextLine();
            System.out.print( "Month: " );
            month = scan.nextInt();
            scan.nextLine();
            System.out.print( "Day: " );
            day = scan.nextInt();
            scan.nextLine();
            final Timestamp manufacturingDate = Utility.getTimestampObject( year, month - 1, day );
            inventory.setManufacturingDate( manufacturingDate );

            inventoryController.updateInventoryInformation( inventory );

        }
    }

    /**
     * Add a transfer
     * @throws SQLException e
     */
    static void addTransfer () throws SQLException {

        System.out.println("This is the current state of the warehouse inventory");
        inventoryController.printWarehouseInventoryList();


        System.out.println("Here are all of the warehouse operators");
        staffController.printWarehouseOperatorList();

        System.out.println("Enter the Warehouse Operator's ID");
        int staffID = scan.nextInt();
        scan.nextLine();

        System.out.println("Here are all of the products");
        productController.printProductList();

        System.out.println("Enter the transferred product's ID");
        int productID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the amount to be transferred");
        int quantity = scan.nextInt();
        scan.nextLine();

        System.out.println("Here are all of the warehouses");
        warehouseController.printWarehouseList();

        System.out.println("Enter the ID of the origin warehouse");
        int originWarehouseID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the ID of the destination warehouse");
        int destinationWarehouseID = scan.nextInt();
        scan.nextLine();

        Transfers transfer = new Transfers(-1, staffID, productID, quantity, originWarehouseID, destinationWarehouseID);
        inventoryController.transferProduct(transfer);
    }

    /**
     * Add a shipment
     * @throws SQLException e
     */
    static void addShipment () throws SQLException {

        System.out.println("Here are all of the warehouse operators");
        staffController.printWarehouseOperatorList();

        System.out.println("Enter the Warehouse Operator's ID");
        int staffID = scan.nextInt();
        scan.nextLine();


        System.out.println("Enter the Warehouse Operator's ID");
        System.out.println("New (n)");
        System.out.println("Return (r)");
        String typeString = scan.nextLine();
        Type type;
        if (typeString.charAt(0) == 'n') {
            type = Type.NEW_SHIPMENT;
        } else {
            type = Type.RETURN;
        }

        System.out.println("Here are all of the warehouses in the system");
        warehouseController.printWarehouseList();

        System.out.println("Enter the Warehouse's ID");
        int warehouseID = scan.nextInt();
        scan.nextLine();

        System.out.println("Here are all of the stores in the system");
        storeController.printStoreList();

        System.out.println("Enter the Store's ID");
        int storeID = scan.nextInt();
        scan.nextLine();

        System.out.println("Here are all of the products in the system");
        productController.printProductList();

        System.out.println("Enter IDs of the products in the shipment");
        System.out.println("Enter -1 to signal end of products input");

        // Hashmap containing the productID and quantity of each of the items in the shipment
        HashMap<Integer, Integer> products = new HashMap<>();

        int productID = 0;
        while ((productID = scan.nextInt()) != -1) {
            scan.nextLine();
            System.out.println("Enter the quantity of the product");
            int quantity = scan.nextInt();
            scan.nextLine();
            // Adds the product and its amount into the hashmap
            products.put(productID, quantity);
            System.out.println("Enter the productID");
        }

        Shipment shipment = new Shipment(-1, staffID, type, warehouseID, storeID, products);

        shipmentController.enterShipmentInformation(shipment);
    }


    /**
     * Closes a connection with MariaDB
     * @param connection connection to SQL database
     */
    static void close ( final Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            }
            catch ( final Throwable whatever ) {
            }
        }
    }

}
