<%@ page import="static com.hillel.AddServlet.BOOK_LIST" %>
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
    <%
        for(int i = 0; i < BOOK_LIST.size(); i++) { %>
    <tr>
        <form method="post" action="DeleteServlet">
            <td width="5%" align="center"><%= i + 1 %></td>
            <td width="50%" align="center"><%=BOOK_LIST.get(i).getBookName()%></td>
            <td width="50%" align="center"><%=BOOK_LIST.get(i).getAuthorName()%></td>
            <td width="50%" align="center"><%=BOOK_LIST.get(i).getAddedName()%></td>
            <td width="50%" align="center"><button name="button" type="submit" value="button<%=i%>">Del</button></td>
        </form>
    </tr>
    <% } %>
</table>
<br>
    <a href='http://localhost:8080/addingBook.jsp'><< Add a book</a>
</body>
</html>
