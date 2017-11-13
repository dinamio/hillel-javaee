<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Books</title>
</head>


<body>

<div class="jumbotron" id="booksList" style="cursor: pointer" onclick=sendRedirectRequest("<c:url value="/welcome"/>")>
    <div class="container">
        <%@ include file="loginUser.jsp" %>
        <div class="h1">Users</div>
    </div>
</div>
<section class="container col-md-7 col-sm-offset-1">
    <div>
        <table id="users_table" class="table table-hover table-striped table-responsive table-bordered">
            <thead class="info">
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
                        <%--<td>--%>
                        <%--<a class="btn btn-danger btn-sm" href=""--%>
                        <%--onclick=sendDeleteRequest("<c:url value="/users?id=${user.id}"/>")>Delete</a>--%>
                        <%--</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-8" id="table_container">

    </div>
    <%--</div>--%>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../resources/js/bookstore.js"></script>
</body>
