<%@ include file="header.jsp" %>

<div class="pull-right">
    <c:if test="${user!=null}">
        <h4>Current user:</h4><h4><b><c:out value="${user.firstName}  ${user.lastName}"/></b></h4>
        <h4>Expiration date: <c:out value="${expDate}"/></h4>
        <a class="btn btn-danger btn-sm pull-right" href="<c:url value="/logout"/>">Logout</a>
    </c:if>
</div>