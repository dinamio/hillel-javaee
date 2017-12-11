<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <link href="/jumbotron-narrow.css" rel="stylesheet">

    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<form action="<c:url value="{id}" />" method="POST">

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
                        <li role="presentation"><a href="/books/menu">Admin menu</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <li role="presentation"><a href="/login">Login</a></li>
                    </sec:authorize>
                </ul>
            </nav>
            <h3 class="text-muted">Books</h3>
        </div>



        <div class="jumbotron">

            <form:form modelAttribute="user">
                <sec:csrfInput/>
                <fieldset>
                    <form:input path="id" cssClass="form-control" cssStyle="display:none" readonly="true"/>
                    <br/>
                    Login:
                    <form:input path="username" cssClass="form-control" readonly="true"/>
                    <br />

                    <form:input path="password" cssClass="form-control" cssStyle="display:none" readonly="true"/>
                    <br />
                    Name:
                    <form:input path="name" cssClass="form-control" readonly="true"/>
                    <br/>
                    Surname:
                    <form:input path="surname" cssClass="form-control" readonly="true"/>
                    <br/>
                    Email:
                    <form:input path="email" cssClass="form-control" readonly="true"/>
                    <br/>
                    Role:
                    <form:input path="role" cssClass="form-control" readonly="true"/>

                </fieldset>
                <p><a class="btn btn-lg btn-success" role="button" type="submit" href="/order/story">Orders story</a></p>
            </form:form>
        </div>


    </div>


</form>
<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
