<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error registration</title>
</head>
<body>
    <h1 align="center"> A user named '${user.userName}' already exists!</h1>
    <form:form action="/security/registration" method="get" align="center">
        <input type="submit" value="<spring:message code="user.registration"/>">
    </form:form>
</body>
</html>