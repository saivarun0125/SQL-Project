package utlities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Utility {


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

    public static Timestamp getTimestampObject(int year, int month, int day) {
        GregorianCalendar date = new GregorianCalendar(year, month, day);
        long ms = date.getTimeInMillis();
        return new Timestamp(ms);
    }
}
