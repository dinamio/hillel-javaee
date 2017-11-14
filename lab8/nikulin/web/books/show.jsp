<%@ page import="model.Book" %>
<%@ page import="model.jdbc.JDBCBookDataAccessObject" %><%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/books/show" method = "POST">
<h1><%
    String s = "<table>";
    for (Book book:
            JDBCBookDataAccessObject.getJdbcBookDataAccessObject().getAllBooks()) {
//        System.out.println(book);
        s +="<tr><td>" + book + "</td></tr>";
    }
    s += "</table>";
    %>
</h1>
    <h2 colspan = "2"><input type = "submit" name = "back" value = "Back"/></h2>
<%=s%>
</body>
</html>
