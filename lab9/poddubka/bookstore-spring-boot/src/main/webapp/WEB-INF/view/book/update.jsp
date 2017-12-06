<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="book.title"/></title>
</head>
<body>
<form method="post" <%--action=""--%>  align="right">
    <p>
        <%--<spring:message code="user.login"/>: <input type="text" name="name"/>--%>
        <%--<spring:message code="user.password"/>: <input type="text" name="password"/>--%>
        <%--<input type="submit" value="Login">--%>
        <a href="?land=en">en</a>
        <a href="?land=ru">ru</a>
    </p>
</form>
<hr/>
    <form:form method="post" action="/book/update/${book.getId()}" modelAttribute="book">
        <table  align="center" cellpadding="7" cellspacing="1">
            <tr>
                <td><label path="bookName"><spring:message code="book.name"/></label></td>
                <td><form:input path="bookName"/></td>
            </tr>
            <tr>
                <td><label path="authors"><spring:message code="book.authors"/></label></td>
                    <%--изменить "authors"--%>
                <td><form:input path="authors"/></td>
            <tr>
                <%--<td><input type="submit" value="<spring:message code="book.add"/>"></td>--%>
                <form:form action="/book/update/${book.getId()}">
                    <input type="submit" value="<spring:message code="book.edit"/>">
                </form:form>
            </tr>
        </table>
    </form:form>
</body>
</html>
