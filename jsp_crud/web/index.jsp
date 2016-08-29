<%@ page import="java.util.*" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title> Current Businesses </title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
		<script src="sortable.js" type="text/javascript"></script>
		<script src="/Sortable.js" type="text/javascript"></script>
		
		<style type="text/css">
			table.sortable td a:link, a:active {}
			.tHeading 
			{
				font-family : Verdana, Geneva, Arial, Helvetica, sans-serif;
				font-size : 7.5pt;
				font-weight : bold;
				color : #000000;
				background-color : #CCCC99;
			}
			tr.even {
				background-color : #EEEECC;
			}
			tr.odd {
				background-color : #FFFFFF;
			}
		</style>
	</head>
	
	<body>
	<h1>Businesses</h1>
		<p> Hello!
		<br />
		<%
			java.util.Date today = new java.util.Date();
			out.println("Today is " + today);
		%>
		</p>
		<h4><a href="/jsp_crud/add">Add A New Business</a></h4>
		<hr></hr>
		<% HttpSession s = request.getSession(); %>
		<h3><c:out value="${sessionScope.msg}"/></h3>
		<table width="100%" class="sortable" border="1" id="businessStuff">
			<thead>
				<tr>
					<th class="tHeading stringSort"></th>
					<th class="tHeading stringSort"></th>
					<th class="tHeading stringSort">Name</th>
					<th class="tHeading stringSort">Address</th>
					<th class="tHeading stringSort">City</th>
					<th class="tHeading stringSort">State</th>
					<th class="tHeading stringSort">ZipCode</th>
					<th class="tHeading stringSort">Phone</th>
					<th class="tHeading stringSort">Latitude</th>
					<th class="tHeading stringSort">Longitude</th>
					<th class="tHeading stringSort">Photo</th>
					<th class="tHeading stringSort">Description</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${business.items}" var="business">
		        	<tr>
		        		<td>
		        			<c:url var="url" scope="page" value="/edit">
                			<c:param name="id" value="${business.id}"/>
            				</c:url>
		        			<a href="${url}">
							<img height="16" width="16" alt="Edit" src="images/edit.gif"/></a>
		        		</td>
		        		<td>
		        			<c:url var="url" scope="page" value="/delete">
                			<c:param name="id" value="${business.id}"/>
            				</c:url>
		        			<a href="${url}">
							<img height="16" width="16" alt="Edit" src="images/delete.gif"/></a>
		        		</td>
		        		<td><c:out value="${business.name}"/></td>
		        		<td><c:out value="${business.address}"/></td>
		        		<td><c:out value="${business.city}"/></td>
		        		<td><c:out value="${business.state}"/></td>
		        		<td><c:out value="${business.zip}"/></td>
		        		<td><c:out value="${business.phone}"/></td>
		        		<td><c:out value="${business.latitude}"/></td>
		        		<td><c:out value="${business.longitude}"/></td>
		        		<td><c:out value="${business.photo}"/></td>
		        		<td><c:out value="${business.description}"/></td>
	        		</tr>
		    	</c:forEach>
			</tbody>
		</table>	
		
	</body>

</html>