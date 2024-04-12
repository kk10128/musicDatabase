import java.sql.*;

public class page_view_songs {
    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Initialize variables
        String show_input;
        String filter_input;

        StringBuilder builder = new StringBuilder();
        Statement statement = jdbc_db.connection.createStatement();

        // Take in user input
        show_input = args[0];
        filter_input = args[1];

        // make sure show input is valid
        if (show_input.length() == 0 || show_input == null) {
            System.out.println("Show input can not be left empty or be null!");
            return;
        }

        // start to build final query
        String final_query = "SELECT a.NAME as ARTIST_NAME, s.NAME as SONG_NAME, s.YEAR as SONG_YEAR, s.GENRE as SONG_GENRE, r.LABEL_NAME as RECORD_LABEL_NAME, r.LABEL_CITY FROM ARTISTS a, RECORD_LABELS r, SONGS s WHERE s.LABEL_ID = r.LABEL_ID AND s.ARTIST_ID = a.ARTIST_ID";

        // run if user wants to view all and quit
        if (show_input.equals("ALL")) {
            builder.append("<br><br> DESIRED SONGS:" + myDB.query(final_query));
            System.out.println(builder.toString());
            return;
        }

        // if filter input is empty, after user doesn't want to just view ALL
        if (filter_input.length() == 0 || filter_input == null) {
            System.out.println("Filter input can not be left empty or be null!");
            return;
        }

        switch (show_input) {
            case "GENRE":
                final_query += " AND s.GENRE = '" + filter_input + "'";
                break;

            case "ARTIST":
                final_query += " AND a.ARTIST_ID = '" + filter_input + "'";
                break;

            case "RECORD_LABEL":
                final_query += " AND r.LABEL_ID = '" + filter_input + "'";
                break;

            default:
                System.out.println(" not a valid filter!");
                return;
        }

        // DEBUGGING
        // System.out.println("// final query TEST = " + final_query);

        // Validate user input
        ResultSet resultSet = statement.executeQuery(final_query);
        int result1 = 0;
        if (resultSet.next()) {
            result1 = 1;
        }

        // If invalid inform user then quit
        if (result1 == 0) {
            System.out.println("<br> No Song with the required input exists!");
            return;
        }

        builder.append("<br><br> DESIRED SONGS:" + myDB.query(final_query));
        System.out.println(builder.toString());

    }

}
