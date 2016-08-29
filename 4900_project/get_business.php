<?php

	require_once 'JSON.php';
	
   // Database credentials
   $host = 'localhost'; 
   $db = 'mbynum'; 
   $uid = 'mbynum'; 
   $pwd = 'mbynum';
 
	// Connect to the database server   
    $link = mysql_connect($host, $uid, $pwd) or die("Could not connect");
   
   //select the json database
   mysql_select_db($db) or die("Could not select database");
   
   // Create an array to hold our results
   $arr = array();
   //Execute the query
   $rs = mysql_query("SELECT * FROM business");
   
   // Add the rows to the array 
   while($obj = mysql_fetch_object($rs)) {
   $arr[] = $obj;
   }
   
   $json = new Services_JSON();

   //return the json result. The string users is just a name for the container object. Can be set anything.
   echo '{"Businesses":'.$json->encode($arr).'}';
?>