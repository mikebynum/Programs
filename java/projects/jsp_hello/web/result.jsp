<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	<title> Results Form </title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	</head>
	
	<body>
		<p> Hello,
		<%
			String name = request.getParameter("name");
			out.println(name);
			
		%>
		!
		<br />
		<%
			java.util.Date today = new java.util.Date();
			out.println("Today is" + today);
		%>
		</p>
		<p>testing</p>
	</body>

</html>