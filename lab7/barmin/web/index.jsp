<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/27/17
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" type="text/css" href="style/bootstrap-4/css/bootstrap.min.css">
<body>

<h2></h2>
</body>
<div class="container">
    <form action="/loginServlet" method="post">
        <div class="form-group">
            <label><b>Username</b></label>
            <input type="text" class="form-control" placeholder="Enter Username" name="uname"><br>
        </div>
        <div class="form-group">
            <label><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="psw"><br>
        </div>
        <button class="btn btn-block btn-danger btn-lg" type="submit">Login</button>

    </form>

    <form action="/view/unregistered/register.jsp" method="get">
        <button class="btn btn-block btn-danger btn-lg" type="submit">Register</button>
    </form>
</div>

</body>
</html>
