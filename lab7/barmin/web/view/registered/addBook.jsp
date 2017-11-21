<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/27/17
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
    <link rel="stylesheet" type="text/css" href="../../style/bootstrap-4/css/bootstrap.min.css">
    <script type="text/javascript" src="/javascript/jquery-3.2.1.min.js"></script>
    <script>
        var index = 1;
        function addField() {
            index++;
            $("<div>").appendTo("#addBookInput")
                .append($("<br>"))
                .append($("<label><b>Author "+index.toString()+": </b></label>)"))
                .append($("<input type=\"text\" class=\"form-control\" placeholder=\"Enter name of the author\" " + "name=\"bauthorName\" required>"))
                    .append($("<input type=\"text\" class=\"form-control\" placeholder=\"Enter surname of the author\" name=\"bauthorSurname\" required>"));
            console.log(index)
        }
    </script>
</head>
<body>

<form action="/booksServlet" method="post">
    <div class="container" >
        <div id="addBookInput">
            <label><b>Book Name</b></label>
            <input type="text" class="form-control" placeholder="Enter book name" name="bname" required><br>
            <label><b>Author1: </b></label>
            <input type="text" class="form-control" placeholder="Enter author of the book" name="bauthorName" required>
            <input type="text" class="form-control" placeholder="Enter author of the book" name="bauthorSurname" required>
        </div>
        <br><button type="button" class='btn btn-info btn-xs' onclick="addField()">Add Author</button><br><br>
        <button type="ok" class='btn btn-info btn-xs'>Add</button>
    </div>
</form>

</body>
</html>
