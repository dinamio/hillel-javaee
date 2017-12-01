<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 11/21/17
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<link rel="stylesheet" href="/resources/styles.css">
</body>
<html>
  <head>
    <title><spring:message code="server.title"/> </title>
  </head>
  <body>
  <h1><spring:message code="welcome.message"/> ${name}</h1>
  <c:if test="${not empty servers}">

    <img>
      <c:forEach var="server" items="${servers}">
        <li>${server.name}, ${server.description}</li>
      </c:forEach>
    </ul>

  </c:if>
  <a href="/server/add"><spring:message code="server.add"/> </a>
  <hr/>
  <a href="?lang=en">en</a>
  <a href="?lang=ru">ru</a>
  <form:form action="/logout" method="post">
    <input type="submit" value="Logout">
  </form:form>
  <!--a href="/logout">Logout</a-->
  </body>
</html>
