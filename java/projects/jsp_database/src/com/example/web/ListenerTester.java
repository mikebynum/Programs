package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;


import com.example.model.BusinessBean;
import com.example.model.ContainerBean;

import java.io.*;
import java.sql.*;

public class ListenerTester extends HttpServlet 
{
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		
		
		Connection conn = (Connection)getServletContext().getAttribute("conn");
		
		ResultSet results;

		
		try 
		{
			Statement stmt = conn.createStatement();
			results = stmt.executeQuery("SELECT * FROM business");
			ContainerBean<BusinessBean> cbean = new ContainerBean<BusinessBean>();
			
			while(results.next())
			{
				BusinessBean bean = new BusinessBean();
				bean = parseResults(results);
				cbean.add(bean);
			}

			request.setAttribute("business", cbean);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		
		/**************************************/
		//String destination = "/index.jsp";
 
		//RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		//rd.forward(request, response);
		/**************************************/
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