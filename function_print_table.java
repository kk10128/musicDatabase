import java.sql.*;

public class function_print_table {
    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

   
        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

    


        // Print out ARTISTS table
        StringBuilder builder = new StringBuilder();
        String query1 = "SELECT * from ARTISTS";
        builder.append("<br> ARTISTS table" + myDB.query(query1) + "<br>");

        // Print out RECORD_LABELS table
        String query2 = "SELECT * from RECORD_LABELS";
        builder.append("<br> RECORD_LABELS table" + myDB.query(query2) + "<br>");
        System.out.println(builder.toString());

       


    }

}
