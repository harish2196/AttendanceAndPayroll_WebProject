package com.chainsys.demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ProjectDemo
 */
@WebServlet("/ProjectDemo")
@MultipartConfig
public class ProjectDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectDemo() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			 ArrayList<User> userList = QueryManager.getEmployeeData();
			 request.setAttribute("userList", userList);
			 request.getRequestDispatcher("displayEmployeeDetails.jsp").forward(request, response);

		} catch (SQLException e) {		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    InputStream inputStream = null;
	    Part filePart = request.getPart("image");
	    if (filePart != null) {
	        inputStream = filePart.getInputStream();
	    }

	    byte[] imageBytes = null;
	    if (inputStream != null) {
	        try {
	            imageBytes = inputStream.readAllBytes();
	        } finally {
	            inputStream.close(); 
	        }
	    }

	    User user = new User();
	    user.setName(request.getParameter("name"));
	    user.setRole(request.getParameter("role"));
	    user.setEmail(request.getParameter("email"));
	    user.setPassword(request.getParameter("pass"));
	    user.setMobile(request.getParameter("contact"));
	    user.setImageData(imageBytes);

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
	        String insertQuery = "INSERT INTO Employee_details (username, designation, useremail, userpassword, usermobile, image, emp_code,soft_delete) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
	        preparedStatement = connection.prepareStatement(insertQuery);
	        preparedStatement.setString(1, user.getName());
	        preparedStatement.setString(2, user.getRole());
	        preparedStatement.setString(3, user.getEmail());
	        preparedStatement.setString(4, user.getPassword());
	        preparedStatement.setString(5, user.getMobile());
	        preparedStatement.setBytes(6, user.getImageData());

	        int randomNumber = 1000 + (int) (Math.random() * 9000);
	        preparedStatement.setInt(7, randomNumber); // Corrected index

	        int rowCount = preparedStatement.executeUpdate();
	        if (rowCount > 0) {
	            request.setAttribute("status", "success");
	            request.setAttribute("randomNumber", randomNumber);
	        } else {
	            request.setAttribute("status", "failed");
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	            if (filePart != null) {
	                filePart.delete(); 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    request.getRequestDispatcher("registration.jsp").forward(request, response);
	}
}