package utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Utility {


    /**
     * Prints out the result of a select query
     * Use this to print out list of objects to be deleted or edited when user makes selection
     * @param rs results of query
     * @throws SQLException e
     */
    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print( rsmd.getColumnName(i) + ": " + columnValue);
            }
            System.out.println("");
        }
    }

    /**
     * Gets a timestamp object by passing in a year, month and day
     * 0 <= month <= 11
     * 1 <= day <= 31
     * @param year year
     * @param month month
     * @param day day
     * @return Timestamp object
     */
    public static Timestamp getTimestampObject(int year, int month, int day) {
        GregorianCalendar date = new GregorianCalendar(year, month, day);
        long ms = date.getTimeInMillis();
        return new Timestamp(ms);
    }
}
