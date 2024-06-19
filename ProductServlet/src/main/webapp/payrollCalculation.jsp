<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.chainsys.demo.User"%>
<%@page import="com.chainsys.demo.QueryManager"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employee Payroll Information</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:hover {
            background-color: lightgrey;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">Employee Payroll Information</h2> 
    <table>
        <tr>
            <th>Employee Code</th>
            <th>Employee Name</th>
             <th>Employee Email</th>
            <th>Permission Count</th>
            <th>Sick Leave Days</th>
            <th>Casual Leave Days</th>
            <th>Total Check-in Count</th>
            <th>Allocate salary</th>
         
        </tr>
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
        %>
                <tr>
                    <td><%= user.getEmpCode() %></td>
                      <td><%= user.getName()%></td>
                        <td><%= user.getEmail() %></td>
                    <td><%= user.getPermissionCount() %></td>
                    <td><%= user.getSickLeaveDays() %></td>
                    <td><%= user.getCasualLeaveDays() %></td>
                    <td><%= user.getTotalCheckinCount() %></td>
                    <td><%= user.getAllocate_salary() %></td>
            
                </tr>
        <%
            }
        %>
    </table>
   
</body>
</html>
