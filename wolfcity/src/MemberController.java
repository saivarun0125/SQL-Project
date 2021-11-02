import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberController {

    private static Connection connection;

    public MemberController(Connection connection) throws SQLException {
        MemberController.connection = connection;
    }

    public void enterMemberInformation(Member member) throws SQLException {
        String query = "INSERT INTO Member (staffID, firstName, lastName, activeStatus, email, address, phoneNumber) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, member.getStaffID());
        preparedStatement.setString(2, member.getFirstName());
        preparedStatement.setString(3, member.getLastName());
        preparedStatement.setBoolean(4, member.getActiveStatus());
        preparedStatement.setString(5, member.getEmail());
        preparedStatement.setString(6, member.getAddress());
        preparedStatement.setString(7, member.getPhone());
        preparedStatement.execute();
    }
}
