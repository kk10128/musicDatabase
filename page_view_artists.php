<html>
<body>
<h3>Enter a genre to view Artists with that genre:</h3>

<form action="page_view_artists.php" method="post">
    Genre: <input type="text" name="Genre"><br>

    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php

$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar function_print_genres';
system($command);    

if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $Genre = escapeshellarg($_POST[Genre]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar page_view_artists ' . $Genre . ' ';

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    //echo "<p>command: $command <p>"; 
    // run page_insert_artist.exe
    system($escaped_command);           
}
?>