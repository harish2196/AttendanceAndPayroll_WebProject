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

/**
 * Servlet implementation class PermissionManager
 */
@WebServlet("/PermissionManager")
public class PermissionManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PermissionManager() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empCode = request.getParameter("empCode");
        String status = request.getParameter("action");
       
        try {
            if (status != null) {
                QueryManager.updatePermissionStatus(empCode, status);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
       
        try {
            ArrayList<User> permissionCountList = QueryManager.getEmpPermissionAdmin();
            request.setAttribute("permissionCountList", permissionCountList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ViewPermission.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 



	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User user = new User();
	      
	    user.setName(request.getParameter("name"));
	    user.setDate(request.getParameter("date"));
	    user.setStart_time(request.getParameter("start_time"));
	    user.setEnd_time(request.getParameter("end_time"));
	
	    request.setAttribute("user", user);
	    
	    HttpSession session = request.getSession();
	    String empCode = (String) session.getAttribute("emp_code");
	    
	    try {
	        QueryManager.insertPermission(user, empCode);
	      
	        response.sendRedirect("PermissionForm.jsp"); 
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	       }
	}
	
	

}
