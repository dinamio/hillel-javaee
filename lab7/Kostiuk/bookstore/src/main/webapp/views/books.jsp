<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Books</title>
</head>


<body>

<div class="jumbotron" onclick=sendRedirectRequest("<c:url value="/welcome"/>")>
    <div class="container">
        <%@ include file="loginUser.jsp" %>
        <div class="h1">Books
            <a class="btn btn-default btn-lg" href="<c:url value="/views/form.jsp"/>">Add new book</a>
        </div>
    </div>
</div>
<section class="container col-md-push-1 center-block col-md-10">
    <a class="btn btn-warning btn-sm pull-right" onclick=toBottom()>To bottom</a>
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
                <td>
                    <a class="btn btn-primary btn-sm"
                       href="<c:url value="/books?id=${book.id}" />">About book</a>
                    <a class="btn btn-danger btn-sm" href=""
                       onclick=sendDeleteRequest("<c:url value="/books?id=${book.id}"/>")>Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-warning btn-sm pull-right" onclick=toTop()>To top</a>
</section>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../resources/js/bookstore.js"></script>
</body>
