<%@ include file="header.jsp" %>

<head>
    <title><c:out value="${book.title}"/></title>
</head>


<body>

<div class="jumbotron">
    <div class="container" id="title" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <%@ include file="loginUser.jsp" %>
        <div><h1><c:out value="${book.title}"/></h1></div>
    </div>
</div>

<section class="container col-md-6 col-md-offset-2">
    <div class="row">

        <div class="col-md-5">
            <img alt="image" src="data:image/png;base64,${image}" style="width: 80%"/>
        </div>

        <div class="col-md-7">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th class="h3 success col-md-6">Book Info</th>
                    <th class="success col-md-6"><a class="btn btn-warning col-sm-8" type="_blank"
                                                    href="<c:out value="${book.link}"/>">Go to wiki</a></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><b>Title</b></td>
                    <td><b><c:out value="${book.title}"/></b></td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td><c:out value="${book.author.fullName}"/></td>
                </tr>
                <tr>
                    <td>Language</td>
                    <td><c:out value="${book.language}"/></td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td>
                        <ul class="list-unstyled">
                            <c:forEach items="${book.author.countries}" var="country">
                                <li class="list-group-item"><c:out value="${country.name}"/></li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Year</td>
                    <td><c:out value="${book.year}"/></td>
                </tr>
                <tr>
                    <td>Pages</td>
                    <td><c:out value="${book.pages}"/></td>
                </tr>
                <tr>
                    <td>Added by</td>
                    <td>
                        <ul class="list-unstyled">
                            <li class="list-group-item" style="cursor: hand"
                                onclick=sendRedirectRequest("<c:url value="/users"/>")>
                                <c:choose>
                                    <c:when test="${book.user.loginName=='undefined'}">
                                        <c:out value="Undefined"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${book.user.firstName}  ${book.user.lastName}"/>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Viewers</td>
                    <td>
                        <ul class="list-unstyled">
                            <c:forEach items="${book.reviewers}" var="reviewer">
                                <li class="list-group-item"><c:out value="${reviewer.fullName}"/></li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="button-bar">
                <a href="<c:url value="/books" />" class="btn btn-primary">Back to catalog</a>
                <a href="<c:url value="/books/edit?id=${book.id}" />" class="btn btn-primary">Edit</a>
            </div>
        </div>
    </div>

</section>
<spring:url value="/js/bookstore.js" var="bookstore"/>
<script src="${bookstore}"></script>

</body>
