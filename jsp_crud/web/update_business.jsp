<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Update A Business</title>

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

	<table width="500" border="0" cellspacing="0" cellpadding="2" align="center">
		<tr>
			<td>
				<form action="editDo" method="GET">
					<h1>Update A Business</h1>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
						
						<tr>
								<td align="right" valign="bottom" style="padding-bottom:4px;padding-right:4px;" nowrap="NOWRAP">
									<div>
										<span>All Fields Are Required</span>
									</div>
								</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<div style="margin-top:10px;">
									<input type="submit" value="  Save  " class="" style="margin:4px;">
									<input type="button" name="cancel" value="Cancel" class="" onclick="window.location = '/jsp_crud/list'" style="margin:4px;">
								</div>
							</td>
						</tr>
								<tr>
									<td colspan="2" align="center" valign="top" class="cagBorder">
										<table border="0" cellspacing="0" cellpadding="4">
											
											<tr>
												<td valign="top">Business Name:</td>
												<td valign="top">
													<input type="hidden" name="id2" value="${requestScope.business.id}"/>
													<input type="text" name="name" size="41" maxlength="30" value="${requestScope.business.name}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Address:</td>
												<td valign="top">
													<input type="text" name="address" size="41" maxlength="40" value="${requestScope.business.address}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Address 2:</td>
												<td valign="top">
													<input type="text" name="address2" size="41" maxlength="50" value="${requestScope.business.address2}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">City:</td>
												<td valign="top">
													<input type="text" name="city" size="41" maxlength="20" value="${requestScope.business.city}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">State:</td>
												<td valign="top">
													<input type="text" name="state" size="41" maxlength="2" value="${requestScope.business.state}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Zip:</td>
												<td valign="top">
													<input type="text" name="zip" size="41" maxlength="11" value="${requestScope.business.zip}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Phone:</td>
												<td valign="top">
													<input type="text" name="phone" size="41" maxlength="15" value="${requestScope.business.phone}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Latitude:</td>
												<td valign="top">
													<input type="text" name="latitude" size="41" maxlength="11" value="${requestScope.business.latitude}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Longitude:</td>
												<td valign="top">
													<input type="text" name="longitude" size="41" maxlength="11" value="${requestScope.business.longitude}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Photo:</td>
												<td valign="top">
													<input type="text" name="photo" size="41" maxlength="30" value="${requestScope.business.photo}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Description:</td>
												<td valign="top">
													<input type="text" name="description" size="41" maxlength="70" value="${requestScope.business.description}"/>
												</td>
											</tr>

										</table>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>

</body>
</html>