<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Books</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <%--<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">--%>

    <%--<link href="/css/jumbotron-narrow.css" rel="stylesheet">--%>

    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="/books/show">Home</a></li>
                <sec:authorize access="isAuthenticated()">
                    <li role="presentation"><a href="/order">Basket</a></li>
                    <li role="presentation"><a href="/profile">Profile</a></li>
                    <li role="presentation"><a href="/logout">Logout</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <li role="presentation"><a href="/admin/menu">Admin menu</a></li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li role="presentation"><a href="/login">Login</a></li>
                </sec:authorize>
            </ul>
        </nav>
        <h3 class="text-muted">Books</h3>
    </div>

    <sec:csrfInput/>

    <c:forEach items="${users}" var="order">
        <div class="jumbotron">
            <h1>
                Name: ${order.username}
            </h1>
            <p>Role:
                ${order.role}
            </p>
            <p><a class="btn btn-lg btn-success" role="button" href="/admin/${order.id}" type="submit">Edit</a></p>
            <p><a class="btn btn-lg btn-success" role="button" href="/admin/delete/${order.id}" type="submit">Delete</a></p>
        </div>
    </c:forEach>


</div>

<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
