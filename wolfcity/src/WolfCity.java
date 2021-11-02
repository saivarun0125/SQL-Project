import java.sql.*;
import java.util.Scanner;

public class WolfCity {


    // Update your user info alone here
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/jjboike"; // Using SERVICE_NAME

// Update your user and password info here!

    private static final String user = "jjboike";
    private static final String password = "wolfpackjack";

    public static void main(String[] args) {
        try {
// Loading the driver. This creates an instance of the driver
// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.


            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            ResultSet result = null;

            try {
                // Get a connection instance from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL
                connection = DriverManager.getConnection(jdbcURL, user, password);
                MemberController memberController = new MemberController(connection);

                while (true) {
                    System.out.println("What operation would you like to perform?");
                    System.out.println("1. Add a member to the system");
                    System.out.println("2. Edit a member");
                    Scanner scan = new Scanner(System.in);
                    int num = scan.nextInt();
                    if (num == 1) {
                        System.out.println("Enter staff ID");
                        int staffID = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter Member's first name");
                        String firstName = scan.nextLine();
                        System.out.println("Enter Member's last name");
                        String lastName = scan.nextLine();
                        System.out.println("Enter Member's email");
                        String email = scan.nextLine();
                        System.out.println("Enter Member's address");
                        String address = scan.nextLine();
                        System.out.println("Enter Member's phone number");
                        String phone = scan.nextLine();
                        Member member = new Member(-1, staffID, firstName,
                                lastName, "Active", email, address, phone);
                        memberController.enterMemberInformation(member);
                    } else if (num == 2) {
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
                        System.out.println("Enter Member's first name");
                        String firstName = scan.nextLine();
                        member.setFirstName(firstName);
                        System.out.println("Enter Member's last name");
                        String lastName = scan.nextLine();
                        member.setLastName(lastName);
                        System.out.println("Enter Member's email");
                        String email = scan.nextLine();
                        member.setEmail(email);
                        System.out.println("Enter Member's address");
                        String address = scan.nextLine();
                        member.setAddress(address);
                        System.out.println("Enter Member's phone number");
                        String phone = scan.nextLine();
                        member.setPhoneNumber(phone);
                        System.out.println("Enter Member's active status (y/n)");
                        String activeStatus = scan.nextLine();
                        char ch = activeStatus.charAt(0);
                        if (ch == 'y') {
                            member.setActiveStatus("Active");
                        } else {
                            member.setActiveStatus("Inactive");
                        }
                        memberController.updateMemberInformation(member);
                    }
                }
            } finally {
                close(connection);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void addMember(MemberController memberController, Scanner scan) {

    }

    static void editMember(MemberController memberController, Scanner scan) {

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