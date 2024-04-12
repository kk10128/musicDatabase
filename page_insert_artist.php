<html>
<body>
<h3>Enter information about an Artist to add to the database:</h3>

<form action="page_insert_artist.php" method="post">
    Name: <input type="text" name="name"><br>
    Genre: <input type="text" name="genre"><br>
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $name = escapeshellarg($_POST[name]);
    $genre = escapeshellarg($_POST[genre]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar page_insert_artist ' . $name . ' ' . $genre;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    //echo "<p>command debug: $command <p>"; 
    echo "<p>escaped_command: $escaped_command <p>"; 
    // run page_insert_artist.exe
    system($escaped_command);           
}
?>


