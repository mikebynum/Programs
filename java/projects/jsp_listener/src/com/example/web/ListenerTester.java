package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ListenerTester extends HttpServlet 
{
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		
		
		Dog dog = (Dog) getServletContext().getAttribute("dog");
		String breed = dog.getBreed();
		
		request.setAttribute ("dogBreed", breed);
		
		
		
		/**************************************/
		String destination = "/result.jsp";
 
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
		/**************************************/
	}
}