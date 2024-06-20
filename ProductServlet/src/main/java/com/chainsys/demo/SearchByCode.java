package com.chainsys.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchByCode
 */
@WebServlet("/SearchByCode")
public class SearchByCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByCode() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String empcode = request.getParameter("empcode");
        ArrayList<User> userList = new ArrayList<>();
        
        try {
            userList = QueryManager.searchEmployeeComments(empcode);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("displayReports.jsp").forward(request, response);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empcode = request.getParameter("empcode");
        ArrayList<User> userList = new ArrayList<>();
        
        try {
            userList = QueryManager.searchEmployee(empcode);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("displayEmployeeDetails.jsp").forward(request, response);
    
		
	}

}
