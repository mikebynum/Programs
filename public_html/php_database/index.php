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

// Create an array to hold our results
$arr = array();
//Execute the query
$result = mysql_query("SELECT * FROM business");

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Business Listing</title>
	<style type="text/css">
		table.db-table 		{ border-right:1px solid #ccc; border-bottom:1px solid #ccc; }
		table.db-table th	{ background:#eee; padding:5px; border-left:1px solid #ccc; border-top:1px solid #ccc; }
		table.db-table td	{ padding:5px; border-left:1px solid #ccc; border-top:1px solid #ccc; }
		
	</style>
</head>
<body>

	<h1>Business Listings</h1>
	<table cellpadding="0" cellspacing="0" class="db-table">
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Address2</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Phone</th>
			<th>Latitude</th>
			<th>Longitude</th>
			<th>Photo</th>
			<th>Description</th>
		</tr>
		<?php 
		//Print out the data from the results as new TR rows with TDs for each column
		while ($row = mysql_fetch_assoc($result))
		{
			print"<tr>";
			print"<td>" . $row['name'] . "</td>";
			print"<td>" . $row['address'] . "</td>";
			print"<td>" . $row['address2'] . "</td>";
			print"<td>" . $row['city'] . "</td>";
			print"<td>" . $row['state'] . "</td>";
			print"<td>" . $row['zip'] . "</td>";
			print"<td>" . $row['phone'] . "</td>";
			print"<td>" . $row['lat'] . "</td>";
			print"<td>" . $row['long'] . "</td>";
			print"<td>" . $row['photo'] . "</td>";
			print"<td>" . $row['description'] . "</td>";
			print"</tr>";
		}
		?>
	</table>
</body>
</html>