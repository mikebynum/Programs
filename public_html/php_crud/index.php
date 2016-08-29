<?php

//SET UP DATABASE CONNECTION AND VARIABLES
include 'inc/dbconnect.inc.php';

$sql = "SELECT * FROM business ORDER BY name";
//Execute the query
$result = mysql_query($sql);


include 'inc/top.inc.php';
?>


<body>

	<h1>Business Listings</h1>
	<p>
	<br />
	<?php 
	session_start();
	
// 	echo (isset($_SESSION['msg']) ? $var : '')
	
	echo (isset($_SESSION['msg']) ? $_SESSION['msg'] : '');

	?>
	<br />
	</p>
	<hr />
	<h5><a href="add.php"> Add A Business </a></h5>
	<table cellpadding="0" cellspacing="0" class="db-table">
		<tr>
			<th>Action</th>
			<th>ID</th>
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
				print"<td><a href=\"edit.php?id=" . $row['id'] . "\"> Edit </a><a href=\"delete.php?id=" . $row['id'] . "\"> Delete </a></td>";
				print"<td>" . $row['id'] . "</td>";
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


<?php include 'inc/bottom.inc.php'?>