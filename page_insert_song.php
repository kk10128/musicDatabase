<html>
<body>
<h3>Enter information about a song to add to the database:</h3>

<form action="page_insert_song.php" method="post">
    ArtistID: <input type="number" name="ArtistID"><br>
    LabelID: <input type="number" name="LabelID"><br>
    Name: <input type="text" name="Name"><br>
    Genre: <input type="text" name="Genre"><br>
    Year: <input type="number" name="Year"><br>
    Ranking: <input type="number" name="Ranking"><br>

    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php

$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar function_print_table';
system($command);    

if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $ArtistID = escapeshellarg($_POST[ArtistID]);
    $LabelID = escapeshellarg($_POST[LabelID]);
    $Name = escapeshellarg($_POST[Name]);
    $Genre = escapeshellarg($_POST[Genre]);
    $Year = escapeshellarg($_POST[Year]);
    $Ranking = escapeshellarg($_POST[Ranking]);


    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar page_insert_song ' . $ArtistID . ' ' . $LabelID . ' ' . $Name . ' ' . $Genre . ' '. $Year . ' '. $Ranking . ' ';

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run page_insert_artist.exe
    system($escaped_command);           
}
?>