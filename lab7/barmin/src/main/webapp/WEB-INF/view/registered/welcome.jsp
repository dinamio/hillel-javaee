<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/27/17
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/bootstrap-4/css/bootstrap.min.css">
</head>
<body>
<div class="container" align="center">
    <div class="col-md-4 text-center">
        <h2>Welcome, <sec:authentication property="principal"/></h2><br>
        <a class="nav-tabs-dropdown btn btn-block btn-primary" href="/booksList">List of books</a><br>
        <a class="nav-tabs-dropdown btn btn-block btn-primary" href="/addBook">Add book</a>
    </div>
</div>
</body>
</html>
