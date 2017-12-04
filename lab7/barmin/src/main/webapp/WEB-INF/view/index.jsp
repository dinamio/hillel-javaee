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
                <%--<input type="text"   name="uname"><br>--%>
                <input type="text" class="form-control" placeholder="Enter Username"  id="username" name="username"/>
            </div>
            <div class="form-group">
                <label></label>
                <label for="password"><b>Password</b></label>
                <%--<input type="password"  name="psw"><br>--%>
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
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>

</div>

<%--<body>--%>
<%--<div tiles:fragment="content">--%>
<%--<form name="f" th:action="@{/login}" method="post">--%>
<%--<fieldset>--%>
<%--<legend>Please Login</legend>--%>
<%--<div th:if="${param.error}" class="alert alert-error">--%>
<%--Invalid username and password.--%>
<%--</div>--%>
<%--<div th:if="${param.logout}" class="alert alert-success">--%>
<%--You have been logged out.--%>
<%--</div>--%>
<%--<label for="username">Username</label>--%>
<%--<input type="text" id="username" name="username"/>--%>
<%--<label for="password">Password</label>--%>
<%--<input type="password" id="password" name="password"/>--%>
<%--<div class="form-actions">--%>
<%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>.--%>
<%--<button type="submit" class="btn">Log in</button>--%>
<%--</div>--%>
<%--</fieldset>--%>
<%--</form>--%>
<%--</div>--%>
<%--</body>--%>


</body>
</html>
