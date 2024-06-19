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
 * Servlet implementation class Permission
 */
@WebServlet("/Permission")
public class Permission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Permission() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String empCode = (String) session.getAttribute("emp_code");

			request.getRequestDispatcher("Home.jsp").forward(request, response);
	                         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String choice = request.getParameter("action");

		String option = request.getParameter("options");

		if(choice.equals("thisorthat"))
		{
			if(option.equals("permission"))
			{
				 ArrayList<User> permissionCountList;
				try {
					permissionCountList = QueryManager.getAllPermissions();
					 request.setAttribute("permissionCountList", permissionCountList);
			           request.getRequestDispatcher("ViewPermission.jsp").forward(request, response);
			           
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	
				
			}
			else if(option.equals("Leave"))
			{
				 ArrayList<User> userList;
					try {
						userList = QueryManager.getEmpDataAdmin();
						 request.setAttribute("userList", userList);
				         request.getRequestDispatcher("LeaveSummary.jsp").forward(request, response);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}

			}
		}


	}

}
