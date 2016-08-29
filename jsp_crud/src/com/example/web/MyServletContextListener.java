package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MyServletContextListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent event) 
	{
		ServletContext sc = event.getServletContext(); //ask the event for the ServletContext
		String url = sc.getInitParameter("database_url"); //use the context to get the init parameter
		String driver = sc.getInitParameter("database_driver"); //use the context to get the init parameter
		String username = sc.getInitParameter("database_username"); //use the context to get the init parameter
		String password = sc.getInitParameter("database_password"); //use the context to get the init parameter
		//Dog d = new Dog(dogBreed);//make a new dog
		
		try 
		{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			sc.setAttribute("conn",conn);
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Class not found. DriverError.");
		} 
		catch (SQLException e) 
		{
			System.out.println("SQL Exception: " + e.getMessage());
		}
		//use the context to set an attribute (a name/object pair)
		//that is the Dog.  Now other parts of the app will be able to get
		//the value of the attribute(the Dog).
		//sc.setAttribute("dog", d);
	}
	
	public void contextDestroyed(ServletContextEvent event) 
	{
		try 
		{
			ServletContext sc =event.getServletContext();
			Connection conn = (Connection)
			sc.getAttribute("conn");
			conn.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQL: " + e.getMessage());
		}
	}

}