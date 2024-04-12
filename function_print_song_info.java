import java.sql.*;

public class function_print_song_info {

    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Print out distinct genres from ARTISTS table
        StringBuilder builder = new StringBuilder();
        String query1 = "select distinct(GENRE) from SONGS";
        builder.append("<br> List of SONG GENRES" + myDB.query(query1) + "<br>");

        query1 = "select ARTIST_ID, NAME FROM ARTISTS";
        builder.append("<br> List of ARTISTS" + myDB.query(query1) + "<br>");

        query1 = "select LABEL_ID, LABEL_NAME FROM RECORD_LABELS";
        builder.append("<br> List of Labels" + myDB.query(query1) + "<br>");

        System.out.println(builder.toString());
    }
}
