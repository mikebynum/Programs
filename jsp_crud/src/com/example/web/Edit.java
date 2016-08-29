package com.example.web;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.BusinessBean;
//import com.example.model.ContainerBean;

import java.io.*;
import java.sql.*;

//This page will take a query string parameter (from the URL) and pre-fill the form for a user to
//edit a Business record.

//Servlet to load data and display it in edit.jsp

@SuppressWarnings("serial")

public class Edit extends HttpServlet
{
	public void doGet (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
	{		
		
		try
		{
			String updateId = "null";
			String sql = "SELECT * FROM business WHERE id = ?";
			
			Connection conn = (Connection)getServletContext().getAttribute("conn");
			ResultSet results;
			updateId = request.getParameter("id");
			
			 PreparedStatement pst = conn.prepareStatement(sql);
			 
			 pst.setString(1, updateId);
			 
			//Statement stmt = conn.createStatement();
			
			results = pst.executeQuery();
			
			BusinessBean output = null;
			
			if(results.next()) 
			{
				output = parseResults(results);
			}
			
			request.setAttribute("business", output);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/update_business.jsp").forward(request, response);
	}
	
	
	
	private BusinessBean parseResults(ResultSet rs) throws SQLException 
	{
		BusinessBean bean = new BusinessBean();
		
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setAddress(rs.getString("address"));
		bean.setAddress2(rs.getString("address2"));
		bean.setCity(rs.getString("city"));
		bean.setState(rs.getString("state"));
		bean.setZip(rs.getString("zip"));
		bean.setPhone(rs.getString("phone"));
		bean.setLongitude(rs.getString("long"));
		bean.setLatitude(rs.getString("lat"));
		bean.setPhoto(rs.getString("photo"));
		bean.setDescription(rs.getString("description"));
		
		return bean;
	}

}
