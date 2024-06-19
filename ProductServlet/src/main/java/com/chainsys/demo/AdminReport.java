package com.chainsys.demo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.backend.Sender;

/**
 * Servlet implementation class AdminReport
 */
@WebServlet("/AdminReport")
public class AdminReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminReport() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String empCode = (String) session.getAttribute("emp_code");

		String name = request.getParameter("name");
		String comments = request.getParameter("comments");
		try {
			QueryManager.insertReport(empCode,name,comments);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		

		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminReport.jsp");
		dispatcher.forward(request, response);

	}

}
