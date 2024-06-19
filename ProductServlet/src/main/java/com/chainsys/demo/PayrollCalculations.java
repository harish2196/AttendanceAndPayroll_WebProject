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

/**
 * Servlet implementation class PayrollCalculations
 */
@WebServlet("/PayrollCalculations")
public class PayrollCalculations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayrollCalculations() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empCode = request.getParameter("empCode");
		String empSalary = request.getParameter("allocatedSalary");

		User user = new User();

		int totalCheckinCount, sickLeaveDays, casualLeaveDays, permissionCount;
		String mail,name;
		try {
			user.setEmpCode(empCode);
			name=QueryManager.getEmployeeName(empCode);
			user.setName(name);
			mail=QueryManager.getEmployeeEmail(empCode);
			user.setEmail(mail);
			totalCheckinCount = QueryManager.getTotalCheckinCount(empCode);
			user.setTotalCheckinCount(totalCheckinCount);
			sickLeaveDays = QueryManager.countSickLeavePayroll(empCode);
			user.setSickLeaveDays(sickLeaveDays);
			casualLeaveDays = QueryManager.countCasualLeavePayroll(empCode);
			user.setCasualLeaveDays(casualLeaveDays);
			permissionCount = QueryManager.countPermissionsPayroll(empCode);
			user.setPermissionCount(permissionCount);
			int allocateSalary= QueryManager.getSalary(empCode);
			user.setAllocate_salary(allocateSalary);

			double salary = Double.parseDouble(empSalary);
			double dailySalary = salary / 22;

			double checkinsSalary = dailySalary * totalCheckinCount; 

			if (permissionCount > 3) {
				double deduction = (permissionCount - 3) * (checkinsSalary / 2);
				salary -= deduction;
			}

			if (sickLeaveDays > 2) {
				int extraSickLeaveDays = sickLeaveDays - 2;
				salary -= extraSickLeaveDays * checkinsSalary;
			}

			if (casualLeaveDays > 1) {
				int extraCasualLeaveDays = casualLeaveDays - 1;
				salary -= extraCasualLeaveDays * checkinsSalary;
			}
			user.setGrossPay(checkinsSalary);
			double pfDeduction = checkinsSalary * 0.12;
			user.setPf(pfDeduction);
			user.setPf(pfDeduction);
			double netPay = checkinsSalary - pfDeduction;
			user.setNetPay(netPay);

			QueryManager.payrollPays(user, empCode);

			request.setAttribute("user", user);
			request.setAttribute("dailySalary", dailySalary);
			request.setAttribute("grossSalary", (int) checkinsSalary); 
			request.setAttribute("pfDeduction", pfDeduction);
			request.setAttribute("netPay", netPay);

			RequestDispatcher dispatcher = request.getRequestDispatcher("payrollInformation.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String empCode = (String) session.getAttribute("emp_code");

		try {
			String getEmpName=QueryManager.getEmployeeName(empCode);//name
			String getEmplEmail= QueryManager.getEmployeeEmail(empCode);//email
			int permissionCount = QueryManager.countPermissionsPayroll(empCode);//permission
			int sickLeaveDays = QueryManager.countSickLeavePayroll(empCode);//sick
			int casualLeaveDays = QueryManager.countCasualLeavePayroll(empCode);//casual
			int getTotalCheckinCount = QueryManager.getTotalCheckinCount(empCode);//workingDays
			int salary= QueryManager.getSalary(empCode);//salary


			User user = new User();

			user.setEmpCode(empCode);
			user.setName(getEmpName);
			user.setEmail(getEmplEmail);
			user.setPermissionCount(permissionCount);
			user.setSickLeaveDays(sickLeaveDays);
			user.setCasualLeaveDays(casualLeaveDays);
			user.setTotalCheckinCount(getTotalCheckinCount);
			user.setAllocate_salary(salary);

			QueryManager.insertOrUpdateLeaveTypes(user);


			session.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("payrollCalculation.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}



}