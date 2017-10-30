<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Adding</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Adding book</h1>--%>
<%--<form method="post" action="MainServlet">--%>
    <%--<p>Input the title of the book:</p>--%>
    <%--<p><input type="text" name="book"></p>--%>
    <%--<p>Input the author:</p>--%>
    <%--<p><input type="text" name="author"></p>--%>
    <%--<p><input type="submit" value="Add"></p>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<html>
<head>
    <title>Adding book</title>
</head>
<body>
<form method="post" action="MainServlet"  align="right">
    <p>
        Login: <input type="text" name="name">
        Password: <input type="text" name="password">
        <input type="submit" value="Login">
    </p>
    <!-- <p><input type="submit" value="Exit"></p> -->
    <p><input type="submit" value="Exit"></p>
    <hr />
</form>
<center>
    <h1>Adding book</h1>
    <form method="post" action="MainServlet">
        <p>Input the title of the book:
            <input type="text" name="book"></p>
        <p>Input the author:
            <input type="text" name="author"></p>
        <p><input type="submit" value="Add"></p>
    </form>
</center>
</body>
</html>
