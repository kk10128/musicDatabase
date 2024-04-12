import java.sql.*;

public class function_print_label_genres {
    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Print out distinct genres from ARTISTS table
        StringBuilder builder = new StringBuilder();
        String query1 = "select distinct DESIRED_GENRE from RECORD_LABELS";
        builder.append("<br> List of GENRES" + myDB.query(query1) + "<br>");
        builder.append("<br> Enter 'ALL' to view all Record Labels regardless of GENRE <br>");
        System.out.println(builder.toString());
    }
}
