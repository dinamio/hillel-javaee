<%@ include file="header.jsp" %>

<head>
    <title>${bookstore}</title>
</head>

<body>

<div class="jumbotron">
    <%@ include file="language_login.jsp" %>
    <div class="container ">
        <h1><spring:message code="welcome.message"/></h1>
        <c:if test="${registerMessage!=null}">
            <h4><span class="label label-success"><c:out value="${registerMessage.value}"/></span></h4>
        </c:if>
        <p>
            <a class="btn btn-default btn-lg" href="<c:url value="/user/register"/>">${add_new_user}</a>
            <a class="btn btn-default btn-lg" href="<c:url value="/login"/>">${login}</a>
        </p>
    </div>
</div>

</body>