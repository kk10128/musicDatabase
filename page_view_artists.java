import java.sql.*;

public class page_view_artists {

    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Initialize variables
        String Genre_Input;
        StringBuilder builder = new StringBuilder();
        Statement statement = jdbc_db.connection.createStatement();

        // Take in user input
        Genre_Input = args[0];

        // Exit if inputs are null/empty
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 0 || args[i] == null) {
                System.out.println("Inputs can not be left empty or be null!");
                return;
            }
        }

        // check if user inputted ALL, select * from ARTISTS then quit
        if (Genre_Input.equals("ALL")) {
            String q = "select NAME from ARTISTS";
            builder.append("<br><br> DESIRED ARTISTS:" + myDB.query(q));
            System.out.println(builder.toString());
            return;
        }

        // Validate user input
        String q1 = "SELECT EXISTS(SELECT * from ARTISTS WHERE GENRE='" + Genre_Input + "') as RESULT";
        ResultSet resultSet = statement.executeQuery(q1);
        int result1 = 0;
        if (resultSet.next())
            result1 = resultSet.getInt("RESULT");

        // If invalid inform user then quit
        if (result1 == 0) {
            System.out.println(Genre_Input + " is not valid Genre name, grab one from the above table!");
            return;
        }

        // If valid, select * from ARTISTS where GENRE = 'Genre_Input'
        String q = "select * from ARTISTS where GENRE = '" + Genre_Input + "'";
        builder.append("<br><br> DESIRED ARTISTS:" + myDB.query(q));
        System.out.println(builder.toString());
    }

}
