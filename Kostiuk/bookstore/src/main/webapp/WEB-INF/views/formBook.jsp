<%@ include file="header.jsp" %>

<head>
    <title>New book</title>
</head>


<body>

<div class="jumbotron" style="cursor: pointer" id="formHeader" onclick=sendRedirectRequest("<c:url value="/"/>")>
    <div class="container">
        <%@ include file="loginUser.jsp" %>
        <div><h1>Add new book to the store</h1></div>
    </div>
</div>

<section class="container col-md-5 col-md-offset-2">
    <form:form method="post" action="${pageContext.request.contextPath}/books" modelAttribute="book"
               cssClass="form-horizontal" enctype="multipart/form-data">
        <fieldset>
            <legend>Please enter as more values as possible.</legend>
            <form:input id="id" path="id" type="hidden" value="${book.id}" readonly="true"/>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="title" class="col-md-2 control-label">Title</label>
                    <div class="col-md-10">
                        <form:input id="title" path="title" type="text" value="${book.title}"
                                    cssClass="form-control"/>
                        <form:errors path="title" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="author" class="col-md-2 control-label">Author</label>
                    <div class="col-md-10">
                        <form:input id="author" path="author" type="text" value="${book.author}"
                                    cssClass="form-control"/>
                        <form:errors path="author" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="language" class="col-md-2 control-label">Language</label>
                    <div class="col-md-10">
                        <form:input id="language" path="language" type="text"
                                    value="${book.language}" cssClass="form-control"/>
                        <form:errors path="language" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="countries" class="col-md-2 control-label">Country</label>
                    <div class="col-md-10">
                        <form:input id="countries" path="countries" type="text"
                                    value="${book.countries}" cssClass="form-control"/>
                        <form:errors path="countries" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="reviewers" class="col-md-2 control-label">Reviewers</label>
                    <div class="col-md-10">
                        <form:input id="reviewers" path="reviewers" type="text" class="form:input-large"
                                    value="${book.reviewers}" cssClass="form-control"/>
                        <form:errors path="reviewers" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="year" class="col-md-2 control-label">Year</label>
                    <div class="col-md-10">
                        <form:input id="year" path="year" type="number" class="form:input-large"
                                    value="${book.year}"
                                    cssClass="form-control"/>
                        <form:errors path="year" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="pages" class="col-md-2 control-label">Pages</label>
                    <div class="col-md-10">
                        <form:input id="pages" path="pages" type="number" class="form:input-large"
                                    value="${book.pages}"
                                    cssClass="form-control"/>
                        <form:errors path="pages" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label class="control-label col-md-2" for="bookImage">Image</label>
                    <div class="col-md-10">
                        <form:input id="bookImage" path="bookImage" type="file" cssClass="btn btn-info"/>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </fieldset>
    </form:form>
</section>

<spring:url value="/js/jquery-3.2.1.min.js" var="jquery"/>
<spring:url value="/js/awesomplete.js" var="awesomplete"/>
<spring:url value="/js/bookstore.js" var="bookstore"/>
<spring:url value="/js/config.js" var="config"/>
<script src="${jquery}"></script>
<script src="${awesomplete}"></script>
<script src="${bookstore}"></script>
<script src="${config}"></script>
<script>
    window.onload = function () {
        updateWriters();
        updateCountries();
    };
</script>
</body>

