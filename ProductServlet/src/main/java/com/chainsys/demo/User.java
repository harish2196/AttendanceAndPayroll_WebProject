package com.chainsys.demo;

public class User {

	int id;
	public String name;
	String salary;
	int allocate_salary;
	public int getAllocate_salary() {
		return allocate_salary;
	}
	public void setAllocate_salary(int allocate_salary) {
		this.allocate_salary = allocate_salary;
	}
	int permissionCount;
	int sickLeaveDays;
	int casualLeaveDays;
	int totalCheckinCount;
	public int getPermissionCount() {
		return permissionCount;
	}
	public void setPermissionCount(int permissionCount) {
		this.permissionCount = permissionCount;
	}
	public int getSickLeaveDays() {
		return sickLeaveDays;
	}
	public void setSickLeaveDays(int sickLeaveDays) {
		this.sickLeaveDays = sickLeaveDays;
	}
	public int getCasualLeaveDays() {
		return casualLeaveDays;
	}
	public void setCasualLeaveDays(int casualLeaveDays) {
		this.casualLeaveDays = casualLeaveDays;
	}
	public int getTotalCheckinCount() {
		return totalCheckinCount;
	}
	public void setTotalCheckinCount(int totalCheckinCount) {
		this.totalCheckinCount = totalCheckinCount;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String email;
	public byte[] imageData;	
	public String getLeaveCount() {
		return leaveCount;
	}
	public void setLeaveCount(String leaveCount) {
		this.leaveCount = leaveCount;
	}
	public String password;
	public String mobile;
	public String empCode;
	String leaveCount;
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
	String checkInTime;
	String checkOutTime;
	double grossPay;
	double netPay;
	double pf;


	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public double getPf() {
		return pf;
	}
	public void setPf(double pf) {
		this.pf = pf;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	String toDate;
	
	String fromDate;
	String leaveType;
	String reason;
	int total_days;
	String date;
	String start_time;
	String status;
	String permission;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	String role;

	public String getStatus() {
		return status;
	}

	public User(int id, String name, String salary, String email, byte[] imageData, String password, String mobile,
			String empCode, String leaveCount, String toDate, String fromDate, String leaveType, String reason,
			int total_days, String date, String start_time, String status, String permission, String role,
			String end_time) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.email = email;
		this.imageData = imageData;
		this.password = password;
		this.mobile = mobile;
		this.empCode = empCode;
		this.leaveCount = leaveCount;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.leaveType = leaveType;
		this.reason = reason;
		this.total_days = total_days;
		this.date = date;
		this.start_time = start_time;
		this.status = status;
		this.permission = permission;
		this.role = role;
		this.end_time = end_time;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}



	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	String end_time;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public User() {
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getToDate() {
		return toDate;
	}

	public int getTotal_days() {
		return total_days;
	}
	public void setTotal_days(int total_days) {
		this.total_days = total_days;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}