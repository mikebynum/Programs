<?php
/*Program:  mysql_send.php
 *Desc:     PHP program that sends an SQL query to the
 *          MySQL server and displays the results.
 */
echo "<html>
      <head><title>SQL Query Sender</title></head>
      <body>";
if(ini_get("magic_quotes_gpc") == "1")
{
   $_POST['query'] = stripslashes($_POST['query']);
}
$host="localhost";
$user="mbynum";
$password="password";

/* Section that executes query and displays the results */
if(!empty($_POST['form']))
{
  $cxn = mysqli_connect($host,$user,$password,
                        $_POST['database']);
  $result = mysqli_query($cxn,$_POST['query']);
  echo "Database Selected: <b>{$_POST['database']}</b><br>
        Query: <b>{$_POST['query']}</b>
        <h3>Results</h3><hr>";
  if($result == false)
  {
     echo "<h4>Error: ".mysqli_error($cxn)."</h4>";
  }
  elseif(@mysqli_num_rows($result) == 0)
  {
     echo "<h4>Query completed. 
            No results returned.</h4>";
  }
  else
  {
   /* Display results */
     echo "<table border='1'><thead><tr>";
     $finfo = mysqli_fetch_fields($result);
     foreach($finfo as $field)
     {
        echo "<th>".$field->name."</th>";
     }
     echo "</tr></thead>
           <tbody>";
     for ($i=0;$i < mysqli_num_rows($result);$i++)
     {
        echo "<tr>";
        $row = mysqli_fetch_row($result);
        foreach($row as $value)
        {
           echo "<td>".$value."</td>";
        }
        echo "</tr>";
     }
     echo "</tbody></table>";
  } 
 /* Display form with only buttons after results */
  $query = str_replace("'","%&%",$_POST['query']);
  echo "<hr><br>
      <form action='{$_SERVER['PHP_SELF']}' method='POST'>
        <input type='hidden' name='query' value='$query'>
        <input type='hidden' name='database'
               value={$_POST['database']}>
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
<form action="<?php echo $_SERVER['PHP_SELF'] ?>" 
      method="POST">
<table>
 <tr><td style='text-align: right; font-weight: bold'>
         Type in database name</td> 
     <td><input type="text" name="database"
            value=<?php echo @$_POST['database'] ?> ></td>
 </tr>
 <tr><td style='text-align: right; font-weight: bold' 
         valign="top">Type in SQL query</td>
     <td><textarea name="query" cols="60"
            rows="10"><?php echo $query ?></textarea></td>
 </tr>
 <tr><td colspan="2" style='text-align: center'>
        <input type="submit" value="Submit Query"></td>
 </tr>
</table>
<input type="hidden" name="form" value="yes">
</form>
</body></html>