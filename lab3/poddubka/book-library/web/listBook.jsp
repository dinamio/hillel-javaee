<%@ page import="java.util.List" %>
<%@ page import="com.hillel.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="static com.hillel.MainServlet.BOOK_LIST" %>
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

    <h1 >List of books</h1>
<ol>
<%
    for(int i = 0; i < BOOK_LIST.size(); i++) { %>
        <form method="post" action="DeleteServlet">
        <li>
        <%=BOOK_LIST.get(i).getBookName() + ", " + BOOK_LIST.get(i).getAuthorName()%>
        <button name="button" type="submit" value="button<%=i%>">Del</button>
        </li>
        </form>
   <% } %>
</ol>
<br>
    <a href='http://localhost:8080/'><< Back</a>
</body>
</html>
