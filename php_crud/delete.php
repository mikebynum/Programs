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
<h1>Do You Really Want To Delete This Business?</h1>

<form action="deleteDo.php" method="post">
<p>
<label>Business ID: </label>
<?php echo htmlspecialchars($business_info['id'])?>
</p>

<p>
<label>Business Name: </label>
<?php echo htmlspecialchars($business_info['name'])?>
<input type="hidden" id="id" name="id" value="<?php echo htmlspecialchars($business_info['id'])?>"/>
</p>
<p>
<label>Business Address: </label>
<?php echo htmlspecialchars($business_info['address'])?>"
</p>
<p>
<label>Business Address 2: </label>
<?php echo htmlspecialchars($business_info['address2'])?>
</p>
<p>
<label>Business City: </label>
<?php echo htmlspecialchars($business_info['city'])?>
</p>
<p>
<label>Business State: </label>
<?php echo htmlspecialchars($business_info['state'])?>
</p>
<p>
<label>Business Zip Code: </label>
<?php echo htmlspecialchars($business_info['zip'])?>
</p>
<p>
<label>Business Phone #: </label>
<?php echo htmlspecialchars($business_info['phone'])?>
</p>
<p>
<label>Business Latitude: </label>
<?php echo htmlspecialchars($business_info['lat'])?>
</p>
<p>
<label>Business Longitude: </label>
<?php echo htmlspecialchars($business_info['long'])?>
</p>
<p>
<label>Business Photo: </label>
<?php echo htmlspecialchars($business_info['photo'])?>
</p>
<p>
<label>Business Description: </label>
<?php echo htmlspecialchars($business_info['description'])?>
</p>
<div style="margin-top:10px;">
<input type="submit" value="  Yes, DELETE  " class="" style="margin:4px;"/>
<input type="button" name="cancel" value="Cancel" class=""  onclick="window.location = 'index.php'" style="margin:4px;"/>
</div>
</form>
<?php include 'inc/bottom.inc.php';?>
