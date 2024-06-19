package com.chainsys.demo;


import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SalarySlip
 */
@WebServlet("/SalarySlip")
public class SalarySlip extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalarySlip() {
		super();
	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empCode = request.getParameter("empCode");
		String name= request.getParameter("name");
		String mail=request.getParameter("email");
		int totalCheckinCount = Integer.parseInt(request.getParameter("totalCheckinCount"));     
		double allocatedSalary = Double.parseDouble(request.getParameter("allocatedSalary"));
		int permissionCount = Integer.parseInt(request.getParameter("permissionCount"));
		int sickLeaveDays = Integer.parseInt(request.getParameter("sickLeaveDays"));
		int casualLeaveDays = Integer.parseInt(request.getParameter("casualLeaveDays"));
		double grossSalary = Double.parseDouble(request.getParameter("grossSalary"));
		double pfDeduction = Double.parseDouble(request.getParameter("pfDeduction"));
		double netPay = Double.parseDouble(request.getParameter("netPay"));

		request.setAttribute("Empode", empCode);
		request.setAttribute("mail", mail);
		request.setAttribute("totalCheckinCount", totalCheckinCount);	     
		request.setAttribute("permissionCount", permissionCount);
		request.setAttribute("sickLeaveDays", sickLeaveDays);
		request.setAttribute("casualLeaveDays", casualLeaveDays);
		request.setAttribute("grossSalary", grossSalary);
		request.setAttribute("pfDeduction", pfDeduction);
		request.setAttribute("netPay", netPay);
		request.setAttribute("allocatedSalary", allocatedSalary);

		String mailBody = "Dear " + name + ",\n" +
				"Your payslip details for the current month are as follows:\n\n" +
				"EmpCode: " + empCode + "\n" +
				"Working days: " + totalCheckinCount + "\n" +
				"Allocated Salary: Rs." + allocatedSalary + "\n" +
				"Permission Count: " + permissionCount + "\n" +
				"Sick Leave Days: " + sickLeaveDays + "\n" +
				"Casual Leave Days: " + casualLeaveDays + "\n" +
				"Gross Salary: Rs." + grossSalary + "\n" +
				"PF Deduction: Rs." + pfDeduction + "\n" +
				"Net Pay: Rs." + netPay + "\n\n" +
				"Thank you for your hard work.\n\n" +
				"Regards,\n" +
				"HR Department";

		try {
			MailRespond.Properties();
			MailRespond.MailBody(mail,mailBody);
		} catch (MessagingException e) {
			e.printStackTrace();
		}


		request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
	}
}