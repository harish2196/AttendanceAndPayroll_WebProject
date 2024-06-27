<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.chainsys.springdemo.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Employee List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            text-align: center;
            margin: 20px;
        }
        input[type="text"] {
            padding: 10px;
            width: 200px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .delete {
            padding: 8px 12px;
            background-color: red;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .delete:hover {
            background-color: firebrick;
        }
        .update{
         padding: 8px 12px;
            background-color: green;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .update:hover{
          background-color:darkgreen;
        }
        
    </style>
</head>
<body>
    <h2>Employee List</h2>
    
    <form action="/search" method="post">  
        <input style="margin-top:0.3%;margin-bottom:1%" type="text" name="name" placeholder="Search Name">      
        <input type="submit" value="Search">
    </form>	
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Designation</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<ObjectModel> employees = (List<ObjectModel>) request.getAttribute("employees");

            if (employees != null) {
                for (ObjectModel employee : employees) {
            %>
                <tr>
                    <td><%= employee.getId() %></td>
                    <td><%= employee.getName() %></td>
                    <td><%= employee.getDesignation() %></td>
                    <td><%= employee.getEmail() %></td>
                    <td><%= employee.getPhoneNumber() %></td>
                    <td>
                        <form action="update.jsp" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= employee.getId() %>">
                            <input type="hidden" name="name" value="<%= employee.getName() %>">
                            <input type="hidden" name="designation" value="<%= employee.getDesignation() %>">
                            <input type="hidden" name="email" value="<%= employee.getEmail() %>">
                            <input type="hidden" name="phoneNumber" value="<%= employee.getPhoneNumber() %>">
                            <button type="submit" class="update" name="action" value="<%= employee.getId() %>">Update</button>
                        </form>
                        <form action="/delete" method="post" style="display:inline;">
                            <button type="submit" class="delete" name="id" value="<%= employee.getId() %>">Delete</button>
                        </form>
                    </td>	
                </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>
</body>
</html>