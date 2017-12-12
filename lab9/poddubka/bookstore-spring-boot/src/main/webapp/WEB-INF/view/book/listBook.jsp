<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="books.title"/></title>
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
<h1 align="center"><spring:message code="books.list"/></h1>

<c:if test="${not empty books}">
    <table align="center" width="50%" border="1" cellpadding="7" cellspacing="1">
        <tr align="center">

            <td width="80%"><spring:message code="book.name"/></td>
            <td width="50%"><spring:message code="book.authors"/></td>
            <td width="50%"><spring:message code="user.add"/></td>
        </tr>
        <c:forEach var="book" items="${books}">
        <tr>

            <td width="80%">${book.getBookName()}</td>
            <td width="50%">${book.showAuthors(book.authors)}</td>
            <td width="50%">${book.user.getUserName()}</td>

            <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
                <td>
                <form:form action="/book/delete/${book.getId()}">
                    <input type="submit" value="<spring:message code="book.del"/>">
                </form:form>
                <form:form action="/book/${book.getId()}" method="get">
                    <input type="submit" value="<spring:message code="book.select"/>">
                </form:form>
                </td>
            </sec:authorize>
        </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
    <form:form action="/book/addBook" method="get" align="center">
        <input type="submit" value="<spring:message code="book.title"/>">
    </form:form>
</sec:authorize>
    </body>
</html>
