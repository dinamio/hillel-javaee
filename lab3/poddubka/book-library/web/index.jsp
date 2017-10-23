<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding</title>
</head>
<body>
<h1>Adding book</h1>
<form method="post" action="MainServlet">
    <p>Input the books id:</p>
    <p><input type="text" name="bookId"></p>
    <p>Input the title of the book:</p>
    <p><input type="text" name="book"></p>
    <p>Input the author:</p>
    <p><input type="text" name="author"></p>
    <p><input type="submit" value="Add"></p>
</form>
</body>
</html>
