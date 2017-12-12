<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="book.title"/></title>
</head>
<body>
<c:url value="/login" var="loginUrl"/>
<form:form align="right" action="${loginUrl}" method="post">
    <p>
        <spring:message code="user.login"/>: <label for="username"></label>
        <input type="text" id="username" name="username"/>
        <spring:message code="user.password"/>: <label for="password"></label>
        <input type="text" id="password" name="password"/>
        <br>
        <br>
        <input type="submit" class="input" value=<spring:message code="user.login"/>>
    </p>
    <p>
        <sec:csrfInput/>
    </p>
</form:form >
<p>
    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <form:form action="/security/registration" method="get" align="right">
            <input type="submit" value="<spring:message code="user.registration"/>">
        </form:form>
    </sec:authorize>
    <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
        <form:form action="/logout" method="post" align="right">
            <spring:message code="user.user"/>: <sec:authentication property="principal.username"/>
            <br>
            <input type="submit" value="<spring:message code="user.logout"/>">
        </form:form>
    </sec:authorize>
</p>
<p align="right">
    <a href="?land=en">en</a>
    <a href="?land=ru">ru</a>
</p>
<hr/>
    <form:form accept-charset = "UTF-8" method="post" action="/book/update/${book.getId()}" modelAttribute="book">
        <table  align="center" cellpadding="7" cellspacing="1">
            <tr>
                <td><label path="bookName"><spring:message code="book.name"/></label></td>
                <td><form:input path="bookName"/></td>
            </tr>
            <tr>
                <td><label path="authors"><spring:message code="book.authors"/></label></td>
                <td><form:input path="authors"/></td>
        </table>
        <p align="center">
            <form:form action="/book/update/${book.getId()}" method="get" modelAttribute="bookDTO">
                <input type="submit" value="<spring:message code="book.edit"/>">
                <sec:csrfInput/>
            </form:form>
        </p>
    </form:form>
</body>
</html>
