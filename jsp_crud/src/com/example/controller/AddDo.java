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

import com.example.model.BusinessBean;

//Servlet to receive post data from add.jsp and add record, redirect back to
//the List Page.

public class AddDo extends HttpServlet
{
	String sql = "INSERT INTO business " +
			"(`name`,`address`,`address2`,`city`,`state`,`zip`,`phone`,`lat`,`long`,`photo`,`description`) " +
			"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	String name = null;
	String address = null;
	String address2 = null;
	String city = null;
	String state = null;
	int zip = 0;
	String phone = null;
	float latitude = 0;
	float longitude = 0;
	String photo = null;
	String description = null;
	
	String errorMessage = null;
	
	BusinessBean bean = new BusinessBean();
	boolean errors = false;
	
	
	public void doPost (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException 
	{		
		try
		{
			HttpSession s = request.getSession();
			
			//do some error checking before anything happens
			bean.setName(request.getParameter("name"));
			bean.setAddress(request.getParameter("address"));
			bean.setAddress2(request.getParameter("address2"));
			bean.setCity(request.getParameter("city"));
			bean.setState(request.getParameter("state"));
			//if(!request.getParameter("zip").isEmpty()){
			//	bean.setZip(Integer.parseInt(request.getParameter("zip")));}
			bean.setZip(request.getParameter("zip"));
			bean.setPhone(request.getParameter("phone"));
			//if(!request.getParameter("latitude").isEmpty()){
			//latitude = Float.valueOf(request.getParameter("latitude").trim()).floatValue();}
			bean.setLatitude(request.getParameter("latitude"));
			//if(!request.getParameter("longitude").isEmpty()){
			//longitude = Float.valueOf(request.getParameter("longitude").trim()).floatValue();}
			bean.setLongitude(request.getParameter("longitude"));
			bean.setPhoto(request.getParameter("photo"));
			bean.setDescription(request.getParameter("description"));
			
			request.setAttribute("business", bean);
			
			errors = checkForErrors();
			
			s.setAttribute("message", errorMessage);
			
			
			
			if (!errors)
			{
				Connection conn = (Connection)getServletContext().getAttribute("conn");
				
				 PreparedStatement pst = conn.prepareStatement(sql);
				 
				  /*pst.setString(1, name);
				  pst.setString(2, address);
				  pst.setString(3, address2);
				  pst.setString(4, city);
				  pst.setString(5, state);
				  pst.setInt(6, zip);
				  pst.setString(7, phone);
				  pst.setFloat(8, latitude);
				  pst.setFloat(9, longitude);
				  pst.setString(10, photo);
				  pst.setString(11, description);*/
				 
				 pst.setString(1,bean.getName());
				 pst.setString(2, bean.getAddress());
				 pst.setString(3, bean.getAddress2());
				 pst.setString(4, bean.getCity());
				 pst.setString(5, bean.getState());
				 pst.setString(6, bean.getZip());
				 pst.setString(7, bean.getPhone());
				 pst.setString(8, bean.getLatitude());
				 pst.setString(9, bean.getLongitude());
				 pst.setString(10, bean.getPhoto());
				 pst.setString(11, bean.getDescription());
				  
				 
				
				  int successResults = pst.executeUpdate();;
				  
				  if (successResults > 0)
				  {
					  errorMessage = "Successfully added '" + bean.getName() + "' !";
					  s.setAttribute("msg", errorMessage);
				  }
						  
				  
					String destination = "/jsp_crud/list";
					
					response.sendRedirect(destination);
					 
					//RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
					//rd.forward(request, response);
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if(errors)
		{
			String destination = "/add";
			 
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}

	}
	
	public boolean checkForErrors()
	{
		boolean errors = false;
		
		name = bean.getName();
		address = bean.getAddress();
		address2 = bean.getAddress2();
		city = bean.getCity();
		state = bean.getState();
		
		if (!bean.getZip().isEmpty())
		zip = Integer.parseInt(bean.getZip());
		
		phone = bean.getPhone();
		
		if (!bean.getLatitude().isEmpty())
		latitude = Float.valueOf(bean.getLatitude().trim()).floatValue();
		
		if (!bean.getLongitude().isEmpty())
		longitude = Float.valueOf(bean.getLongitude().trim()).floatValue();
		
		photo = bean.getPhoto();
		description = bean.getDescription();
		
		//check to see if any fields are blank
		if(name.isEmpty()|address.isEmpty()|address2.isEmpty()|city.isEmpty()|state.isEmpty()|zip==0|
				phone.isEmpty()|latitude==0|longitude==0|photo.isEmpty()|description.isEmpty())
		{
			errorMessage = "There are fields left blank.  All fields are required";
			errors = true;
			return errors;
		}
		
		return errors;
	}
}
