package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

import com.example.model.BusinessBean;

//Servlet to receive post data from edit.jsp and change the record, redirect
//back to List Page

public class EditDo extends HttpServlet
{
	
	String sql = "UPDATE business SET `name` = ?, `address` = ?, `address2` = ?, `city` = ?, `state` = ?, " +
			"`zip` = ?, `phone` = ?, `lat` = ?, `long` = ?, `photo` = ?, `description` = ? WHERE `id` = ?";
	
	public void doGet (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
	{		
		try
		{
			//String updateId = "null";
			//String sql = "SELECT * FROM business WHERE id = ?";
			
			Connection conn = (Connection)getServletContext().getAttribute("conn");
			//ResultSet results;
			//updateId = request.getParameter("id");
			
			 PreparedStatement pst = conn.prepareStatement(sql);
			 
			  pst.setString(1, request.getParameter("name"));
			  pst.setString(2, request.getParameter("address"));
			  pst.setString(3, request.getParameter("address2"));
			  pst.setString(4, request.getParameter("city"));
			  pst.setString(5, request.getParameter("state"));
			  pst.setString(6, request.getParameter("zip"));
			  pst.setString(7, request.getParameter("phone"));
			  pst.setString(8, request.getParameter("latitude"));
			  pst.setString(9, request.getParameter("longitude"));
			  pst.setString(10, request.getParameter("photo"));
			  pst.setString(11, request.getParameter("description"));
			  pst.setString(12, request.getParameter("id2"));
			 
			//Statement stmt = conn.createStatement();
			
			int successResults = pst.executeUpdate();
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		HttpSession s = request.getSession();
		
		String destination = "/jsp_crud/list";
		
		response.sendRedirect(destination);
	}
	
	   @Override  
	    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {  
	 
	        doGet(request, response);  
	          
	    }
	

}
