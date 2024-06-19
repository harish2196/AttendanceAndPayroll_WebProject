package com.chainsys.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LeaveCount
 */
@WebServlet("/LeaveCount")
public class LeaveCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user=new User();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveCount() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromDate=request.getParameter("fromDate");
	        String action = request.getParameter("action");
	        String empCode = request.getParameter("empCode");
	        try {
	            if (action != null) {
	                if (action.equalsIgnoreCase("rejected")) {
	                	 int totalLeaveDays = QueryManager.remainrejectLeaveDays(empCode);
	                	 QueryManager.insertTotalLeaveDays(empCode, totalLeaveDays);
	                    QueryManager.updateLeaveStatus(fromDate, "Rejected");
	                    System.err.println("---->" + totalLeaveDays);
	                 
	                } else if (action.equalsIgnoreCase("accepted")) {
	                	 
	                	 int totalLeaveDays = QueryManager.getTotalLeaveDays(empCode);
	                     QueryManager.insertTotalLeaveDays(empCode, totalLeaveDays);
	                    QueryManager.updateLeaveStatus(fromDate, "Accepted");
	                }
	            }
	           
	            ArrayList<User> userList = QueryManager.getEmpDataAdmin();     
	      
	            request.setAttribute("userList", userList);
	            request.getRequestDispatcher("LeaveSummary.jsp").forward(request, response);
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            
	        }
	    }
	




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		 HttpSession session = request.getSession();
		    String empCode = (String) session.getAttribute("emp_code");

		    String status = request.getParameter("action");
		    try {
		        if (status != null) {
		            if (status.equalsIgnoreCase("rejected")) {
		                QueryManager.updateLeaveStatus(empCode, status);
		                return; 
		            } else if (status.equalsIgnoreCase("accepted")) {
		                QueryManager.updateLeaveStatus(empCode, status);
		            }
		        }
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		      
		    }

		    try {
		        int totalLeaveDays = QueryManager.getTotalLeaveDays(empCode);
		        User user = new User(); 
		        user.setTotal_days(totalLeaveDays);
		        QueryManager.insertTotalLeaveDays(empCode, totalLeaveDays);
		        QueryManager.getTotalLeaveDays(empCode);
		        ArrayList<User> userList = QueryManager.getEmpLeaveCount(empCode);
		        request.setAttribute("userList", userList);
		        request.getRequestDispatcher("LeaveSummary.jsp").forward(request, response);
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        // Handle exception
		    }
	}

}
