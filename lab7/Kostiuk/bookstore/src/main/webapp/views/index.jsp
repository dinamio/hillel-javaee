<%@ include file="header.jsp" %>

<head>
    <title>Bookstore</title>
</head>

<body>

<div class="jumbotron">
    <div class="container ">
        <h1>Bookstore</h1>
        <h3><c:out value="${header.get(message)}"/></h3>
        <p>
            <a class="btn btn-default btn-lg" href="<c:url value="/register"/>">Add new user</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/login"/>">Login</a>
        </p>
    </div>
</div>

</body>