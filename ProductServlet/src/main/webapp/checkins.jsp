<%@page import="com.chainsys.demo.User"%>
<%@page import="com.chainsys.demo.QueryManager"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Check-Ins</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: black;
	background-color: #f2f2f2;
}

.container {
	width: 80%;
	margin: 20px auto;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

h2 {
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}

input[type="submit"] {
	padding: 4px 10px;
	border: none;
	border-radius: 4px;
	background-color: #28a745;
	color: white;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #218838;
}
</style>
</head>
<body>

	<div class="container">
		<form action="PayrollDeduction" method="get">
			<input type="text" name="empcode" placeholder="Search EmpCode">
			<input type="submit" value="Search">
					<a href="AdminDashboard.jsp">
			<input type="submit" value="Back" style="margin-left:0.8%;background-color: firebrick;">
		</a>
		</form>

		<h2>Employee Check-In'S & Check-Out'S</h2>

		<table>
			<thead>
				<tr>
					<th>Employee Code</th>
					<th>Name</th>
					<th>Check-In Time</th>
					<th>Check-Out Time</th>
				</tr>
			</thead>
			<tbody>
				<% 
                ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
                for (User user : userList) { 
            %>
				<tr>
					<td><%= user.getEmpCode() %></td>
					<td><%= user.getName() %></td>
					<td><%= user.getCheckInTime() %></td>
					<td><%= user.getCheckOutTime() %></td>
				</tr>
				<% } %>

			</tbody>

		</table>
	</div>

</body>
</html>
