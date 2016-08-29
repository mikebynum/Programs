package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class hello extends HttpServlet
{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{	
		String destination = "/result.jsp";
 
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}
}