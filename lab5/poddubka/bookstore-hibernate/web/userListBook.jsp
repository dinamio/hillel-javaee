<%@ page import="com.hillel.model.Book" %>
<%@ page import="com.hillel.servlets.AddServlet" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListBook</title>
</head>
<body>
<form  align="right">
<%--<form method="post" action=""  align="right">--%>
    <p>
        Login: <input type="text" name="name">
        Password: <input type="text" name="password">
        <input type="submit" value="Login">
    </p>
</form>
<form method="get" action="LogoutServlet"  align="right">
    <p><button type="submit">Exit</button></p>
    <hr/>
</form>
<h1 align="center">List of books</h1>
<table border="1" cellpadding="7" cellspacing="1">
    <tr>
        <td width="5%" align="center">#</td>
        <td width="50%" align="center">BookName</td>
        <td width="50%" align="center">AuthorName</td>
        <td width="50%" align="center">AddedName</td>
        <td width="50%" align="center">Operation</td>
    </tr>
    <% List<Book> book = (List<Book>) request.getAttribute("listBook");
        for(int i = 0; i < book.size(); i++) { // ???? %>
    <tr>
        <form method="post" action="DeleteServlet">
            <td width="5%" align="center"><%=book.get(i).getId()%></td>
            <td width="50%" align="center"><%=book.get(i).getBookName()%></td>
            <td width="50%" align="center"><%=book.get(i).getAuthors()%></td>
            <td width="50%" align="center"><%=book.get(i).getUser()%></td>
            <td width="50%" align="center"><button name="button" type="submit" value="button<%=book.get(i).getId()%>">Del</button></td>
        </form>
    </tr>
    <% } %>
</table>
<br>
    <a href='http://localhost:8080/addingBook.jsp'><< Add a book</a>
    <br>
    <a href='http://localhost:8080/listBook.jsp'><< list of books</a>
</body>
</html>
