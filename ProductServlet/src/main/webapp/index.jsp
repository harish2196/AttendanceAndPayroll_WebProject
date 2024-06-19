<%
if(session.getAttribute("name") == null){
    response.sendRedirect("login.jsp");	
}
%> 

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Simple Form</title>
</head>
<style>
.button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff; 
  text-decoration: none; 
  border: none; 
  border-radius: 5px; 
  cursor: pointer; 
}
.button:hover{
  background-color: #0056b3; 
}
</style>
<body>
   
    <form action="Login" method="get">  
        <a href="http://localhost:8080/ProductServlet/"> <input type="submit" value="Logout"></a>
    </form>
 </body>
</html>
