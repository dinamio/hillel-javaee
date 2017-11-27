<%@ include file="header.jsp" %>

<head>
    <title>Users</title>
</head>

<body>

<div class="jumbotron" id="booksList" style="cursor: pointer" onclick=sendRedirectRequest("<c:url value="/"/>")>
    <div class="container">
        <%@ include file="loginUser.jsp" %>
        <div class="h1">Users</div>
    </div>
</div>
<section class="container col-md-9 col-sm-offset-1">
    <div>
        <table id="users_table" class=" col-md-pull-8 table table-hover table-striped table-responsive table-bordered">
            <thead class="thead-dark">
            <tr>
                <th><h4>ID</h4></th>
                <th><h4>First Name</h4></th>
                <th><h4>Last Name</h4></th>
                <th><h4>Login</h4></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                <tr onclick="updateUserBooks(${user.id})">
                    <td><h5><c:out value="#${user.id}"/></h5></td>
                    <td><h5><c:out value="${user.firstName}"/></h5></td>
                    <td><h5><c:out value="${user.lastName}"/></h5></td>
                    <td><h5><c:out value="${user.loginName}"/></h5></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="table_container" class="modal col-md-1">

    </div>
</section>
<spring:url value="/js/jquery-3.2.1.min.js" var="jquery"/>
<spring:url value="/js/bookstore.js" var="bookstore"/>
<spring:url value="/js/config.js" var="config"/>
<script src="${jquery}"></script>
<script src="${bookstore}"></script>
<script src="${config}"></script>
</body>
