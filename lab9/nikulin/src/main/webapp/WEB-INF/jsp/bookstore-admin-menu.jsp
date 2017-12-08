<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <sec:csrfInput/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>Books</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/jumbotron-narrow.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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

    <form>
        <div class="jumbotron">
            <sec:csrfInput/>
            <p><a class="btn btn-lg btn-success" role="button" type="submit" href="/books/show">Catalogue</a></p>
            <p><a class="btn btn-lg btn-success" role="button" type="submit" href="/books/add">Add new</a></p>
            <p><a class="btn btn-lg btn-success" role="button" type="submit" href="/admin">Users</a></p>
            <p><a class="btn btn-lg btn-success" role="button" type="submit" href="/admin/orders">All Orders Story</a></p>

        </div>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
