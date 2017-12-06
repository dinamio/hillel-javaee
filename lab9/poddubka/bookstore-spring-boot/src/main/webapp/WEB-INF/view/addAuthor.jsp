<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add author</title>
</head>
<body>
<form method="post" <%--action=""--%>  align="right">
    <p>
        <spring:message code="user.login"/>: <input type="text" name="name"/>
        <spring:message code="user.password"/>: <input type="text" name="password"/>
        <input type="submit" value="Login">
        <a href="?land=en">en</a>
        <a href="?land=ru">ru</a>
    </p>
</form>
<hr/>
    <form:form modelAttribute="author"><%--method="post" action="/book/"--%>
        <table  align="center" cellpadding="7" cellspacing="1">
            <tr>
                <td><label path="title">Author name</label></td>
                <td><form:input path="authorName"/></td>
            </tr>
            <tr>
                <td><label path="title">Book title</label></td>
                <td><form:input path="bookName"/></td>
            <tr>
                <td><input type="submit">Add author</td>
            </tr>
        </table>
    </form:form>

</body>
</html>
