import java.sql.*;

public class page_insert_song {

    public static void main(String[] args) throws SQLException {
        String Username = ""; // Change to your own username
        String mysqlPassword = ""; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        String ArtistID;
        String LabelID;
        String Name;
        String Genre;
        String Year;
        String Ranking;

        // Read command line arguments
        ArtistID = args[0];
        LabelID = args[1];
        Name = args[2];
        Genre = args[3];
        Year = args[4];
        Ranking = args[5];

        // Exit if inputs are null/empty
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 0 || args[i] == null) {
                System.out.println("Inputs can not be left empty or be null!");
                return;
            }
        }

        // Get the next id
        String q = "select IFNULL(max(SONG_ID), 0) as max_id from SONGS";
        ResultSet result = myDB.rawQuery(q);
        int next_id = 1;
        if (result.next()) // get first row of result set
            next_id += result.getInt("max_id");

        // VALIDATION
        Statement statement = jdbc_db.connection.createStatement();

        // Validate ArtistID
        String q1 = "SELECT EXISTS(SELECT * from ARTISTS WHERE ARTIST_ID='" + ArtistID + "') as RESULT";
        ResultSet resultSet = statement.executeQuery(q1);
        int result1 = 0;
        if (resultSet.next())
            result1 = resultSet.getInt("RESULT");

        if (result1 == 1) {
            // do nothing, code is valid
        } else {
            // does not exist
            System.out.println(ArtistID + " is not valid Artist ID!");
            return;
        }

        // Validate LabelID
        String q2 = "SELECT EXISTS(SELECT * from RECORD_LABELS WHERE LABEL_ID='" + LabelID + "') as RESULT";
        resultSet = statement.executeQuery(q2);
        int result2 = 0;
        if (resultSet.next())
            result2 = resultSet.getInt("RESULT");

        if (result2 == 1) {
            // do nothing, code is valid
        } else {
            // does not exist
            System.out.println(LabelID + " is not valid Label ID!");
            return;
        }

        // Insert the new song
        String input = next_id + "," + ArtistID + "," + LabelID + ",'" + Name + "','" + Genre + "'," + Year + ","
                + Ranking;
        myDB.insert("SONGS", input); // insert new

        System.out.println("Inserted Successfully!");

        myDB.disConnect();
    }

}
