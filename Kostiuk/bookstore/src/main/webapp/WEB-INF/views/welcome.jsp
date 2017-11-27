<%@ include file="header.jsp" %>

<head>
    <title>Bookstore</title>
</head>

<body>

<div class="jumbotron">
    <div class="container">
        <%@ include file="loginUser.jsp" %>
        <h1>Bookstore</h1>
        <p>
            <a class="btn btn-primary btn-lg" href="<c:url value="/books"/>">Show books</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/books/form"/>">Add new book</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/users"/>">Show users</a>
        </p>
    </div>
</div>

</body>