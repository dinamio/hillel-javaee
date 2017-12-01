<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<head>
    <title>${bookstore}</title>
</head>

<body>

<div class="jumbotron">
    <%@ include file="language_login.jsp" %>
    <div class="container">
        <h1>${welcome}</h1>
        <p>
            <a class="btn btn-primary btn-lg" href="<c:url value="/books"/>">${show_books}</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/books/form"/>">${add_new_book}</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/users"/>">${show_users}</a>
        </p>
    </div>
</div>

</body>