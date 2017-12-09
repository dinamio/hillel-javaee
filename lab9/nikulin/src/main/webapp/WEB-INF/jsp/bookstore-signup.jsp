<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/26/17
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>SignUp</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

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

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    $('#password, #confirmationPassword').on('onkeyup', function () {
        if ($('#password').val() == $('#confirmationPassword').val()) {
            $('#message').html('Matching').css('color', 'green');
        } else
            $('#message').html('Not Matching').css('color', 'red');
    });
</script>

<div class="container">


    <form action="<c:url value="signup" />" method="POST" class="form-signin">
        <form:form modelAttribute="userData">
            <sec:csrfInput/>
            <h2 class="form-signin-heading">Please sign in</h2>
            <form:input path="id" style="display: none;"/>
            <label for="username" class="sr-only">Login</label>
            <form:input path="username" type="text" id="username" name="username" class="form-control" placeholder="username"/>
            <label for="password" class="sr-only">Password</label>
            <form:input path="password"  type="password" id="password" name="password" class="form-control" placeholder="password"/>
            <input type="password" id="confirmationPassword" name="confirmationPassword" class="form-control" placeholder="repeat password"/>
            <span id='message'></span>
            <label for="name" class="sr-only">Name</label>
            <form:input path="name"  type="text" id="name" name="name" class="form-control" placeholder="name"/>
            <label for="surname" class="sr-only">Name</label>
            <form:input path="surname"  type="text" id="surname" name="surname" class="form-control" placeholder="surname"/>
            <label for="email" class="sr-only">Name</label>
            <form:input path="email"  type="text" id="email" name="email" class="form-control" placeholder="email"/>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="signUp" >Sign up</button>
        </form:form>
    </form>
</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
