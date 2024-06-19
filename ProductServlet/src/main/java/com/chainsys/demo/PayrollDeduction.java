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
 * Servlet implementation class PayrollDeduction
 */
@WebServlet("/PayrollDeduction")
public class PayrollDeduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayrollDeduction() {
        super();
   
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String empcode = request.getParameter("empcode");
          ArrayList<User> userList = new ArrayList<>();
          
          try {
              userList = QueryManager.searchEmpCode(empcode);
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
          }
          
          request.setAttribute("userList", userList);
          request.getRequestDispatcher("checkins.jsp").forward(request, response);
      }
  

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			  ArrayList<User> userList = QueryManager.getAllEmployeePayScales();
		        request.setAttribute("userList", userList);
		        request.getRequestDispatcher("employeePayScale.jsp").forward(request, response);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            
	        }
	}

}
