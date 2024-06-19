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
 * Servlet implementation class SearchNames
 */
@WebServlet("/SearchNames")
public class SearchNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNames() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empcode = request.getParameter("empcode");
          ArrayList<User> userList = new ArrayList<>();
          
          try {
              userList = QueryManager.searchLeave(empcode);
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
          }
          
          request.setAttribute("userList", userList);
          request.getRequestDispatcher("LeaveSummary.jsp").forward(request, response);
      
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String empcode = request.getParameter("empcode");
          ArrayList<User> userList = new ArrayList<>();
          
          try {
              userList = QueryManager.searchPermission(empcode);
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
          }
          
          request.setAttribute("permissionCountList", userList);
          request.getRequestDispatcher("ViewPermission.jsp").forward(request, response);
      
		
	}

}
