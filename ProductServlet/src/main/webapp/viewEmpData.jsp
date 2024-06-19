<%@page import="com.chainsys.demo.User"%>
<%@page import="com.chainsys.demo.QueryManager"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leave Details</title>
    
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <style>
        body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: white; 
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
        }

    th {
        background-color: #4CAF50; 
        color: white;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2; 
    }

    tr:hover {
        background-color: #ddd;
    }

    td[colspan="5"] {
        text-align: center;
        padding: 10px;
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


.custom-dropdown-content .a1 button {
    border: none; 
    background: none;
    padding: 0; 
    font: inherit; 
    cursor: pointer; 
}

.custom-dropdown-content .a1 button:hover {
     background-color: lightgray; 
}
   .logout input[type="submit"] {
    background-color: transparent; 
    border: none;
    color: inherit;
    cursor: pointer; 
     color: lightgray;
     margin-top:10%;
   
}
    </style>
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand" href="#">I N N O W E L L</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
  
   <div>
    <p style="color: white;margin-top:6.7%;bottom:0;margin-left=-60%">Welcome, <%= session.getAttribute("name") %></p>
</div>

        <li class="nav-item">
            <a class="nav-link" href="Home.jsp">Home</a>
          </li>                   
          <li class="nav-item">
            <a class="nav-link" href="JoinUs.jsp">Join Us</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Contact.jsp">Contact</a>
          </li>
           <li class="nav-item">
          <form action="Login" class="logout" method="get">
    <a href="http://localhost:8080/ProductServlet/">     
        <input type="submit" value="Logout">
    </a>
</form>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  
    <h1 style="margin-top:2%;">Employee Leave Details</h1>
    <form action="EmployeeLeave" method="get">
        <table>
            <tr>
                <th>Employee Code</th>
                <th>Name</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Leave Type</th>
                <th>Leave Count</th>
                <th>Status</th>
            </tr>
            <% 
            ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
            if (userList != null && !userList.isEmpty()) {
                for (User user : userList) {
            %>
                <tr>
                    <td><%= user.getEmpCode() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getFromDate() %></td>
                    <td><%= user.getToDate() %></td>
                    <td><%= user.getLeaveType() %></td>
                   <td><%= user.getLeaveCount() %></td>
                      <td><%= user.getStatus() %></td> 
                </tr>
            <% 
                }
            } else { 
            %>
                <tr>
                    <td colspan="5">No user found for the provided employee code.</td>
                </tr>
            <% } %>
        </table>
        
    </form>
</body>
</html>