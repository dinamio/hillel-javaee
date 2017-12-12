<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user.registration"/></title>
</head>
<body>
<c:url value="/login" var="loginUrl"/>
<form align="center" action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username"><spring:message code="user.login"/></label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password"><spring:message code="user.password"/></label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}">
    <button type="submit" class="btn"><spring:message code="user.log_in"/></button>
    <p>
        <a href="?land=en">en</a>
        <a href="?land=ru">ru</a>
    </p>
</form>
</body>
</html>