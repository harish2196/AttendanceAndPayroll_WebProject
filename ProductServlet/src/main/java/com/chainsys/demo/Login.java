package com.chainsys.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String empCode = (String) session.getAttribute("emp_code");
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			insertCheckOutTime(connection, empCode);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

	public void insertCheckOutTime(Connection connection, String emp_code) {
		try {
			String insertCheckOutQuery = "UPDATE check_ins SET checkout_time = NOW() WHERE emp_code = ? AND checkout_time IS NULL";
			PreparedStatement insertCheckOutStatement = connection.prepareStatement(insertCheckOutQuery);
			insertCheckOutStatement.setString(1, emp_code);
			insertCheckOutStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			String viewQuery = "SELECT * FROM Employee_details WHERE username=? AND userpassword=?";
			PreparedStatement preparedStatement = connection.prepareStatement(viewQuery);

			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				session.setAttribute("name", rs.getString("username"));
				String empCode = rs.getString("emp_code");
				session.setAttribute("emp_code", empCode);
				
				
				dispatcher = request.getRequestDispatcher("Home.jsp");


				insertCheckInTime(connection, empCode, user.getName()); 
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "error");
			dispatcher = request.getRequestDispatcher("login.jsp");
		}

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	public void insertCheckInTime(Connection connection, String emp_code, String name) { 
		try {
			String insertCheckInQuery = "INSERT INTO check_ins (emp_code, name, checkin_time) VALUES (?, ?, NOW())"; 
			PreparedStatement insertCheckInStatement = connection.prepareStatement(insertCheckInQuery);
			insertCheckInStatement.setString(1, emp_code);
			insertCheckInStatement.setString(2, name); 
			insertCheckInStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}