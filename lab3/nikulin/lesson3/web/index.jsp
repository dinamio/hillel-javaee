<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/20/17
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Books</title>
</head>

<body>
<h1>Books</h1>
<form action = "${pageContext.request.contextPath}/books" method = "POST">
  <table border = "0">

    <tr>
      <td><b>Id Book For Remove or Update</b></td>
      <td><input type = "text" name = "BookId"
                 value = "007" size = "70"/></td>
    </tr>

    <tr>
      <td><b>BookName</b></td>
      <td><input type = "text" name = "BookName"
                 value = "Legends of the Midgard" size = "70"/></td>
    </tr>

    <tr>
      <td><b>BookAuthor</b></td>
      <td><input type = "text" name = "BookAuthor"
                 value = "John Snow" size = "65"/></td>
    </tr>

    <tr>
      <td>Puplisher</td>
      <td>
        <select name = "BookPublisher">
          <option value = "ROSMAN">Rosman</option>
          <option value = "RANOK">Ranok</option>
          <option value = "PITER">Piter</option>
          <option value = "Midgard">Midgard</option>
          <option value = "MIDDLEEARTH">Middle-earth</option>
        </select>
      </td>
    </tr>

    <tr>
      <td colspan = "2"><input type = "submit" name = "submitButton" value = "Add"/></td>
      <td colspan = "2"><input type = "submit" name = "deleteButton" value = "Delete"/></td>
      <td colspan = "2"><input type = "submit" name = "findButton" value = "Find All"/></td>
      <td colspan = "2"><input type = "submit" name = "updateButton" value = "Update"/></td>
    </tr>
  </table>
</form>
</body>
</html>