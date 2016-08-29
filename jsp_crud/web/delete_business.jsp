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
<title>Delete A Business</title>
</head>
<body>
<table width="500" border="0" cellspacing="0" cellpadding="2" align="center">
		<tr>
			<td>
				<form action="deleteDo" method="GET">
					<h1>Delete A Business</h1>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td></td>
								<td align="right" valign="bottom" style="padding-bottom:4px;padding-right:4px;" nowrap="NOWRAP">
									<hr>
									<div>
										<h4 align="center"><c:out value="${requestScope.message}"/></h4>
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
													<input type="hidden" name="name" value="${requestScope.business.name}"/>
													<c:out value="${requestScope.business.name}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Address:</td>
												<td valign="top">
													<c:out value="${requestScope.business.address}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Address 2:</td>
												<td valign="top">
													<c:out value="${requestScope.business.address2}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">City:</td>
												<td valign="top">
													<c:out value="${requestScope.business.city}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">State:</td>
												<td valign="top">
													<c:out value="${requestScope.business.state}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Zip:</td>
												<td valign="top">
													<c:out value="${requestScope.business.zip}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Phone:</td>
												<td valign="top">
													<c:out value="${requestScope.business.phone}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Latitude:</td>
												<td valign="top">
													<c:out value="${requestScope.business.latitude}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Longitude:</td>
												<td valign="top">
													<c:out value="${requestScope.business.longitude}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Photo:</td>
												<td valign="top">
													<c:out value="${requestScope.business.photo}"/>
												</td>
											</tr>
											<tr>
												<td valign="top">Description:</td>
												<td valign="top">
													<c:out value="${requestScope.business.description}"/>
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