import java.sql.*;

public class page_insert_artist {
    // The main program that inserts an artist
    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // For debugging purposes: Show the database before the insert
        StringBuilder builder = new StringBuilder();
        String query1 = "SELECT * from ARTISTS";
        builder.append("<br> Table ARTISTS before:" + myDB.query(query1) + "<br>");

        // Parse input string to get artist Name and genre
        String name;
        String genre;

        // Read command line arguments
        // args[0] is the first parameter
        name = args[0];
        genre = args[1];

        // Exit if inputs are null/empty
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 0 || args[i] == null) {
                System.out.println("Inputs can not be left empty or be null!");
                return;
            }
        }

        // Get the next id
        String q = "select IFNULL(max(ARTIST_ID), 0) as max_id from ARTISTS";
        ResultSet result = myDB.rawQuery(q);
        int next_id = 1;
        if (result.next()) // get first row of result set
            next_id += result.getInt("max_id");

        // Insert the new artist
        String input = next_id + ",'" + name + "','" + genre + "'";
        myDB.insert("ARTISTS", input); // insert new artist

        // For debugging purposes: Show the database after the insert
        builder.append("<br><br><br> Table ARTISTS after:" + myDB.query(query1));
        System.out.println(builder.toString());

        System.out.println("Inserted Successfully!");

        myDB.disConnect();
    }
}
