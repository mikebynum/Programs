package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Servlet to delete record and redirect back to List Page

public class DeleteDo extends HttpServlet
{
	String sql = "DELETE FROM business WHERE id = ?";
	String message = "";
	
	public void doGet (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
	{
		int successResults = 0;
		HttpSession s = request.getSession();
		try
		{
			Connection conn = (Connection)getServletContext().getAttribute("conn");
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, request.getParameter("id2"));
			
			successResults = pst.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if (successResults > 0)
			message = "Successfully deleted " + "'" + request.getParameter("name")+"'";
		
		s.setAttribute("msg", message);
		
		String destination = "/jsp_crud/list";
		
		response.sendRedirect(destination);
	}
	
	   @Override  
	    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {  
	 
	        doGet(request, response);  
	          
	    }

}
