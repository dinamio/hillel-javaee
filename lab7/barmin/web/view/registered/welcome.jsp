<%@ page import="static servlets.LoginServlet.currentSessions" %><%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/27/17
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="../../style/bootstrap-4/css/bootstrap.min.css">
</head>
<body>
<div class="container" align="center">
    <div class="col-md-4 text-center">
    <h2>Welcome, <%=currentSessions.get(request.getSession().getId())%></h2><br>
    <a class="nav-tabs-dropdown btn btn-block btn-primary" href="/view/registered/booksList.jsp">List of books</a><br>
    <a class="nav-tabs-dropdown btn btn-block btn-primary" href="/view/registered/addBook.jsp">Add book</a>
    </div>
</div>
</body>
</html>
