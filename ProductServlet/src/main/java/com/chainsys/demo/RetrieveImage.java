package com.chainsys.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RetrieveImage
 */
@WebServlet("/RetrieveImage")
public class RetrieveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveImage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  int empCode = Integer.parseInt(request.getParameter("emp_code"));

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		            String selectQuery = "SELECT image FROM Employee_details WHERE emp_code = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
		            preparedStatement.setInt(1, empCode);
		            ResultSet resultSet = preparedStatement.executeQuery();

		            if (resultSet.next()) {
		                byte[] imgData = resultSet.getBytes("image");
		                if (imgData != null) {
		                    response.setContentType("image/jpeg");
		                    response.getOutputStream().write(imgData);
		                } else {
		                   System.out.println("No Photos found!");
		                }
		            } else {
		                System.out.println("No record found!");
		            }
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		        } 
		    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
