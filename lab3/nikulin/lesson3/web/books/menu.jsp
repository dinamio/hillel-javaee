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
<form action="${pageContext.request.contextPath}/books/menu" method="POST">
    <h1 colspan="2"><input type="submit" name="show" value="Show books"></h1>
    <h1 colspan="2"><input type="submit" name="add" value="Add new book"></h1>
    <h1 colspan="2"><input type="submit" name="update" value="Update book"></h1>
    <h1 colspan="2"><input type="submit" name="delete" value="Delete book"></h1>
    <h2><%=request.getLocalAddr()%></h2>
</body>
</html>
