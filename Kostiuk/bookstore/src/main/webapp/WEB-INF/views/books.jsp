<%@ include file="header.jsp" %>

<head>
    <title>${booksMsg}</title>
</head>

<body>

<div class="jumbotron">
    <%@ include file="language_login.jsp" %>
    <div class="container" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div class="h1">${booksMsg}
            <a class="btn btn-default btn-lg" href="<c:url value="/books/form"/>">${add_new_book}</a>
        </div>
    </div>
</div>
<section class="container col-md-push-1 center-block col-md-10">
    <a class="btn btn-warning btn-sm pull-right col-md-1" onclick=toBottom()>[ ${to_bottom} ]</a>
    <table class="table table-striped table-condensed table-hover">
        <thead>
        <tr>
            <th><h4>ID</h4></th>
            <th><h4>${title}</h4></th>
            <th><h4>${author}</h4></th>
            <th><h4>${country}</h4></th>
            <th><h4>${year}</h4></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book">
            <tr>
                <td><c:out value="#${book.id}"/></td>
                <td><b><c:out value="${book.title}"/></b></td>
                <td><c:out value="${book.author.fullName}"/></td>
                <td>
                    <c:forEach items="${book.author.countries}" var="country">
                        <ul class="list-unstyled">
                            <li><c:out value="${country.name}"/></li>
                        </ul>
                    </c:forEach>
                </td>
                <td><c:out value="${book.year}"/></td>
                <td>
                    <a class="btn btn-primary btn-sm"
                       href="<c:url value="/books/${book.id}" />">${about_book}</a>
                    <a class="btn btn-danger btn-sm" href="" type="button"
                       onclick=sendDeleteRequest("<c:url value="/books/delete?id=${book.id}"/>")>${delete}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-warning btn-sm pull-right col-md-1" onclick=toTop()>[ ${to_top} ]</a>
</section>
<spring:url value="/js/jquery-3.2.1.min.js" var="jquery"/>
<spring:url value="/js/bookstore.js" var="bookstore_js"/>
<script src="${jquery}"></script>
<script src="${bookstore_js}"></script>
</body>
