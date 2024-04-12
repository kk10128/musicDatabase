<html>
<body>

<h3>View songs</h3>
<h4>Scroll Down if needed</h4>
<form action="page_view_songs.php" method="post">
    Show <input type="text" name="show"> Enter 'ALL' to show all songs, or enter one of the following: 'GENRE','ARTIST','RECORD_LABEL'<br>
    Filter by: <input type="text" name="filter"> Enter the value you want to filter by according to your input above. Enter ID for desired ARTIST or RECORD_LABEL (Skip if 'ALL')<br>

    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php

$command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar function_print_song_info';
system($command);    

if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $show = escapeshellarg($_POST[show]);
    $filter = escapeshellarg($_POST[filter]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar page_view_songs ' . $show . ' ' . $filter . ' ';

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    //echo "<p>command: $command <p>"; 
    // run page_insert_artist.exe
    system($escaped_command);           
}
?>