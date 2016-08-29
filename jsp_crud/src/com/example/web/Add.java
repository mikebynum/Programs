package com.example.web;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.*;

//Servlet to display add.jsp form

@SuppressWarnings("serial")
public class Add extends HttpServlet
{
	public void doGet (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
	{
		getServletContext().getRequestDispatcher("/add_business.jsp").forward(request, response);
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
	{
		getServletContext().getRequestDispatcher("/add_business.jsp").forward(request, response);
	}
			
}
