<%@ include file="header.jsp" %>

<head>
    <title><c:out value="${book.title}"/></title>
</head>


<body>

<div class="jumbotron">
    <div class="container" id="title" onclick=sendRedirectRequest("<c:url value="/welcome"/>")>
        <%@ include file="user.jsp" %>
        <div><h1><c:out value="${book.title}"/></h1></div>
    </div>
</div>

<section class="container col-md-6 col-md-offset-2">
    <div class="row">

        <div class="col-md-5">
            <img src="<c:url value="/resources/${book.imageLink}"/>"
                 alt="image" style="width: 80%"/>
        </div>

        <div class="col-md-7">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th class="h3 success col-md-6">Book Info</th>
                    <th class="success col-md-6"><a class="btn btn-warning col-sm-8" target="_blank"
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
                    <td><c:out value="${book.author}"/></td>
                </tr>
                <tr>
                    <td>Language</td>
                    <td><c:out value="${book.language}"/></td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><c:out value="${book.country}"/></td>
                </tr>
                <tr>
                    <td>Year</td>
                    <td><c:out value="${book.year}"/></td>
                </tr>
                <tr>
                    <td>Pages</td>
                    <td><c:out value="${book.pages}"/></td>
                </tr>
                </tbody>
            </table>
            <a href="<c:url value="/books" />" class="btn btn-primary">Back to catalog</a>
        </div>
    </div>

</section>

<script>
    document.getElementById("title").style.cursor = "pointer";

    function sendRedirectRequest(url) {
        window.location = url
    }

</script>

</body>
