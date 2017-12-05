<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/27/17
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" type="text/css" href="/resources/style/bootstrap-4/css/bootstrap.min.css">
<body>

<h2></h2>
</body>
<div class="container">

    <form name="f" th:action="@{/login}" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <div class="form-group">
                <label for="username"><b>Username</b></label>
                <input type="text" class="form-control" placeholder="Enter Username"  id="username" name="username"/>
            </div>
            <div class="form-group">
                <label></label>
                <label for="password"><b>Password</b></label>
                <input type="password" class="form-control" placeholder="Enter Password" id="password" name="password"/>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-block btn-danger btn-lg">Log in</button>
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </fieldset>
    </form>

    <form action="/unregistered/register" method="get">
        <button class="btn btn-block btn-danger btn-lg" type="submit">Register</button>
    </form>

</div>

</body>
</html>
