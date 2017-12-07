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
        <%@ include file="loginUser.jsp" %>
        <div class="h1">Users</div>
    </div>
</div>
<section class="container col-md-5 col-md-offset-1">
    <div class="row">
        <table id="users_table" class="table table-hover table-striped col-md-4">
            <thead>
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
                        <%--<tr onclick="updateUserBooks(${user.id})">--%>
                    <td><h5><c:out value="#${user.id}"/></h5></td>
                    <td><h5><c:out value="${user.firstName}"/></h5></td>
                    <td><h5><c:out value="${user.lastName}"/></h5></td>
                    <td><h5><c:out value="${user.loginName}"/></h5></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="container col-md-offset-6 col-md-8" id="table_container">

        </div>
    </div>
</section>

<script>

    document.getElementById("booksList").style.cursor = "pointer";
	
function sendRedirectRequest(url) {
    window.location = url;
}
	
	
</script>

</body>
