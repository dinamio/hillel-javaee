<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/27/17
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add book</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/bootstrap-4/css/bootstrap.min.css">
    <script type="text/javascript" src="/resources/javascript/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/resources/javascript/addBook.js"></script>
</head>
<body>

<form action="/booksController" method="post">
    <div class="container" >
        <div id="addBookInput">
            <label><b>Book Name</b></label>
            <input type="text" class="form-control" placeholder="Enter book name" name="bname" required><br>
            <label><b>Author1: </b></label>
            <input type="text" class="form-control" placeholder="Enter author of the book" name="bauthorName" required>
            <input type="text" class="form-control" placeholder="Enter author of the book" name="bauthorSurname" required>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
        <div>
            <br><button type="button" class='btn btn-info btn-xs' onclick="addField()">Add Author</button><br>
            <button type="ok" class='btn btn-info btn-xs'>Add</button>
        </div>
    </div>
</form>

</body>
</html>
