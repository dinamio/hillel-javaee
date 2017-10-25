<%@ include file="header.jsp" %>

<div class="pull-right">
    <c:if test="${user!=null}">
        <h5>Current user: <b><c:out value="${user.firstName}  ${user.lastName}"/></b></h5>
        <h6>Expiration date: <c:out value="${expDate}"/></h6>
        <a class="btn btn-danger btn-sm pull-right" href="<c:url value="/logout"/>">Logout</a>
    </c:if>
</div>