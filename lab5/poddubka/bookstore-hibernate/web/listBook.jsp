<%@ page import="java.util.List" %>
<%@ page import="com.hillel.model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="static com.hillel.servlets.AddServlet.BOOK_LIST" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ListBook</title>
</head>
<body>
<form method="post" action="LoginServlet"  align="right">
    <p>
        Login: <input type="text" name="username">
        Password: <input type="text" name="password">
        <input type="submit" value="Login">
    </p>
</form>
<form method="post" action="RegServlet"  align="right">
    <p><button type="submit">Registration</button></p>
    <hr />
</form>
    <h1 align="center">List of books</h1>
<table border="1" cellpadding="7" cellspacing="1">
    <tr>
        <td width="5%" align="center">#</td>
        <td width="50%" align="center">BookName</td>
        <td width="50%" align="center">AuthorName</td>
        <td width="50%" align="center">AddedName</td>
    </tr>
<%
    for(int i = 0; i < BOOK_LIST.size(); i++) { %>
    <tr>
        <form method="post" action="DeleteServlet">
            <td width="5%" align="center"><%= i + 1 %></td>
            <td width="50%" align="center"><%=BOOK_LIST.get(i).getBookName()%></td>
            <td width="50%" align="center"><%=BOOK_LIST.get(i).getAuthorName()%></td>
            <td width="50%" align="center"><%=BOOK_LIST.get(i).getAddedName()%></td>
            <%--<td width="50%" align="center"><button name="button" type="submit" value="button<%=i%>">Del</button></td>--%>
        </form>
    </tr>
   <% } %>
</table>
<br>
    <%--<a href='http://localhost:8080/'><< Back</a>--%>
</body>
</html>






