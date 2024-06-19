package com.chainsys.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class QueryManager {
	
	public static void insertData(User user, String empCode) throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();
		String leaveInserting = "INSERT INTO Leave_report (emp_code, name, from_date, to_date, leave_type) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement   preparedStatement = connection.prepareStatement(leaveInserting);

		preparedStatement.setString(1, empCode);
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getFromDate());
		preparedStatement.setString(4, user.getToDate());
		preparedStatement.setString(5, user.getLeaveType());

		int rowCount = preparedStatement.executeUpdate();
		if (rowCount > 0) {
			System.out.println("Data inserted successfully.");
		} else {
			System.out.println("Data insertion failed.");
		}
	}
	
	public static boolean updateEmployeeData(User user, String empCode) throws SQLException, ClassNotFoundException {
		boolean success = false;

		Connection  connection = DBManager.getConnection();
		String query = "UPDATE Employee_details SET username=?,designation=?, useremail=?, usermobile=?,salary=? WHERE emp_code=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, user.getName());
		statement.setString(2, user.getRole());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getMobile());
		statement.setString(5, user.getSalary());
		statement.setString(6, empCode);

		int rowsUpdated = statement.executeUpdate();
		success = (rowsUpdated > 0);
		return success;
	}

	public static void insertPermission(User user, String empCode) throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();  

		String checkPermissionQuery = "SELECT COUNT(*) FROM permission_count WHERE emp_code = ?";
		PreparedStatement checkStatement = connection.prepareStatement(checkPermissionQuery);
		checkStatement.setString(1, empCode);
		ResultSet resultSet = checkStatement.executeQuery();
		if (resultSet.next() && resultSet.getInt(1) > 0) {

			String updatePermissionQuery = "UPDATE permission_count SET date = ?, start_time = ?, end_time = ?, status = 'waiting', permission = permission  WHERE emp_code = ?";

			PreparedStatement updateStatement = connection.prepareStatement(updatePermissionQuery);
			updateStatement.setString(1, user.getDate()); 
			updateStatement.setString(2, user.getStart_time());
			updateStatement.setString(3, user.getEnd_time());
			updateStatement.setString(4, empCode);

			int rowCount = updateStatement.executeUpdate();
			if (rowCount > 0) {
				System.out.println("Permission count updated successfully.");
			} else {
				System.out.println("Permission count update failed.");
			}
		}
		else {

			String insertPermissionQuery = "INSERT INTO permission_count (emp_code, name, date, start_time, end_time, permission) VALUES (?, ?, ?, ?, ?, 0)";
			PreparedStatement insertStatement = connection.prepareStatement(insertPermissionQuery);
			insertStatement.setString(1, empCode);
			insertStatement.setString(2, user.getName());
			insertStatement.setString(3, user.getDate());
			insertStatement.setString(4, user.getStart_time());
			insertStatement.setString(5, user.getEnd_time());

			int rowCount = insertStatement.executeUpdate();
			if (rowCount > 0) {
				System.out.println("New record inserted successfully.");
			} else {
				System.out.println("Insertion failed.");
			}
		}
	}


	public static ArrayList<User> getAllPermissions() throws SQLException, ClassNotFoundException {

		ArrayList<User> userList = new ArrayList<>();

		Connection connection = DBManager.getConnection();       
		String selectPermissionQuery = "SELECT emp_code,name,date,start_time,end_time,status,permission  FROM permission_count";
		PreparedStatement preparedStatement = connection.prepareStatement(selectPermissionQuery);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setDate(resultSet.getString("date"));
			user.setStart_time(resultSet.getString("start_time"));
			user.setEnd_time(resultSet.getString("end_time"));
			user.setStatus(resultSet.getString("status"));
			user.setPermission(resultSet.getString("permission"));
			userList.add(user);
		}

		return userList;
	}
	public static ArrayList<User> getAllPermissions(String emp_code) throws SQLException, ClassNotFoundException {
		ArrayList<User> userList = new ArrayList<>();


		Connection connection = DBManager.getConnection();
		String selectPermissionQuery = "SELECT emp_code,name,date,start_time,end_time,status,permission  FROM permission_count WHERE emp_code=?";
		PreparedStatement preparedStatement = connection.prepareStatement(selectPermissionQuery);
		preparedStatement.setString(1, emp_code); 
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setDate(resultSet.getString("date"));
			user.setStart_time(resultSet.getString("start_time"));
			user.setEnd_time(resultSet.getString("end_time"));
			user.setStatus(resultSet.getString("status"));
			user.setPermission(resultSet.getString("permission"));
			userList.add(user);


		}

		return userList;
	}


	public static ArrayList<User> getEmployeeData() throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();  
		ArrayList<User> userList = new ArrayList<>();
		String selectQuery = "SELECT username, designation, useremail, userpassword, usermobile, image, salary, emp_code FROM Employee_details WHERE soft_delete ='1' ORDER BY emp_code ASC";
		PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setName(resultSet.getString("username"));
			user.setRole(resultSet.getString("designation"));
			user.setEmail(resultSet.getString("useremail"));
			user.setPassword(resultSet.getString("userpassword"));
			user.setMobile(resultSet.getString("usermobile"));
			user.setImageData(resultSet.getBytes("image"));
			user.setSalary(resultSet.getString("salary"));
			user.setEmpCode(resultSet.getString("emp_code"));
			userList.add(user);
		}
		return userList;              

	}





	public static ArrayList<User> deleteEmployee(String empCodeToDelete) throws SQLException, ClassNotFoundException {

		ArrayList<User> userList = new ArrayList<>();

		Connection  connection = DBManager.getConnection();
		String updateQuery = "UPDATE Employee_details SET soft_delete = '0' WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.setString(1, empCodeToDelete);
		int rowsAffected = preparedStatement.executeUpdate();


		if (rowsAffected > 0) {
			userList.add(new User());
		}
		return userList;
	}

	public static ArrayList<User> getEmpDataEmployee(String empCode) throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT emp_code,name,from_date,to_date,leave_type,leave_Count,status FROM Leave_report WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setFromDate(resultSet.getString("from_date"));
			user.setToDate(resultSet.getString("to_date"));
			user.setLeaveType(resultSet.getString("leave_type"));
			user.setLeaveCount(resultSet.getString("leave_Count"));
			user.setStatus(resultSet.getString("status"));
			userList.add(user);
		} 
		return userList;
	}

	public static int getTotalLeaveDays(String empCode) throws SQLException, ClassNotFoundException {

		int totalLeaveDays = 0;
		User user = new User();
		Connection  connection = DBManager.getConnection();
		String query = "SELECT SUM(DATEDIFF(to_date, from_date)) AS total_days FROM Leave_report WHERE emp_code = ?";
		PreparedStatement  preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);
		ResultSet  resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			totalLeaveDays = resultSet.getInt("total_days");
			user.setTotal_days(totalLeaveDays);
		}

		return totalLeaveDays;

	}


	public static int remainrejectLeaveDays(String empCode) throws SQLException, ClassNotFoundException {

		int totalLeaveDays = 0;
		User user = new User();
		Connection  connection = DBManager.getConnection();
		String query = "SELECT SUM(DATEDIFF(to_date, from_date)) AS total_days FROM Leave_report WHERE emp_code = ?";
		PreparedStatement  preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);
		ResultSet  resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			totalLeaveDays = resultSet.getInt("total_days");
			user.setTotal_days(totalLeaveDays);
		}

		return totalLeaveDays-1;

	}



	public static boolean insertTotalLeaveDays(String empCode, int totalLeaveDays) throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();
		String query = "UPDATE Leave_report SET leave_Count = ? WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, totalLeaveDays);
		preparedStatement.setString(2, empCode);

		int rowCount = preparedStatement.executeUpdate();

		if (rowCount > 0) {
			System.out.println("Data inserted successfully.");
			return true;
		} else {
			System.out.println("Data insertion failed.");
			return false;
		}
	}

	
	public static boolean updatePermissionStatus(String empCode, String status) throws SQLException, ClassNotFoundException {
		boolean isSuccess = false;

		Connection connection = DBManager.getConnection();
		String updateQuery = "UPDATE permission_count SET status = ? WHERE emp_code = ?";
		PreparedStatement  preparedStatement = connection.prepareStatement(updateQuery);

		preparedStatement.setString(1, status);
		preparedStatement.setString(2, empCode);

		int rowCount = preparedStatement.executeUpdate();

		if (rowCount > 0) {
			System.out.println("Data updated successfully.");
			isSuccess = true;

			if ("accepted".equalsIgnoreCase(status)) {
				String incrementQuery = "UPDATE permission_count SET permission = permission + 1 WHERE emp_code = ?";
				PreparedStatement incrementStatement = connection.prepareStatement(incrementQuery);
				incrementStatement.setString(1, empCode);
				incrementStatement.executeUpdate();
			}
		} else if("rejected".equalsIgnoreCase(status)) {
			String incrementQuery = "UPDATE permission_count SET permission = permission  WHERE emp_code = ?";
			PreparedStatement incrementStatement = connection.prepareStatement(incrementQuery);
			incrementStatement.setString(1, empCode);
			incrementStatement.executeUpdate(); 
		}

		return isSuccess;
	}



	public static boolean updateLeaveStatus(String from_date, String status) throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();
		String query="UPDATE Leave_report SET status = ? WHERE from_date = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, status); 
		preparedStatement.setString(2, from_date); 

		int rowCount = preparedStatement.executeUpdate();

		if (rowCount > 0) {
			System.out.println("Data inserted successfully.");
			return true;
		} else {
			System.out.println("Data insertion failed.");
			return false;
		}
	}


	public static ArrayList<User> getEmpLeaveCount(String empCode) throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();
		ArrayList<User> userList1 = new ArrayList<>();
		String query = "SELECT emp_code,name,from_date,to_date,leave_type,leave_Count,status FROM Leave_report WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setFromDate(resultSet.getString("from_date"));
			user.setToDate(resultSet.getString("to_date"));
			user.setLeaveType(resultSet.getString("leave_type"));
			user.setTotal_days(resultSet.getInt("leave_Count"));
			user.setStatus(resultSet.getString("status"));
			userList1.add(user);
		} 
		return userList1;
	}


	public static ArrayList<User> getEmpDataAdmin() throws SQLException, ClassNotFoundException {
		Connection connection = DBManager.getConnection();
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT emp_code,name,from_date,to_date,leave_type,leave_Count,status FROM Leave_report";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setFromDate(resultSet.getString("from_date"));
			user.setToDate(resultSet.getString("to_date"));
			user.setLeaveType(resultSet.getString("leave_type"));
			user.setLeaveCount(resultSet.getString("leave_Count"));
			user.setStatus(resultSet.getString("status"));
			userList.add(user);
		} 
		return userList;
	}



	public static ArrayList<User> getEmpPermissionCount(String empCode) throws SQLException, ClassNotFoundException {

		ArrayList<User> userList = new ArrayList<>();
		Connection  connection = DBManager.getConnection();
		String query = "SELECT emp_code,name,date,start_time,end_time,status,permission FROM permission_count WHERE emp_code = ?";
		PreparedStatement  preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);
		ResultSet  resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setDate(resultSet.getString("date"));
			user.setStart_time(resultSet.getString("start_time"));
			user.setEnd_time(resultSet.getString("end_time"));
			user.setStatus(resultSet.getString("status"));
			user.setPermission(resultSet.getString("permission"));
			userList.add(user);
		}

		return userList;
	}


	public static ArrayList<User> getEmpPermissionAdmin() throws SQLException, ClassNotFoundException {
		ArrayList<User> userList = new ArrayList<>();
		Connection connection = DBManager.getConnection();
		String query = "SELECT emp_code,name,date,start_time,end_time,status,permission FROM permission_count";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setDate(resultSet.getString("date"));
			user.setStart_time(resultSet.getString("start_time"));
			user.setEnd_time(resultSet.getString("end_time"));
			user.setStatus(resultSet.getString("status"));
			user.setPermission(resultSet.getString("permission"));
			userList.add(user);
		}

		return userList;
	}


	public static int countPermissionsPayroll(String empCode) throws ClassNotFoundException, SQLException {
		int permissionCount = 0;

		Connection connection = DBManager.getConnection();
		String query = "SELECT permission,name  FROM permission_count WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);

		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			permissionCount = resultSet.getInt("permission");

		}

		return permissionCount;
	}

	public static int countSickLeavePayroll(String empCode) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		String query = "SELECT SUM(DATEDIFF(to_date, from_date)) AS sick_leave_days FROM Leave_report WHERE emp_code = ? AND leave_type = 'sick'";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);

		ResultSet resultSet = preparedStatement.executeQuery();

		int sickLeaveDays = 0;
		if (resultSet.next()) {
			sickLeaveDays = resultSet.getInt("sick_leave_days");
		}
		return sickLeaveDays;

	}


	public static int countCasualLeavePayroll(String empCode) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		String query = "SELECT SUM(DATEDIFF(to_date, from_date)) AS casual_leave_days FROM Leave_report WHERE emp_code = ? AND leave_type = 'casual'";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);

		ResultSet resultSet = preparedStatement.executeQuery();

		int casualLeaveDays = 0;
		if (resultSet.next()) {
			casualLeaveDays = resultSet.getInt("casual_leave_days");
		}
		return casualLeaveDays;

	}

	public static int insertOrUpdateLeaveTypes(User user) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		int affectedRows = 0;

		String checkQuery = "SELECT COUNT(*) FROM employee_payscale WHERE emp_code = ?";
		PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
		checkStatement.setString(1, user.getEmpCode());
		ResultSet resultSet = checkStatement.executeQuery();
		resultSet.next();
		int count = resultSet.getInt(1);

		if (count > 0) {

			String updateQuery = "UPDATE employee_payscale SET username = ?, useremail = ?, payroll_permission = ?, sick_leaveDays = ?, casual_leaveDays = ?, working_days = ?, salary = ? WHERE emp_code = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, user.getName());
			updateStatement.setString(2, user.getEmail());
			updateStatement.setInt(3, user.getPermissionCount());
			updateStatement.setInt(4, user.getSickLeaveDays());
			updateStatement.setInt(5, user.getCasualLeaveDays());
			updateStatement.setInt(6, user.getTotalCheckinCount());
			updateStatement.setInt(7, user.getAllocate_salary());
			updateStatement.setString(8, user.getEmpCode());

			affectedRows = updateStatement.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Data updated successfully.");
			} else {
				System.out.println("Data update failed.");
			}
			updateStatement.close();
		} else {

			String insertQuery = "INSERT INTO employee_payscale (emp_code, username, useremail, payroll_permission, sick_leaveDays, casual_leaveDays, working_days, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, user.getEmpCode());
			insertStatement.setString(2, user.getName());
			insertStatement.setString(3, user.getEmail());
			insertStatement.setInt(4, user.getPermissionCount());
			insertStatement.setInt(5, user.getSickLeaveDays());
			insertStatement.setInt(6, user.getCasualLeaveDays());
			insertStatement.setInt(7, user.getTotalCheckinCount());
			insertStatement.setInt(8, user.getAllocate_salary());

			affectedRows = insertStatement.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Data inserted successfully.");
			} else {
				System.out.println("Data insertion failed.");
			}
			insertStatement.close();
		}

		checkStatement.close();
		resultSet.close();
		connection.close();

		return affectedRows;
	}



	public static int getTotalCheckinCount(String empCode) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		String query = "SELECT COUNT(*) AS total_checkin_count FROM check_ins WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);
		ResultSet resultSet = preparedStatement.executeQuery();

		int totalCheckinCount = 0;
		if (resultSet.next()) {
			totalCheckinCount = resultSet.getInt("total_checkin_count");
		}

		return totalCheckinCount;
	}


	public static int getSalary(String empCode) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();

		String query = "SELECT salary FROM Employee_details WHERE emp_code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);

		ResultSet resultSet = preparedStatement.executeQuery();

		int salary = 0;
		if (resultSet.next()) {
			salary = resultSet.getInt("salary");
		}

		return salary;
	}


	public static ArrayList<User> getCheckIns() throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT emp_code, name, checkin_time, checkout_time FROM check_ins";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setCheckInTime(resultSet.getString("checkin_time"));
			user.setCheckOutTime(resultSet.getString("checkout_time"));

			userList.add(user);
		}

		return userList;
	}
	
	public static String getEmployeeName(String empCode) throws ClassNotFoundException, SQLException {

		Connection connection = DBManager.getConnection();
		String query = "SELECT username FROM Employee_details WHERE emp_code = ?";
		PreparedStatement  preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);

		ResultSet resultSet = preparedStatement.executeQuery();

		String empName = null;
		while (resultSet.next()) {
			empName = resultSet.getString("username");
		}

		return empName;
	}


	public static String getEmployeeEmail(String empCode) throws ClassNotFoundException, SQLException {

		Connection connection = DBManager.getConnection();
		String query = "SELECT useremail FROM Employee_details WHERE emp_code = ?";
		PreparedStatement  preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, empCode);

		ResultSet resultSet = preparedStatement.executeQuery();

		String empEmail = null;
		while (resultSet.next()) {
			empEmail = resultSet.getString("useremail");
		}

		return empEmail;
	}



	public static ArrayList<User> getAllEmployeePayScales() throws ClassNotFoundException, SQLException {
		ArrayList<User> userList = new ArrayList<>();

		Connection connection = DBManager.getConnection();
		String query = "SELECT id,emp_code,username,useremail,payroll_permission,sick_leaveDays,casual_leaveDays,working_days,salary,gross_pay FROM employee_payscale";
		PreparedStatement  statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("username"));
			user.setEmail(resultSet.getString("useremail"));
			user.setPermissionCount(resultSet.getInt("payroll_permission"));
			user.setSickLeaveDays(resultSet.getInt("sick_leaveDays"));
			user.setCasualLeaveDays(resultSet.getInt("casual_leaveDays"));
			user.setTotalCheckinCount(resultSet.getInt("working_days"));
			user.setAllocate_salary(resultSet.getInt("salary"));
			user.setGrossPay(resultSet.getDouble("gross_pay"));

			userList.add(user);
		} 

		return userList;
	}





	public static void payrollPays(User user,String empCode) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();

		String updateQuery = "UPDATE employee_payscale SET gross_pay=?, Pf=?, netpay=? WHERE emp_code=?"; 

		PreparedStatement  updateStatement = connection.prepareStatement(updateQuery);
		updateStatement.setDouble(1, user.getGrossPay());
		updateStatement.setDouble(2, user.getPf());
		updateStatement.setDouble(3, user.getNetPay());
		updateStatement.setString(4, empCode);
		int insertRowCount = updateStatement.executeUpdate();
		if (insertRowCount > 0) {
			System.out.println("Data inserted successfully.");
		} else {
			System.out.println("Data insertion failed.");
		}

	}


	public static ArrayList<User> searchEmpCode(String empCode) throws ClassNotFoundException, SQLException {
		ArrayList<User> userList = new ArrayList<>();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DBManager.getConnection();

		String query = "SELECT emp_code, name, checkin_time, checkout_time FROM check_ins WHERE emp_code LIKE ?";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, "%" + empCode + "%");

		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setCheckInTime(resultSet.getString("checkin_time"));
			user.setCheckOutTime(resultSet.getString("checkout_time"));
			userList.add(user);
		}

		return userList;
	}



	public static ArrayList<User> searchPermission(String empCode) throws ClassNotFoundException, SQLException {
		ArrayList<User> userList = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DBManager.getConnection();
		String query = "SELECT emp_code, name, date,start_time,end_time,status,permission FROM permission_count WHERE emp_code LIKE ?";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, "%" + empCode + "%");
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setDate(resultSet.getString("date"));
			user.setStart_time(resultSet.getString("start_time"));
			user.setEnd_time(resultSet.getString("end_time"));
			user.setStatus(resultSet.getString("status"));
			user.setPermission(resultSet.getString("permission"));
			userList.add(user);	
		}
		return userList;
	}


	public static ArrayList<User> searchLeave(String empCode) throws ClassNotFoundException, SQLException {
		ArrayList<User> userList = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DBManager.getConnection();
		String query = "SELECT emp_code, name, from_date,to_date,leave_type,leave_Count,status FROM Leave_report WHERE emp_code LIKE ?";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, "%" + empCode + "%");
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setEmpCode(resultSet.getString("emp_code"));
			user.setName(resultSet.getString("name"));
			user.setFromDate(resultSet.getString("from_date"));
			user.setToDate(resultSet.getString("to_date"));
			user.setLeaveType(resultSet.getString("leave_type"));
			user.setLeaveCount(resultSet.getString("leave_Count"));
			user.setStatus(resultSet.getString("status"));		
			userList.add(user);
		}
		return userList;
	}

	public static void insertReport(String empCode, String name, String comments) throws ClassNotFoundException, SQLException {
	    String insertQuery = "INSERT INTO admin_report (emp_code, name, report_text) VALUES (?, ?, ?)";

	    try (Connection connection = DBManager.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
	        stmt.setString(1, empCode);
	        stmt.setString(2, name);
	        stmt.setString(3, comments);

	        int rowsInserted = stmt.executeUpdate();

	        if (rowsInserted > 0) {
	            System.out.println("New report was inserted successfully!");
	        } else {
	            System.out.println("No report was inserted.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();	    
	    }
	}

	
	
	
}



