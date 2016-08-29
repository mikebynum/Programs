package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyServletContextListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent event) 
	{
		ServletContext sc = event.getServletContext(); //ask the event for the ServletContext
		String dogBreed = sc.getInitParameter("breed"); //use the context to get the init parameter
		Dog d = new Dog(dogBreed);//make a new dog

		//use the context to set an attribute (a name/object pair)
		//that is the Dog.  Now other parts of the app will be able to get
		//the value of the attribute(the Dog).
		sc.setAttribute("dog", d);
	}
	
	public void contextDestroyed(ServletContextEvent event) 
	{
		// nothing to do here
	}

}