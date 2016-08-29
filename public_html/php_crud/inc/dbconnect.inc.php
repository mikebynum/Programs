<?php

// Database credentials
$host = 'localhost';
$db = 'mbynum';
$uid = 'mbynum';
$pwd = 'mbynum';

// Connect to the database server
$link = mysql_connect($host, $uid, $pwd) or die("Could not connect");

//select the database
mysql_select_db($db) or die("Could not select database");


?>