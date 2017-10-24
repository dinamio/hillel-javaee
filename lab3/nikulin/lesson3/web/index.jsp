<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/books" method="POST">
    <h1 colspan="2"><input type="submit" name="signIn" value="Sign In"></h1>
    <h1 colspan="2"><input type="submit" name="signUp" value="Sign Up"></h1>
    <table>
        <tr>
            <td><b>login </b></td>
            <td><input type="text" name="login"
                       value="admin" size="70"/></td>
        </tr>
        <tr>
            <td><b>password </b></td>
            <td><input type = "text" name = "password"
                       value = "admin" size = "70"/></td>
        </tr>
    </table>
</body>
</html>
