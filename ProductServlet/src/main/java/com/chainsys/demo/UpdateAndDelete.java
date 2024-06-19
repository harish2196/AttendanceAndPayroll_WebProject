package com.chainsys.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/UpdateAndDelete")
public class UpdateAndDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAndDelete() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        String empCode = (String) session.getAttribute("emp_code");
	       
	        try {
	        	 ArrayList<User> permissionCountList = QueryManager.getAllPermissions(empCode);
	        	
	        	 request.setAttribute("permissionCountList", permissionCountList);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("permissionEmployee.jsp");
	            dispatcher.forward(request, response);
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	         
	        }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String choice = request.getParameter("action");

	    if (choice != null) {
	        switch(choice) {
	            case "delete": 
	                try {
	                     QueryManager.deleteEmployee(request.getParameter("id"));
	                    ArrayList<User> userList = QueryManager.getEmployeeData();
	                    request.setAttribute("userList", userList);
	                    request.getRequestDispatcher("displayEmployeeDetails.jsp").forward(request, response);

	                } catch (SQLException e) {
	                    e.printStackTrace();
	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                }
	             
	            case "update":
	                User user = new User();
	                user.setName(request.getParameter("name"));
	                user.setRole(request.getParameter("role"));
	                user.setEmail(request.getParameter("email"));
	                user.setMobile(request.getParameter("contact"));
	                user.setSalary(request.getParameter("salary"));
	                user.setEmpCode(request.getParameter("id")); 

	                try {
	                    boolean success = QueryManager.updateEmployeeData(user, request.getParameter("id"));
	                    if (success) {                           
	                        try {
	                            ArrayList<User> userList = QueryManager.getEmployeeData();
	                            request.setAttribute("userList", userList);
	                            request.getRequestDispatcher("displayEmployeeDetails.jsp").forward(request, response);
	                        } catch (ClassNotFoundException e) {
	                            e.printStackTrace();
	                        }

	                    } else {
	                        System.out.println("Update failed");
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                } catch (ClassNotFoundException e1) {
					
						e1.printStackTrace();
					}
	                break;
	            default:       
	                break;
	        }
	    } else {
	        System.out.println("Error");
	    }
	}
}