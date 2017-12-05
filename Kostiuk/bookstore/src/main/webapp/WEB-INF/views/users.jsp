<%@ include file="header.jsp" %>

<head>
    <title>${users}</title>
</head>

<body>

<div class="jumbotron" id="booksList" style="cursor: pointer">
    <%@ include file="language_login.jsp" %>
    <div class="container" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div class="h1">${users}</div>
    </div>
</div>
<section class="container col-md-9 col-sm-offset-1">
    <div>
        <table id="users_table" class=" col-md-pull-8 table table-hover table-striped table-responsive table-bordered">
            <thead class="thead-dark">
            <tr>
                <th><h4>ID</h4></th>
                <th><h4>${first_name}</h4></th>
                <th><h4>${second_name}</h4></th>
                <th><h4>${login}</h4></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${all_users}" var="user">
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
<spring:url value="/js/bookstore.js" var="bookstore_js"/>
<spring:url value="/js/config.js" var="config"/>
<script src="${jquery}"></script>
<script src="${bookstore_js}"></script>
<script src="${config}"></script>
</body>
