package com.example.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.BusinessBean;

public class Delete extends HttpServlet
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
		
		getServletContext().getRequestDispatcher("/delete_business.jsp").forward(request, response);
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
