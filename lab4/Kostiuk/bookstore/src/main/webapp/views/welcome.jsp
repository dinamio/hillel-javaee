<%@ include file="header.jsp" %>

<head>
    <title>Bookstore</title>
</head>

<body>

<div class="jumbotron">
    <div class="container">
        <%@ include file="user.jsp" %>
        <h1>Bookstore</h1>
        <p>
            <a class="btn btn-primary btn-lg" href="<c:url value="/books"/>">View all books</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/views/form.jsp"/>">Add new book</a>
        </p>
    </div>
</div>

</body>