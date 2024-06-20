<%@page import="com.chainsys.demo.User"%>
<%@page import="com.chainsys.demo.QueryManager"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Payroll Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
       
        body {
            padding: 20px;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        table th {
            background-color: #f2f2f2;
        }
        table tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center" style="color:dodgerblue">Employee Payroll Information</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>Employee Code</th>
                        <th>Employee Name</th>
                        <th>Employee Email</th>
                        <th>Permission Count</th>
                        <th>Sick Leave Days</th>
                        <th>Casual Leave Days</th>
                        <th>Total Check-in Count</th>
                        <th>Allocate Salary</th>
                    </tr>
                </thead>
                <tbody>
                    <% User user = (User) session.getAttribute("user");
                       if (user != null) { %>
                        <tr>
                            <td><%= user.getEmpCode() %></td>
                            <td><%= user.getName() %></td>
                            <td><%= user.getEmail() %></td>
                            <td><%= user.getPermissionCount() %></td>
                            <td><%= user.getSickLeaveDays() %></td>
                            <td><%= user.getCasualLeaveDays() %></td>
                            <td><%= user.getTotalCheckinCount() %></td>
                            <td><%= user.getAllocate_salary() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
