<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Team 4 Database Management System Project 2</title>
<?php

define ('HOSTNAME', 'localhost'); //defining the hostname for the DB, since we're running this on vulcan,  'localhost' works.
define ('USERNAME', 'mbynum');//my username
define ('PASSWORD', 'password');//my password :)
define ('DATABASE_NAME', 'csci4850_team4'); //set DB name

$db = mysql_connect(HOSTNAME, USERNAME, PASSWORD) or die ('Unable to connect to MySQL.'); //use php function to connect or die with an error.

mysql_select_db(DATABASE_NAME); //php function to select our DB.

function print_query ($sql, $from)
{
	$result = mysql_query($sql);
	$ctr = 0;
	if (!$result) 
	{
		echo "DB Error, could not list tables\n";
		echo 'MySQL Error: ' . mysql_error();
		exit;
	}
	
	$row = mysql_fetch_row($result);
	$max = count($row);
	?><table cellpadding=5px>
    <?
	/*$sql2 = "SELECT COLUMN_NAME FROM information_schema.`COLUMNS` C WHERE table_name=$from;";
	$colresult = mysql_query($sql2);
	while ( $colrow = mysql_fetch_row($colresult) )
	{
		echo"<th>{$colrow[0]}</th>";
	}*/
	do 
	{
		if ( $ctr++ % 2 == 0 )
			echo "<tr class=\"evenTr\">";
		else
			echo "<tr>";
		for($i = 0; $i < $max; $i++)
		{
			echo "<td>{$row[$i]}</td>";
		}
		echo "</tr>";
	}
	while ($row = mysql_fetch_row($result));
	?></table><?
}
?>
</head>

<body>
<?php
/*Declaring some variables here*/
$dbname = "csci4850_team4";
$sql = "SHOW TABLES FROM $dbname";
$result = mysql_query($sql); //this line actually runs the query defined in $sql
$ctr = 0;

if (!$result) //if ( there was an error with result )
{
    echo "DB Error, could not list tables\n";
    echo 'MySQL Error: ' . mysql_error();
    exit;
}
echo "<table><tr><th>Tables</th><th>Columns</th></tr>";
while ($row = mysql_fetch_row($result)) //basically, foreach row in the result from the query, print that result to the page.
{
	if ( $ctr++ % 2 == 0 )
		echo "<tr class=\"evenTr\">";
	else
		echo "<tr>";
	echo "<td>{$row[0]}</td>";
	$sql = "SELECT COLUMN_NAME FROM information_schema.`COLUMNS` C WHERE table_name='{$row[0]}';";
	$colresult = mysql_query($sql);
	echo "<td>";
	while ( $colrow = mysql_fetch_row($colresult) )
	{
		echo"{$colrow[0]}&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	echo "</td>";
	echo "</tr>"; //{$row[0]} is just how you refer to a column, similar to the way you reference items in an array.
}
echo "</table>";
?>



<h3>Our Group Queries</h3>
<p>Show the max salary from each department and which department it is from (grouped by department).</p>
<?php $query1="SELECT `department`.`dept_name` , MAX( `doctor`.`salary` ) AS 'Max Salary' FROM `department` NATURAL JOIN `doctor` GROUP BY `department`.`dept_name` ;"; 
$from = "`department` NATURAL JOIN `doctor`";?>
<span class="query"><?php echo $query1; ?></span><br />
<?php print_query($query1, $from); ?>


<?php //paragraph text explaining the query?>
<p>Show the patient's first and last names sorted alphabetically by last name, who have been admitted to the same hospital that doctor Boyd Harrison works at.  </p>
<?php 
//variable to hold the query.. has to be all on one line.
$query2="SELECT  `patient`.`fname` ,  `patient`.`lname` FROM  `patient` NATURAL JOIN  `admitted` NATURAL JOIN  `hospital` WHERE ( `admitted`.`hospital_id`) IN ( SELECT  `hospital_id` FROM  `works` NATURAL JOIN  `doctor` WHERE  `doctor`.`lname` =  'Harrison' AND `doctor`.`fname` =  'Boyd') ORDER BY  `patient`.`lname` ASC"; 
$from = "`patient` NATURAL JOIN  `admitted` NATURAL JOIN  `hospital`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query2; ?></span><br />
<?php 
//prints the query results
print_query($query2, $from); ?>



<?php //paragraph text explaining the query?>
<p>Show everyone that has a family name of Haute.  </p>
<?php 
//variable to hold the query.. has to be all on one line.
$query3="SELECT `patient`.`fname`, `patient`.`lname` FROM `patient` NATURAL JOIN `family` WHERE `family_name`='Haute'"; 
$from = "`patient` NATURAL JOIN `family`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query3; ?></span><br />
<?php 
//prints the query results
print_query($query3, $from); ?>

<?php //paragraph text explaining the query?>
<p>Show how many patients (grouped by gender) have no medications, diseases, and allergies</p>
<?php 
//variable to hold the query.. has to be all on one line.
$query4="SELECT `gender`, COUNT(`gender`) FROM `patient` WHERE (`allergies`='none' AND `diseases`='none' AND `medications`='none') GROUP BY `gender`"; 
$from = "`patient`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query4; ?></span><br />
<?php 
//prints the query results
print_query($query4, $from); ?>

<?php //paragraph text explaining the query?>
<p>Show patients and their age</p>
<?php 
//variable to hold the query.. has to be all on one line.
$query5="SELECT `fname`, `lname`, `DOB`,  year(CURRENT_TIMESTAMP) - year(`DOB`) AS age FROM `patient` ORDER BY `lname`"; 
$from = "`patient`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query5; ?></span><br />
<?php 
//prints the query results
print_query($query5, $from); ?>


<br />
<br />
<h3>Write your own query!</h3>
<form name="customquery" method="get" action="team4.php">
	<table>
		<tr class="noColor"><td>SELECT:</td>
		<td><input type="text" name="select" /><span class="exampleText">Example: *</span></td></tr>
		<tr class="noColor"><td>FROM:</td><td><input type="text" name="from" /><span class="exampleText">Example: admitted</span></td></tr>
		<tr class="noColor"><td>WHERE:</td><td><input type="text" name="where" /><span class="exampleText">Example: 1</span></td></tr>
		<tr class="noColor"><td>&nbsp;</td><td><input type="submit" name="Submit" /></td></tr>
	</table>
</form>
<?php 
if(isset($_GET['select'])) //$_GET['select'] will be set only if they're coming from pressing the submit button on the form above.
{
	$ctr = 0;
	?>
    <h2>Query Results</h2>
    <h5><?php $sql = "SELECT ".$_GET['select']." FROM ".$_GET['from']." WHERE ".$_GET['where'];
	echo $sql;?></h5>
    <?
	
	$result = mysql_query(mysql_real_escape_string($sql));
	
	if (!$result) 
	{
		echo "DB Error, could not list tables\n";
		echo 'MySQL Error: ' . mysql_error();
		exit;
	}
	
	$row = mysql_fetch_row($result);
	$max = count($row);
	$FROM = $_GET['from'];
	?><table cellpadding=5px>
    <?
	$sql = "SELECT COLUMN_NAME FROM information_schema.`COLUMNS` C WHERE table_name='$FROM';";
	$colresult = mysql_query($sql);
	while ( $colrow = mysql_fetch_row($colresult) )
	{
		echo"<th>{$colrow[0]}</th>";
	}
	while ($row = mysql_fetch_row($result)) 
	{
		if ( $ctr++ % 2 == 0 )
			echo "<tr class=\"evenTr\">";
		else
			echo "<tr>";
		for($i = 0; $i < $max; $i++)
		{
			echo "<td>{$row[$i]}</td>";
		}
		echo "</tr>";
	}
	?></table><?
}
?>
<br />

</body>
</html>