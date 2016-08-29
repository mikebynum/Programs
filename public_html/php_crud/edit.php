<?php

//set up database connection
require_once 'inc/dbconnect.inc.php';

//start session so we can use $_SESSION
session_start();


//Get query string variable
$id = $_GET['id'];

if(!is_numeric($id) || $id < 1)
{
	//if there is a problem, get a message and redirect
	$msg = "Invalid ID given";
	$_SESSION['msg'] = $msg;
	header("Location: index.php");
	exit();
}

//prep variable for SQL
$idSql = mysql_escape_string($id);

//SQL to get record
$sql = "SELECT * FROM business WHERE id='$idSql' ";

$result = mysql_query($sql);

//Test to see if record is OK
if(!is_resource($result))
{
	//if there is a problem, get a message and redirect
	$msg = "Error retrieving record. MYSQL Error: ".mysql_error();
	$_SESSION['msg'] = $msg;
	header("Location: index.php");
	exit();
}

if(mysql_num_rows($result) != 1)
{
	$msg = "Could not find record $id";
	$_SESSION['msg'] = $msg;
	header("Location: index.php");
	exit();
}

//Grab the only row
$business_info = mysql_fetch_assoc($result);

//Start the HTML
include 'inc/top.inc.php';
?>
<body>
<h1>Edit Business Information</h1>

<form action="editDo.php" method="post">
<p>

<label for="name">Business Name: </label>
<input type="text" id="name" name="name" value="<?php echo htmlspecialchars($business_info['name'])?>" />
<input type="hidden" id="id" name="id" value="<?php echo htmlspecialchars($business_info['id'])?>"/>

</p>
<p>
<label for="address">Business Address: </label>
<input type="text" id="address" name="address" value="<?php echo htmlspecialchars($business_info['address'])?>" />
</p>
<p>
<label for="address2">Business Address 2: </label>
<input type="text" id="address2" name="address2" value="<?php echo htmlspecialchars($business_info['address2'])?>" />
</p>
<p>
<label for="city">Business City: </label>
<input type="text" id="city" name="city" value="<?php echo htmlspecialchars($business_info['city'])?>" />
</p>
<p>
<label for="state">Business State: </label>
<input type="text" id="state" name="state" value="<?php echo htmlspecialchars($business_info['state'])?>" />
</p>
<p>
<label for="zip">Business Zip Code: </label>
<input type="text" id="zip" name="zip" value="<?php echo htmlspecialchars($business_info['zip'])?>" />
</p>
<p>
<label for="phone">Business Phone #: </label>
<input type="text" id="phone" name="phone" value="<?php echo htmlspecialchars($business_info['phone'])?>" />
</p>
<p>
<label for="latitude">Business Latitude: </label>
<input type="text" id="latitude" name="latitude" value="<?php echo htmlspecialchars($business_info['lat'])?>" />
</p>
<p>
<label for="longitude">Business Longitude: </label>
<input type="text" id="longitude" name="longitude" value="<?php echo htmlspecialchars($business_info['long'])?>" />
</p>
<p>
<label for="photo">Business Photo: </label>
<input type="text" id="photo" name="photo" value="<?php echo htmlspecialchars($business_info['photo'])?>" />
</p>
<p>
<label for="description">Business Description: </label>
<input type="text" id="description" name="description" value="<?php echo htmlspecialchars($business_info['description'])?>" />
</p>
<div style="margin-top:10px;">
<input type="submit" value="  Save  " class="" style="margin:4px;"/>
<input type="button" name="cancel" value="Cancel" class=""  onclick="window.location = 'index.php'" style="margin:4px;"/>
</div>
</form>
<?php include 'inc/bottom.inc.php';?>
