import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import controllers.DiscountController;
import controllers.InventoryController;
import controllers.MemberController;
import controllers.MembershipController;
import controllers.ProductController;
import controllers.StaffController;
import controllers.StoreController;
import controllers.SupplierController;
import controllers.TransactionController;
import controllers.WarehouseController;
import models.*;
import utlities.Utility;

public class WolfCity {

    // Update your user info alone here
    private static final String         jdbcURL  = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/jjboike"; // Using
                                                                                                        // SERVICE_NAME

    // Update your user and password info here!

    private static final String         user     = "jjboike";
    private static final String         password = "wolfpackjack";
    private static Scanner              scan;
    private static Connection           connection;
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
                    System.out.println( "24. Add a transaction to the system" );
                    System.out.println( "25. Edit a transaction" );
                    System.out.println( "26. Delete a transaction" );
                    System.out.println( "30. Add a bill to the system" );
                    System.out.println( "31. Edit a bill" );
                    System.out.println( "32. Delete a bill" );
                    System.out.println( "33. Generate RewardsCheck" );

                    scan = new Scanner(System.in);
                    int num = scan.nextInt();
                    scan.nextLine();
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
                    else if (num == 34) {
                        addTransfer();
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
    
    static void deleteTransaction() throws SQLException {
    	System.out.println("Here are all of the Transactions in the system");
        transactionController.printTransactionList();
        System.out.println("Which transaction would you like to delete?");
        int transactionID = scan.nextInt();
        scan.nextLine();
        transactionController.deleteTransactionInformation(transactionID);
	}

	static void editTransaction() throws SQLException {
		System.out.println("Here are a list of all of the transactions in the system");
		transactionController.printTransactionList();
		
		int transactionID = scan.nextInt();
        String query = "SELECT * FROM Transaction where transactionID = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, transactionID);
        ResultSet set = st.executeQuery();
        set.next();
        System.out.println(set);
        Transaction t = new Transaction(set.getInt("transactionID"), set.getInt("totalPrice"),
        		set.getInt("storeID"), set.getInt("memberID"),
        		set.getInt("staffID"), set.getTimestamp("purchaseDate"));
        scan.nextLine();
        System.out.println("Edit this transaction's attributes");
        System.out.println("Leave attributes blank to not edit them");
        System.out.println();
        System.out.println("Enter models.Transaction's total price");
        int totalPrice = scan.nextInt();
        t.setTotalPrice(totalPrice);
        System.out.println("Enter models.Transaction's store ID");
        int storeID = scan.nextInt();
        t.setStoreID(storeID);
        System.out.println("Enter models.Transaction's member ID");
        int memberID = scan.nextInt();
        t.setMemberID(memberID);
        System.out.println("Enter models.Transaction's staff ID");
        int staffID = scan.nextInt();
        t.setStaffID(staffID);
        System.out.println("Enter models.Transaction's purchase date");
        System.out.print("Year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("Month: ");
        int month = scan.nextInt();
        scan.nextLine();
        System.out.print("Day: ");
        int day =scan.nextInt();
        scan.nextLine();
        Timestamp date = Utility.getTimestampObject(year, month - 1, day);
        System.out.print(date.toString());
        t.setPurchaseDate(date);
        transactionController.updateTransactionInformation(t);
		
	}

	static void addTransaction() throws SQLException {
		System.out.println("Enter total price");
		int totalPrice = scan.nextInt();
		System.out.println("Enter store ID");
		int storeID = scan.nextInt();
		System.out.println("Enter member ID");
		int memberID = scan.nextInt();
		System.out.println("Enter staff ID");
		int staffID = scan.nextInt();
		System.out.println("Enter the transaction date");
		System.out.println("Month: ");
		int month = scan.nextInt();
		System.out.println("Day: ");
		int day = scan.nextInt();
		System.out.println("Year: ");
		int year = scan.nextInt();
		Timestamp date = Utility.getTimestampObject(year, month - 1, day);
		Transaction t = new Transaction(-1, totalPrice, storeID, memberID, staffID, date);
		transactionController.enterTransactionInformation(t);
	}

    static void addMember () throws SQLException {
        System.out.println( "Enter staff ID" );
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
        System.out.println( "Enter models.Member's first name" );
        final String firstName = scan.nextLine();
        member.setFirstName( firstName );
        System.out.println( "Enter models.Member's last name" );
        final String lastName = scan.nextLine();
        member.setLastName( lastName );
        System.out.println( "Enter models.Member's email" );
        final String email = scan.nextLine();
        member.setEmail( email );
        System.out.println( "Enter models.Member's address" );
        final String address = scan.nextLine();
        member.setAddress( address );
        System.out.println( "Enter models.Member's phone number" );
        final String phone = scan.nextLine();
        member.setPhoneNumber( phone );
        System.out.println( "Enter models.Member's active status (y/n)" );
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

    static void deleteMember () throws SQLException {
        System.out.println( "Here are all of the Members in the system" );
        memberController.printMemberList();
        System.out.println( "Which member would you like to delete?" );
        final int memberID = scan.nextInt();
        scan.nextLine();
        memberController.deleteMemberInformation( memberID );
    }

    static void deleteStaff () throws SQLException {
        System.out.println( "Here are all of the models.Staff members in the system" );
        staffController.printStaffList();
        System.out.println( "Which member would you like to delete?" );
        final int staffID = scan.nextInt();
        scan.nextLine();
        staffController.deleteStaffInformation( staffID );
    }

    static void editStaff () throws SQLException {
        System.out.println( "Here are all of the models.Staff members in the system" );

        staffController.printStaffList();
        System.out.println( "Which staff member would you like to edit" );

        final int staffID = scan.nextInt();
        final String query = "SELECT * FROM Staff where staffID = ?";
        final PreparedStatement st = connection.prepareStatement( query );
        st.setInt( 1, staffID );
        final ResultSet set = st.executeQuery();
        set.next();
        System.out.println( set );
        StaffType type = null;
        switch ( set.getString( "jobTitle" ) ) {
            case "Admin":
                type = StaffType.ADMIN;
            case "RegistrationOperator":
                type = StaffType.REGISTRATION_OPERATOR;
            case "WarehouseOperator":
                type = StaffType.WAREHOUSE_OPERATOR;
            case "Cashier":
                type = StaffType.CASHIER;
            case "BillingStaff":
                type = StaffType.BILLING_STAFF;
            default:
                type = StaffType.CASHIER;
        }
        ;
        final Staff staff = new Staff( set.getInt( "staffID" ), set.getString( "name" ), set.getInt( "age" ),
                set.getInt( "storeID" ), set.getString( "homeAddress" ), set.getString( "jobTitle" ),
                set.getString( "phoneNumber" ), set.getString( "email" ), set.getTimestamp( "employmentDate" ), type );
        scan.nextLine();
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

    private static void addStaff () throws SQLException {
        System.out.println( "Enter the staff member's name" );
        final String name = scan.nextLine();
        System.out.println( "Enter the staff member's age" );
        final int age = scan.nextInt();
        scan.nextLine();
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
        switch ( jobTitle ) {
            case "Admin":
                type = StaffType.ADMIN;
            case "RegistrationOperator":
                type = StaffType.REGISTRATION_OPERATOR;
            case "WarehouseOperator":
                type = StaffType.WAREHOUSE_OPERATOR;
            case "Cashier":
                type = StaffType.CASHIER;
            case "BillingStaff":
                type = StaffType.BILLING_STAFF;
            default:
                type = StaffType.CASHIER;
        }
        ;

        final Staff staff = new Staff( -1, name, age, storeID, address, jobTitle, phone, email,
                java.sql.Timestamp.valueOf( LocalDateTime.now() ), type );
        staffController.enterStaffInformation( staff );
    }

    private static void addStore () throws SQLException {
        System.out.println( "Enter the store's address" );
        final String address = scan.nextLine();
        System.out.println( "Enter the store's phone number" );
        final String phone = scan.nextLine();
        final Store store = new Store( -1, phone, address );
        storeController.enterStoreInformation( store );
    }

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
                set.getString( "address" ) );
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
        storeController.updateStoreInformation( store );
    }

    static void deleteStore () throws SQLException {
        System.out.println( "Here are all of the stores in the system" );
        storeController.printStoreList();
        System.out.println( "Which store would you like to delete?" );
        final int storeID = scan.nextInt();
        scan.nextLine();
        storeController.deleteStoreInformation( storeID );
    }

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

    static void deleteDiscount () throws SQLException {
        System.out.println( "Here are all of the discounts in the system" );
        discountController.printDiscountList();
        System.out.println( "Which discount would you like to delete?" );
        final int discountID = scan.nextInt();
        scan.nextLine();
        discountController.deleteDiscountInformation( discountID );
    }

    private static void addProduct () throws SQLException {
        System.out.println( "Enter the product's name" );
        final String productName = scan.nextLine();
        System.out.println( "Enter the supplier's id:" );
        final int supplierID = scan.nextInt();
        scan.nextLine();
        final Product product = new Product( -1, productName, supplierID );
        productController.enterProductInformation( product );
    }

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
                set.getInt( "supplierID" ) );
        scan.nextLine();
        System.out.println( "Edit this store's attributes" );
        System.out.println( "Leave attributes blank to not edit them" );
        System.out.println();
        System.out.println( "Enter the product's name" );
        final String productName = scan.nextLine();
        product.setProductName( productName );
        System.out.println( "Enter the supplier's id:" );
        final int supplierID = scan.nextInt();
        product.setSupplierID( supplierID );
        scan.nextLine();
        productController.updateProductInformation( product );
    }

    static void deleteProduct () throws SQLException {
        System.out.println( "Here are all of the products in the system" );
        productController.printProductList();
        System.out.println( "Which product would you like to delete?" );
        final int productID = scan.nextInt();
        scan.nextLine();
        productController.deleteProductInformation( productID );
    }

    private static void addWarehouse () throws SQLException {
        System.out.println( "Enter the warehouse's address" );
        final String address = scan.nextLine();
        final Warehouse warehouse = new Warehouse( -1, address );
        warehouseController.enterWarehouseInformation( warehouse );
    }

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

    static void deleteWarehouse () throws SQLException {
        System.out.println( "Here are all of the warehouses in the system" );
        warehouseController.printWarehouseList();
        System.out.println( "Which warehouse would you like to delete?" );
        final int warehouseID = scan.nextInt();
        scan.nextLine();
        warehouseController.deleteWarehouseInformation( warehouseID );
    }

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

    static void deleteSupplier () throws SQLException {
        System.out.println( "Here are all of the suppliers in the system" );
        supplierController.printSupplierList();
        System.out.println( "Which supplier would you like to delete?" );
        final int supplierID = scan.nextInt();
        scan.nextLine();
        supplierController.deleteSupplierInformation( supplierID );
    }

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

    static void deleteMembership () throws SQLException {
        System.out.println( "Here are all of the memberships in the system" );
        membershipController.printMembershipList();
        System.out.println( "Which membership would you like to delete?" );
        final int membershipID = scan.nextInt();
        scan.nextLine();
        membershipController.deleteMembershipInformation( membershipID );
    }

    private static void addInventory () throws SQLException {
        System.out.println( "Enter the amount of inventory for the product" );
        final int amount = scan.nextInt();
        scan.nextLine();
        System.out.println( "Enter the price of the product" );
        final float price = scan.nextFloat();
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
            inventory = new StoreInventory( -1, amount, price, productID, expirationDate, manufacturingDate, storeID );
        }
        else if ( inventoryType.charAt( 0 ) == 'w' ) {
            System.out.println( "Here are all of the warehouses in the system:" );
            warehouseController.printWarehouseList();
            System.out.println( "Enter the warehouseID for this inventory entry" );
            final int warehouseID = scan.nextInt();
            scan.nextLine();
            inventory = new WarehouseInventory( -1, amount, price, productID, expirationDate, manufacturingDate,
                    warehouseID );
        }
        assert inventory != null;
        inventoryController.enterInventoryInformation( inventory );
    }

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
                inventory = new WarehouseInventory( inventoryID, set.getInt( "amount" ), set.getFloat( "price" ),
                        set.getInt( "productID" ), set.getTimestamp( "expirationDate" ),
                        set.getTimestamp( "manufacturingDate" ), set.getInt( "warehouseID" ) );
            }
            else if ( set.getInt( "storeID" ) != 0 ) {
                inventory = new StoreInventory( inventoryID, set.getInt( "amount" ), set.getFloat( "price" ),
                        set.getInt( "productID" ), set.getTimestamp( "expirationDate" ),
                        set.getTimestamp( "manufacturingDate" ), set.getInt( "storeID" ) );
            }

            System.out.println( "Enter the amount of inventory for the product" );
            final int amount = scan.nextInt();
            scan.nextLine();
            assert inventory != null;
            inventory.setAmount( amount );
            System.out.println( "Enter the price of the product" );
            final float price = scan.nextFloat();
            scan.nextLine();
            inventory.setPrice( price );

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

    static void addTransfer() throws SQLException {

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

    // static void deleteInventory() throws SQLException {
    // System.out.println("Here are all of the inventory in the system");
    // inventoryController.printInventoryList();
    // System.out.println("Which inventory would you like to delete?");
    // int inventoryID = scan.nextInt();
    // scan.nextLine();
    // supplierController.deleteSupplierInformation(inventoryID);
    // }

    static void close ( final Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            }
            catch ( final Throwable whatever ) {
            }
        }
    }

    static void close ( final Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            }
            catch ( final Throwable whatever ) {
            }
        }
    }

    static void close ( final ResultSet result ) {
        if ( result != null ) {
            try {
                result.close();
            }
            catch ( final Throwable whatever ) {
            }
        }
    }
}
