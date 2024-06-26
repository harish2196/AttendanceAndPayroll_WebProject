<%@page import="com.chainsys.demo.User"%>
<%@page import="com.chainsys.demo.QueryManager"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Permission Details</title>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
      body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
              margin-left:44% ;
            cursor: pointer;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
           .custom-button {
        border: none;
        background-color: #4CAF50; 
        color: white;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 4px;
        transition: background-color 0.3s ease;
    }

 
    .custom-button:hover {
        background-color: #45a049; 
    }

    
    form {
        display: inline;
        margin: 0;
        padding: 0;
    }
    
    
    
    .custom-nav {
            background-color: lightgray;
            padding: 10px 5px;
            width: 39%;
          margin-left: 27%;
          margin-top: 3%;
            border-radius: 20px;
            overflow: hidden;
            border: none;
            box-shadow: 1px 7px 10px rgba(0, 0, 0, 0.2);
        }

        .custom-nav a {
            float: left;
            display: block;
            color: #333;
           
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .custom-nav a:hover {
            background-color: #ddd;
            color: black;
        }

        .custom-dropdown {
            float: left;
            overflow: hidden;
        }

        .custom-dropdown .custom-dropbtn {
            font-size: 17px;
            border: none;
            outline: none;
            color: #333;
            padding: 14px 16px;
            background-color: inherit;
            margin: 0;
        }

        .custom-dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
            z-index: 1;
        }

        .custom-dropdown-content a {
            float: none;
            color: #333;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .custom-dropdown-content a:hover {
            background-color: #ddd;
            color: black;
        }

        .custom-dropdown:hover .custom-dropdown-content {
            display: block;
        }

        .custom-dropdown:after {
            content: "";
            clear: both;
            display: table;
        } 
    
    </style>
</head>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand" href="#">Payroll</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
  
   <div>
    <p style="color: white;margin-top:8%;bottom:0;">Welcome, <%= session.getAttribute("name") %></p>
</div>

        <li class="nav-item">
            <a class="nav-link" href="AdminDashboard.jsp">Home</a>
          </li>                   
          <li class="nav-item">
            <a class="nav-link" href="JoinUs.jsp">Join Us</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#contact">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
<body>
  <form action="UpdateAndDelete" method="get">
    <h1>Employee Permission Details</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Status</th>
            <th>Permission Count</th>
         
        </tr>
        <%
        ArrayList<User> permissionCountList = (ArrayList<User>) request.getAttribute("permissionCountList");
        
            for (User user : permissionCountList) {
        %>
                <tr>
                    <td><%= user.getName() %></td>
                    <td><%= user.getDate() %></td>
                    <td><%= user.getStart_time() %></td>
                    <td><%= user.getEnd_time() %></td>
                    <td><%= user.getStatus() %></td>
                    <td><%= user.getPermission() %></td>
                  
                </tr>
        <%
            }
         
        %>
           
            </form>
    </table>
<script>
    function acceptRequest(acceptButton) {
        var rejectButton = acceptButton.nextElementSibling;
        rejectButton.style.display = 'none';
        acceptButton.disabled = true;
    }

    function rejectRequest(rejectButton) {
        var acceptButton = rejectButton.previousElementSibling;
        acceptButton.style.display = 'none';
        rejectButton.disabled = true;
    }
</script>

</body>
</html>
