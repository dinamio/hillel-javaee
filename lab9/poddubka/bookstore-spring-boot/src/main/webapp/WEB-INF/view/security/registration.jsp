<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user.registration"/></title>
</head>
<body>
<h1 align="center"><spring:message code="user.userRegistration"/></h1>
<form:form method="post" action="/security/addUser" align="center" modelAttribute="user">
    <p><spring:message code="user.inputName"/>:</p>
    <p><input:input path="userName"/></p>
    <p><spring:message code="user.inputPassword"/>:</p>
    <p><input:input path="userPassword"/></p>
    <sec:csrfInput/>
    <input type="submit" value="<spring:message code="user.registration"/>">
    </br>
    </br>
    <a href='http://localhost:8080/book/'><< <spring:message code="books.list"/></a>
</form:form>

</body>
</html>