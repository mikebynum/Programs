<?php
//set up database connection
require_once 'inc/dbconnect.inc.php';

//start session so we can use $_SESSION
session_start();

//prepare variables - making sure to prevent SQL injection
$id = mysql_real_escape_string($_POST['id']);
$name = mysql_real_escape_string($_POST['name']);
$address = mysql_real_escape_string($_POST['address']);
$address2 = mysql_real_escape_string($_POST['address2']);
$city = mysql_real_escape_string($_POST['city']);
$state = mysql_real_escape_string($_POST['state']);
$zip = mysql_real_escape_string($_POST['zip']);
$phone = mysql_real_escape_string($_POST['phone']);
$latitude = mysql_real_escape_string($_POST['latitude']);
$longitude = mysql_real_escape_string($_POST['longitude']);
$photo = mysql_real_escape_string($_POST['photo']);
$description = mysql_real_escape_string($_POST['description']);

//prepare sql statement
$sql = "UPDATE business SET
		`name` = '$name',
		`address` = '$address',
		`address2` = '$address2',
		`city` = '$city',
		`state` = '$state',
		`zip` = '$zip',
		`phone` = '$phone',
		`lat` = '$latitude',
		`long` = '$longitude',
		`photo` = '$photo',
		`description` = '$description'
		
		WHERE
		
		`id` = '$id' ";
		
//Run the SQL INSERT and return true or false
$ok = mysql_query($sql);

if ($ok)
{	
	//prep the message
	$msg = "Record $name id $id updated!";
}
else
{
	$msg = "Error saving record.  MYSQL Error: ".mysql_error();
}

$_SESSION['msg'] = $msg;
header("Location: index.php");
exit();
?>

