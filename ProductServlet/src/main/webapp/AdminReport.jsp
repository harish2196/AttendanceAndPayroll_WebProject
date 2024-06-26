<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Report</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color:#17a2b8;
;
        }
        .survey-form {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }
        .survey-form .form-group label {
            font-weight: bold;
        }
        .survey-form .form-group input,
        .survey-form .form-group textarea {
            border-radius: 5px;
        }
        .survey-form .form-group textarea {
            resize: none;
        }
        .survey-form button {
            background-color: #6f42c1;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 0.5rem 1rem;
            font-size: 1rem;
        }
        .survey-form button:hover {
            background-color: #5a32a0;
        }
        .btn{
            display: flex;
        }
        
    </style>
</head>
<body>
 
    <div class="survey-form">
        <h3 style="margin-left: 32%;color: darkmagenta;font-weight: bolder;">Report Form</h3>
            <form action="AdminReport" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" name="name" id="name" placeholder="Name...">
            </div>
            <div class="form-group">
                <label for="comments">Your comments</label>
                <textarea class="form-control" name="comments" id="comments" rows="4" placeholder="Write your comments here..."></textarea>
            </div>
            <div class="btn">
        
                <div class="btn1">
            <button style="margin-left: 159%;" type="submit" class="btn btn-primary">Submit</button>
    
        </div>
       <div class="btn2"> 
            <button style="background-color: firebrick; margin-left: 215%;" onclick="window.location.href='Home.jsp'" type="button" class="btn btn-primary">Back</button>
        </div>
        </div>
             </div>
            </form>
   
       
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>