<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Team 4 Database Management System Project 2</title>
<?php

if(ini_get("magic_quotes_gpc") == "1")
{
   $_POST['query'] = stripslashes($_POST['query']);
}
$host="localhost";
$user="mbynum";
$password="password";
$database="csci4850_team4";

$cxn = mysqli_connect($host,$user,$password,
                        $database);
						
if(isset($_GET['lname']))
{
	$lastname = $_GET['lname'];
	$qry = "SELECT lname FROM patient WHERE 1;";
	$rslt = mysqli_query($cxn,$qry);
	$i = 0;
	while ($row = mysqli_fetch_row($rslt))
	{
		if(preg_match_all("/.*$lastname.*/i", $row[0], $matches))
		{
			foreach($matches as $val)
			{
				$resarray[$i++] = $val[0];
				//echo "Added ".$val[0]." to the array...<br />";
			}
		}
	}
	
}

function print_results($cxn,$array)
{
	$ctr =0 ;
	$test = "SELECT * FROM `patient`";
	$result = mysqli_query($cxn,$test) or die();
	echo "<h4>General Information</h4>";
	echo "<table cellpadding=\"4\">";
	$num = mysqli_num_fields($result);
	echo "<tr>";
	for($i = 0; $i < $num; $i++)
	{
		$field = mysqli_fetch_field($result);
		echo"<th>{$field->name}</th>";
	}
	echo "</tr>";
	foreach($array as $n)
	{
		$sql1 = "SELECT * FROM `patient` WHERE `patient`.`lname`='".$n."';";
		$result = mysqli_query($cxn,$sql1) or die();
		while ($row = mysqli_fetch_row($result)) 
		{
			$max = count($row);
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
	}
	echo "</table>";
	
	
	
	$ctr =0 ;
	$test = "SELECT DISTINCT `patient`.`fname` AS First, `patient`.`lname` AS Last, `admitted`.`date` AS Date, `hospital`.`name` AS Hospital, `admitted`.`symptoms` AS Symptoms, `admitted`.`treatment` AS Treatment FROM  `patient` NATURAL JOIN  `admitted` NATURAL JOIN  `hospital`";
	$result = mysqli_query($cxn,$test) or die();
	echo "<h4>Hospital Visits</h4>";
	echo "<table cellpadding=\"4\">";
	$num = mysqli_num_fields($result);
	echo "<tr>";
	for($i = 0; $i < $num; $i++)
	{
		$field = mysqli_fetch_field($result);
		echo"<th>{$field->name}</th>";
	}
	echo "</tr>";
	foreach($array as $n)
	{
		$sql1 = "SELECT DISTINCT `patient`.`fname` AS First, `patient`.`lname` AS Last, `admitted`.`date` AS Date, `hospital`.`name` AS Hospital, `admitted`.`symptoms` AS Symptoms, `admitted`.`treatment` AS Treatment FROM  `patient` NATURAL JOIN  `admitted` NATURAL JOIN  `hospital` WHERE `patient`.`lname`='".$n."';";
		$result = mysqli_query($cxn,$sql1) or die();
		while ($row = mysqli_fetch_row($result)) 
		{
			$max = count($row);
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
	}
	echo "</table>";
}

function print_query ($cxn,$sql)
{
	
	$result = mysqli_query($cxn,$sql);
	$ctr = 0;
	if (!$result) 
	{
		echo "DB Error, could not list tables\n";
		echo 'MySQL Error: ' . mysql_error();
		exit;
	}
	
	
	
	?><table cellpadding=5px>
    <?
	$num = mysqli_num_fields($result);
	echo "<tr>";
	for($i = 0; $i < $num; $i++)
	{
		$field = mysqli_fetch_field($result);
		echo"<th>{$field->name}</th>";
	}
	echo "</tr>";
	while ($row = mysqli_fetch_row($result)) 
	{
		$max = count($row);
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
</head>

<body>
<div align="center">
<div align="left" id="toc">
<h2>Table of Contents</h2>
<ul>
	<li><a href="#contents">Database Contents</a></li>
	<li><a href="#searchpatient">Search For Patients</a></li>
	<li><a href="#groupQuery1">Group Query 1</a></li>
	<li><a href="#groupQuery2">Group Query 2</a></li>
	<li><a href="#groupQuery3">Group Query 3</a></li>
	<li><a href="#groupQuery4">Group Query 4</a></li>
	<li><a href="#groupQuery5">Group Query 5</a></li>
	<li><a href="#customquery">Write your own query</a></li>
</ul>
</div>
<div id="contents">


<?php
/*Declaring some variables here*/
$dbname = "csci4850_team4";
$sql = "SHOW TABLES FROM $dbname";
$result = mysqli_query($cxn,$sql); //this line actually runs the query defined in $sql
$ctr = 0;

if (!$result) //if ( there was an error with result )
{
    echo "DB Error, could not list tables\n";
    echo 'MySQL Error: ' . mysql_error();
    exit;
}
echo "<table><tr><th>Tables</th><th>Columns</th></tr>";
while ($row = mysqli_fetch_row($result)) //basically, foreach row in the result from the query, print that result to the page.
{
	if ( $ctr++ % 2 == 0 )
		echo "<tr class=\"evenTr\">";
	else
		echo "<tr>";
	echo "<td>{$row[0]}</td>";
	$sql = "SELECT COLUMN_NAME FROM information_schema.`COLUMNS` C WHERE table_name='{$row[0]}';";
	$colresult = mysqli_query($cxn,$sql);
	echo "<td>";
	while ( $colrow = mysqli_fetch_row($colresult) )
	{
		echo"{$colrow[0]}&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	echo "</td>";
	echo "</tr>"; //{$row[0]} is just how you refer to a column, similar to the way you reference items in an array.
}
echo "</table>";
?>
</div>
<br /><br /><br /><br />
<div id="searchpatient" style="border: 1px solid;" width="auto">
	<h3>Search Hospital Database by Lastname</h3>
	<table>
	<form>
		<tr class="noColor">
			<td align="right">Last Name</td><td><input type="text" name="lname" /><span class="exampleText">Example: "F", "Fo", "Forr" will all match "Forrester"</td>
		</tr>
		<tr class="noColor">
			<td>&nbsp;</td><td><input type="Submit" name="Submit" value="Submit" /></td>
		</tr>
	</form></table>
</div>
<?php if(isset($resarray)) 
{ 
	if(count($resarray) > 0)
		print_results($cxn,$resarray); 
	else
		echo "No Patient Found With Last Name '".$_GET['lname']."'.<br />";
} 
?>
<div id="customquery">
<?php
/* Section that executes query and displays the results */

if(!empty($_POST['form']))
{
  $cxn = mysqli_connect($host,$user,$password,
                        $database);
 
  echo "Database Selected: <b>{$database}</b><br>
        Query: <b>{$_POST['query']}</b>
        <h3>Results</h3><hr>";
   print_query($cxn,$_POST['query']);
 /* Display form with only buttons after results */
  $query = str_replace("'","%&%",$_POST['query']);
  echo "<hr><br>
      <form action='{$_SERVER['PHP_SELF']}' method='POST'>
        <input type='hidden' name='query' value='$query'>
        <input type='hidden' name='$database'
               value={$database}>
        <input type='submit' name='queryButton'
               value='New Query'>
        <input type='submit' name='queryButton'
               value='Edit Query'>
      </form>";
  exit();
} 

/* Displays form for query input */
if (@$_POST['queryButton'] != "Edit Query")
{
   $query = " ";
}
else
{
   $query = str_replace("%&%","'",$_POST['query']);
}
?>
<br />
<br />
<br />
<br />
<form action="<?php echo $_SERVER['PHP_SELF'] ?>" 
      method="POST">
<table>
 
 <tr><td style='text-align: right; font-weight: bold' 
         valign="top">Type in your SQL query</td>
     <td><textarea name="query" cols="60"
            rows="10"><?php echo $query ?></textarea></td>
 </tr>
 <tr><td colspan="2" style='text-align: center'>
        <input type="submit" value="Submit Query"></td>
 </tr>
</table>
<input type="hidden" name="form" value="yes">
</form>
</div>

<br /><br /><br /><br />
<h3>Our Group Queries</h3>
<div id="groupQuery1">
<p>Show the max salary from each department and which department it is from (grouped by department). <a href="#toc" class="ToC">Back To Top</a></p>
<?php $query1="SELECT `department`.`dept_name` , MAX( `doctor`.`salary` ) AS 'Max Salary' FROM `department` NATURAL JOIN `doctor` GROUP BY `department`.`dept_name` ;"; 
$from = "`department` NATURAL JOIN `doctor`";?>
<span class="query"><?php echo $query1; ?></span><br />
<?php print_query($cxn,$query1); ?>
</div>

<?php //paragraph text explaining the query?>
<div id="groupQuery2">
<p>Show the patient's first and last names sorted alphabetically by last name, who have been admitted to the same hospital that doctor Boyd Harrison works at. <a href="#toc" class="ToC">Back To Top</a> </p>
<?php 
//variable to hold the query.. has to be all on one line.
$query2="SELECT  `patient`.`fname` ,  `patient`.`lname` FROM  `patient` NATURAL JOIN  `admitted` NATURAL JOIN  `hospital` WHERE ( `admitted`.`hospital_id`) IN ( SELECT  `hospital_id` FROM  `works` NATURAL JOIN  `doctor` WHERE  `doctor`.`lname` =  'Harrison' AND `doctor`.`fname` =  'Boyd') ORDER BY  `patient`.`lname` ASC"; 
$from = "`patient` NATURAL JOIN  `admitted` NATURAL JOIN  `hospital`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query2; ?></span><br />
<?php 
//prints the query results
print_query($cxn,$query2); ?>
</div>


<?php //paragraph text explaining the query?>
<div id="groupQuery3">
<p>Show everyone that has a family name of Haute.<a href="#toc" class="ToC">Back To Top</a> </p>
<?php 
//variable to hold the query.. has to be all on one line.
$query3="SELECT `patient`.`fname`, `patient`.`lname` FROM `patient` NATURAL JOIN `family` WHERE `family_name`='Haute'"; 
$from = "`patient` NATURAL JOIN `family`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query3; ?></span><br />
<?php 
//prints the query results
print_query($cxn,$query3); ?>
</div>


<?php //paragraph text explaining the query?>
<div id="groupQuery4">
<p>Show how many patients (grouped by gender) have no medications, diseases, and allergies <a href="#toc" class="ToC">Back To Top</a></p>
<?php 
//variable to hold the query.. has to be all on one line.
$query4="SELECT `gender`, COUNT(`gender`) AS Count FROM `patient` WHERE (`allergies`='none' AND `diseases`='none' AND `medications`='none') GROUP BY `gender`"; 
$from = "`patient`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query4; ?></span><br />
<?php 
//prints the query results
print_query($cxn,$query4); ?>
</div>


<?php //paragraph text explaining the query?>
<div id="groupQuery5">
<p>Show patients and their age <a href="#toc" class="ToC">Back To Top</a></p>
<?php 
//variable to hold the query.. has to be all on one line.
$query5="SELECT `fname`, `lname`, `DOB`,  year(CURRENT_TIMESTAMP) - year(`DOB`) AS age FROM `patient` ORDER BY `lname`"; 
$from = "`patient`";?>
<span class="query"><?php 
//prints out the query to the web page in a different font inside the span tags.
echo $query5; ?></span><br />
<?php 
//prints the query results
print_query($cxn,$query5); ?>
</div>


</body>
</html>