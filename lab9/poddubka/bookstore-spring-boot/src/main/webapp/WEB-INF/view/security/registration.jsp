<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user.registration"/></title>
</head>
<body>
<h1 align="center"><spring:message code="user.userRegistration"/></h1>
<form:form method="post" align="center">
    <p><spring:message code="user.inputName"/>:</p>
    <p><input type="text" name="newName"></p>
    <p><spring:message code="user.inputPassword"/>:</p>
    <p><input type="text" name="newPassword"></p>
    <input type="submit" value="Registration">
    </br>
    </br>
    <a href='http://localhost:8080/book/book/'><< <spring:message code="books.list"/></a>
</form:form>
</body>
</html>