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

@WebServlet("/EmployeeLeave")
public class EmployeeLeave extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeLeave() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String empCode = (String) session.getAttribute("emp_code");
       
        try {
            ArrayList<User> userList = QueryManager.getEmpDataEmployee(empCode);
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewEmpData.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
         
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmpCode(request.getParameter("empId"));
        user.setName(request.getParameter("name"));
        user.setFromDate(request.getParameter("fromDate"));
        user.setToDate(request.getParameter("toDate"));
        user.setLeaveType(request.getParameter("leaveType"));
        user.setReason(request.getParameter("reason"));

        HttpSession session = request.getSession();
        String empCode = (String) session.getAttribute("emp_code");
        try {
            QueryManager.insertData(user, empCode);
            response.sendRedirect("Leave.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
