<%@ include file="header.jsp" %>

<head>
    <title>${new_book}</title>
</head>


<body>

<div class="jumbotron" style="cursor: pointer" id="formHeader">
    <%@ include file="language_login.jsp" %>
    <div class="container" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div><h1>${add_new_book_to}</h1></div>
    </div>
</div>

<section class="container col-md-5 col-md-offset-2">
    <form:form method="post" action="${pageContext.request.contextPath}/books" modelAttribute="book"
               cssClass="form-horizontal" enctype="multipart/form-data">
        <fieldset>
            <legend>${new_book_info}.</legend>
            <form:input id="id" path="id" type="hidden" value="${book.id}" readonly="true"/>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="title" class="col-md-2 control-label">${title}</label>
                    <div class="col-md-10">
                        <form:input id="title" path="title" type="text" value="${book.title}"
                                    cssClass="form-control"/>
                        <form:errors path="title" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="author" class="col-md-2 control-label">${author}</label>
                    <div class="col-md-10">
                        <form:input id="author" path="author" type="text" value="${book.author}"
                                    cssClass="form-control"/>
                        <form:errors path="author" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="language" class="col-md-2 control-label">${language}</label>
                    <div class="col-md-10">
                        <form:input id="language" path="language" type="text"
                                    value="${book.language}" cssClass="form-control"/>
                        <form:errors path="language" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="countries" class="col-md-2 control-label">${country}</label>
                    <div class="col-md-10">
                        <form:input id="countries" path="countries" type="text"
                                    value="${book.countries}" cssClass="form-control"/>
                        <form:errors path="countries" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="reviewers" class="col-md-2 control-label">${reviewers}</label>
                    <div class="col-md-10">
                        <form:input id="reviewers" path="reviewers" type="text" class="form:input-large"
                                    value="${book.reviewers}" cssClass="form-control"/>
                        <form:errors path="reviewers" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9">
                    <label for="year" class="col-md-2 control-label">${year}</label>
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
                    <label for="pages" class="col-md-2 control-label">${pages}</label>
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
                    <label class="control-label col-md-2" for="bookImage">${image}</label>
                    <div class="col-md-10">
                        <form:input id="bookImage" path="bookImage" type="file" cssClass="btn btn-info"/>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">${submit}</button>
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

