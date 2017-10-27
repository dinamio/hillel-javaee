<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Books</title>
</head>


<body>

<div class="jumbotron" id="booksList" onclick=sendRedirectRequest("<c:url value="/welcome"/>")>
    <div class="container">
        <%@ include file="user.jsp" %>
        <div class="h1">Books</div>
    </div>
</div>
<section class="container col-md-pull-10 center-block">
    <table class="table table-striped table-condensed table-hover">
        <thead>
        <tr>
            <th><h4>ID</h4></th>
            <th><h4>Title</h4></th>
            <th><h4>Author</h4></th>
            <th><h4>Country</h4></th>
            <th><h4>Year</h4></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book">
            <tr>
                <td><c:out value="#${book.id}"/></td>
                <td><b><c:out value="${book.title}"/></b></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.country}"/></td>
                <td><c:out value="${book.year}"/></td>
                    <%--<div class="row">--%>
                <td>
                    <a class="btn btn-primary btn-sm"
                       href="<c:url value="/books?id=${book.id}" />">About book</a>
                    <a class="btn btn-danger btn-sm" href=""
                       onclick=sendDeleteRequest("<c:url value="/books?id=${book.id}"/>")>Delete</a>
                </td>
                    <%--</div>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<script>

    document.getElementById("booksList").style.cursor = "pointer";

    function sendDeleteRequest(url) {
        $.ajax({
            type: "DELETE",
            url: url
        });
    }

    function sendRedirectRequest(url) {
        window.location = url
    }

</script>

</body>
