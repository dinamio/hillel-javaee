<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding book</title>
</head>
<body>
<form method="post" action=""  align="right">
    <p>
        Login: <input type="text" name="name">
        Password: <input type="text" name="password">
        <input type="submit" value="Login">
    </p>
</form>
<form method="get" action="LogoutServlet"  align="right">
    <p><button type="submit">Exit</button></p>
    <hr />
</form>
    <h1 align="center">Adding book</h1>
    <form method="post" action="AddServlet" align="center" >
        <p>Input the title of the book:
            <input type="text" name="book"></p>
        <p>Input the author:
            <input type="text" name="author" placeholder="Author1,Author2,..."></p>
        <p><input type="submit" value="Add"></p>
    </form>
</body>
</html>
