<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 11/21/17
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Сервер</title>
  </head>
  <body>
  <form:form method="post" action="/server/" modelAttribute="server">
    <table>
      <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name"/></td>
        <td><form:errors path="name"/></td>
      </tr>
      <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:input path="description"/></td>
        <td><form:errors path="description"/></td>
      </tr>
      <tr>
        <td><form:label path="enabled">Enabled</form:label></td>
        <td><form:checkbox path="enabled"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="Submit"/></td>
      </tr>
    </table>

  </form:form>
  <form:form method="POST" enctype="multipart/form-data" action="/file">
    <table>
      <tr>
        <td>File to upload:</td>
        <td><input type="file" name="file" /></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Upload" /></td>
      </tr>
    </table>
  </form:form>
  </body>
</html>
