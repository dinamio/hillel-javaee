<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="pull-right">
    <sec:authorize access="isAuthenticated()">
        <h4>Current user:</h4><h4><b><c:out value="${firstName}  ${lastName}"/></b></h4>
        <h4>Expiration date: <c:out value="${expiration_date}"/></h4>
        <a class="btn btn-danger btn-sm pull-right" href="<c:url value="/logout"/>">Logout</a>
    </sec:authorize>
</div>