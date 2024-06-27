<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.chainsys.springdemo.model.ObjectModel" %>

<html>
<head>
    <title>Update Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
  
    <% 
        int id = Integer.parseInt(request.getParameter("id")); 
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
    %>
    
    <form action="/update" method="post">
        <input type="hidden" name="id" value="<%= id %>">
        
        Name: <input type="text" name="name"  pattern="[A-Za-z]{2,20}" value="<%= name %>"><br>
        Designation: <input type="text" name="designation"  pattern="[A-Za-z]{2,20}" value="<%= designation %>"><br>
        Email: <input type="text" name="email" pattern="[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[com]$" value="<%= email %>"><br>
        Phone Number: <input type="text" name="phoneNumber" pattern="[0-9]{10}"  value="<%= phoneNumber %>"><br>
        
        <input type="submit" value="Update Employee">
    </form>
</body>
</html>
