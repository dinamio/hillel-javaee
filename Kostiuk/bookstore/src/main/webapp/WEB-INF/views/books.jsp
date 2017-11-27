<%@ include file="header.jsp" %>

<head>
    <title>Books</title>
</head>

<body>

<div class="jumbotron" onclick=sendRedirectRequest("<c:url value="/"/>")>
    <div class="container">
        <%@ include file="loginUser.jsp" %>
        <div class="h1">Books
            <a class="btn btn-default btn-lg" href="<c:url value="/books/form"/>">Add new book</a>
        </div>
    </div>
</div>
<section class="container col-md-push-1 center-block col-md-10">
    <a class="btn btn-warning btn-sm pull-right col-md-1" onclick=toBottom()>[ To bottom ]</a>
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
                <td><c:out value="${book.author.fullName}"/></td>
                <td>
                    <c:forEach items="${book.author.countries}" var="country">
                        <ul class="list-unstyled">
                            <li><c:out value="${country.name}"/></li>
                        </ul>
                    </c:forEach>
                </td>
                <td><c:out value="${book.year}"/></td>
                <td>
                    <a class="btn btn-primary btn-sm"
                       href="<c:url value="/books/${book.id}" />">About book</a>
                    <a class="btn btn-danger btn-sm" href="" type="button"
                       onclick=sendDeleteRequest("<c:url value="/books/delete?id=${book.id}"/>")>Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-warning btn-sm pull-right col-md-1" onclick=toTop()>[ To top ]</a>
</section>
<spring:url value="/js/jquery-3.2.1.min.js" var="jquery"/>
<spring:url value="/js/bookstore.js" var="bookstore"/>
<script src="${jquery}"></script>
<script src="${bookstore}"></script>
</body>
