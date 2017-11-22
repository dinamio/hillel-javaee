<%--
  Created by IntelliJ IDEA.
  User: pikachu00
  Date: 10/23/17
  Time: 8:11 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Books</title>
</head>

<body>
<h1>Add new</h1>
<form action = "${pageContext.request.contextPath}/books/delete" method = "POST">
    <table border = "0">

        <tr>
            <td><b>Id Book </b></td>
            <td><input type = "text" name = "BookId"
                       value = "007" size = "70"/></td>
        </tr>

        <tr>
            <td colspan = "2"><input type = "submit" name = "deleteButton" value = "Delete"/></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" name = "back" value = "Back"/></td>
        </tr>
    </table>
</form>
</body>
</html>
