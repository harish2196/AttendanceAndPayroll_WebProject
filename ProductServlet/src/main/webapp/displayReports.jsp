<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.chainsys.demo.User" %>
<%@ page import="com.chainsys.demo.QueryManager" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa; 
        }
        h1 {
            font-family: 'Arial', sans-serif;
            font-weight: bold;
            text-align: center;
            margin-bottom:2%;
           color:darkmagenta;
        }
        .table-hover tbody tr:hover {
            background-color: lightgrey; 
        }
        .hover-effect:hover {
            color: #ff6347; 
        }
        .btn-back {
            background-color: firebrick;
            border-color: firebrick;
        }
        .btn-back:hover {
            background-color: darkred;
            border-color: darkred;
        }
         .btn-search {
            background-color: green;
            border-color: green;
            color: white;
            padding: 3.3px 7px;
            margin-left:0.2%;
            margin-top:-0.6%;
        }
        .btn-search:hover {
            background-color: darkgreen;
            border-color: darkgreen;
        }
    </style>
</head>
<body>
    <div class="container my-5">
        <h1>Report List</h1>
         <form action="SearchByCode" method="get">  
         <input style="margin-left:38.5%;margin-bottom:2%" type="text" name="empcode" placeholder="Search EmpCode">      
        <input type="submit" value="Search" class="btn btn-search">
    </form>
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Employee Code</th>
                    <th>Name</th>
                    <th>Report Text</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
                    if (userList != null) {
                        for (User user : userList) {
                %>
                <tr class="hover-effect">
                    <td><%= user.getEmpCode() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getText() %></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    
        <button onclick="window.location.href='AdminDashboard.jsp'" type="button" class="btn btn-primary btn-back" style="margin-top: 20px; margin-left: 45%;">Back</button>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
