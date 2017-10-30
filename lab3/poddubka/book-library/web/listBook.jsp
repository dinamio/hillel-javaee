<%@ page import="com.hillel.BookList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hillel.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="static com.hillel.MainServlet.listBook" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<html>
<head>
    <title>ListBook</title>
</head>
<body>
<form method="post" action="MainServlet"  align="right">
    <p>
        Login: <input type="text" name="name">
        Password: <input type="text" name="password">
        <input type="submit" value="Login">
    </p>
    <p><input type="submit" value="Registration"></p>
    <hr />
</form>

    <h1>List of books</h1>

<%
    pageContext.setAttribute("listBook", listBook);

    out.println("<ol>");
    for(int i = 0; i < listBook.size(); i++) {
        out.println("<form method=\"post\" action=\"DeleteServlet\">");
        out.println("<li>");
        out.println(listBook.get(i) + " ");
        out.println("<button name='button' type='submit' value='" + i + "'>Del</button>");
        out.println("</li>");
        out.println("</form>");
    }
    out.println("</ol>");
%>

<br>
    <a href='http://localhost:8080/'><< Back</a>
</body>
</html>
