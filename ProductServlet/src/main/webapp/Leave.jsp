<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leave Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 0;
            margin: 0;
            height: 100vh;
            background-image: url("images/a18.avif");
            background-size: cover;
            
        }
        .leave-form {
            background-color: lightgray;
            padding: 20px;
            border-radius: 8px;
               min-height: 93vh;
            border: none;  
            box-shadow: 0 0 18px rgba(11, 0, 0, 0.1);
             width: 700px;
            margin-left: 55%;
        }
        .leave-form h2 {
            text-align: center;
            margin-bottom: 40px;
            color: #333;
        }
        .leave-form label {
            display: block;
            margin-bottom: 11px;
            color: #333;
        }
        .leave-form input[type="text"], .leave-form input[type="date"], .leave-form select, .leave-form textarea {
            width: 100%;
            padding:7.5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            transition: border-color 0.3s, box-shadow 0.3s;
        }
        .leave-form input[type="text"]:hover, .leave-form input[type="date"]:hover, .leave-form select:hover, .leave-form textarea:hover {
            border-color: #007BFF;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.3);
        }
        .leave-form input[type="text"]:focus, .leave-form input[type="date"]:focus, .leave-form select:focus, .leave-form textarea:focus {
            border-color: #007BFF;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.3);
            outline: none;
        }
        .leave-form button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }
        .leave-form button:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
        }
          .button {
            display: inline-block;
            margin: 20px;
            margin-left:43%;
        }
        .button a {
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 4px;
            background-color:firebrick ;
            color: white;
            font-size: 16px;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }
        .button a:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .button a:active {
            background-color: #004085;
        }
    </style>
</head>
<body>
    <form class="leave-form" action="EmployeeLeave" method="post" onsubmit="return validateDates()">
        <h2>Leave Application</h2>
             
        <label for="name">Name</label>
        <input type="text" id="name" name="name" pattern="[A-Za-z]{2,20}"
         required>
        
      <label for="fromDate">From Date</label>
<input type="date" id="fromDate" name="fromDate" required min="2024-06-14" max="2024-06-30">
        
        <label for="toDate">To Date</label>
        <input type="date" id="toDate" name="toDate" required min="2024-06-15" max="2024-06-30">
        
        <label for="leaveType">Leave Type</label>
        <select id="leaveType" name="leaveType" required>
          <option value="leave.jsp">None</option>
            <option value="sick">Medical Leave</option>
            <option value="casual">Casual Leave</option>           
        </select>
        
        <label for="reason">Reason</label>
        <textarea id="reason" name="reason" rows="4" ></textarea>
        <button type="submit">Submit</button>
                     <div class="button">
<a href="Home.jsp">Back</a>
</div>
    </form>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <script>
        function validateDates() {
            var fromDate = document.getElementById("fromDate").value;
            var toDate = document.getElementById("toDate").value;

            if (toDate <= fromDate) {
            	swal("Error", "To date must be greater than From date.", "error");
                return false; 
                
            }

            return true;
        }
    </script>
</body>
</html>
