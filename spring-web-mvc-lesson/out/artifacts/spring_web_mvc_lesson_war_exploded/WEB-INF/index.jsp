<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 11/21/17
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Сервера</title>
  </head>
  <body>
  <h1>Hello ${name}</h1>
  <c:if test="${not empty servers}">

    <ul>
      <c:forEach var="server" items="${servers}">
        <li>${server.name}, ${server.description}</li>
      </c:forEach>
    </ul>

  </c:if>
  <a href="/server/add">Добавить сервер</a>
  </body>
</html>
