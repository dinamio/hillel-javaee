<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="books.title"/></title>
</head>
<body>
<c:url value="/login" var="loginUrl"/>
<form align="right" action="${loginUrl}" method="post">
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
        <spring:message code="user.login"/>: <input type="text" id="username" name="username"/>
        <spring:message code="user.password"/>: <input type="text" id="password" name="password"/>
        <input type="submit" <%--class="btn"--%> value=<spring:message code="user.login"/>>
    </p>
    <p>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}">
    </p>
    <p>
        <a href="?land=en">en</a>
        <a href="?land=ru">ru</a>
    </p>
</form>
<form:form action="/logout" method="post" align="right">
    <input type="submit" value="<spring:message code="user.logout"/>">
</form:form>
<%--<form method="post" &lt;%&ndash;action=""&ndash;%&gt;  align="right">--%>
<%--<p>--%>
<%--<spring:message code="user.login"/>: <input type="text" name="name"/>--%>
<%--<spring:message code="user.password"/>: <input type="text" name="password"/>--%>
<%--<input type="submit" value=<spring:message code="user.login"/>>--%>
<%--</p>--%>
<%--<p>--%>
<%--<a href="?land=en">en</a>--%>
<%--<a href="?land=ru">ru</a>--%>
<%--</p>--%>
<%--</form>--%>
<hr/>
<h1 align="center"><spring:message code="book.book"/> #${books.getId()}</h1>

<c:if test="${not empty books}">
    <table align="center" width="50%" border="1" cellpadding="7" cellspacing="1">
        <tr align="center">
                <%--<td width="5%">ID</td>--%>
            <td width="80%"><spring:message code="book.name"/></td>
            <td width="50%"><spring:message code="book.authors"/></td>
            <td width="50%"><spring:message code="user.add"/></td>
        </tr>
        <tr>
                <%--<td width="50%">${book.getId()}</td>--%>
            <td width="80%">${books.getBookName()}</td>
            <td width="50%">${books.showAuthors(books.authors)}</td>
            <td width="50%">${books.user.getUserName()}</td>
        </tr>
    </table>
</c:if>
<br>
<form:form action="/book/delete/${books.getId()}" method="post" align="center">
    <input type="submit" value="<spring:message code="book.del"/>">
</form:form>
<%--references???--%>
<a href="/book/addBook"><< <spring:message code="book.title"/></a>
</body>
</html>
