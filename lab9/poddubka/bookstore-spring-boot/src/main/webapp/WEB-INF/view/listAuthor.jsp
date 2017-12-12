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
        <spring:message code="user.login"/>: <input type="text" name="name"/>
        <spring:message code="user.password"/>: <input type="text" name="password"/>
        <input type="submit" value="Login">
        <a href="?land=en">en</a>
        <a href="?land=ru">ru</a>
    </p>
</form>
<hr/>
<h1>List of authors</h1>

<c:if test="${not empty author}">
    <ul>
        <c:forEach var="author" items="${author}">
            <li>${author.authorName}, ${listToString(author.books)}</li>
        </c:forEach>
    </ul>
</c:if>

<a href="/author/addAuthor"><< Add author</a>
</body>
</html>
