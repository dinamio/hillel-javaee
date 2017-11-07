<%@ include file="header.jsp" %>

<head>
    <title>New book</title>
</head>


<body>

<div class="jumbotron" style="cursor: pointer">
    <div class="container navbar-fixed-top" id="formHeader" onclick=sendRedirectRequest("<c:url value="/welcome"/>")>
        <%@ include file="loginUser.jsp" %>
        <div><h1>Add new book to the store</h1></div>
    </div>
</div>

<section class="container col-md-5 col-md-offset-2">
    <form role="form" class="form-horizontal" name="bookForm" method="post"
          action="${pageContext.request.contextPath}/books">
        <fieldset>
            <legend>Please enter as more values as possible.</legend>
            <div class="form-group">
                <label for="title" class="col-sm-3 control-label">Title</label>
                <div class="col-sm-9">
                    <input type="text" minlength="1" maxlength="50" value="Unknown_Title" required="required"
                           class="form-control" id="title"
                           name="title"
                           placeholder="title">
                </div>
            </div>
            <div class="form-group">
                <label for="author" class="col-sm-3 control-label">Author</label>
                <div class="col-sm-9">
                    <input type="text" minlength="1" maxlength="50" value="Unknown_Author" class="form-control"
                           id="author"
                           name="author"
                           placeholder="author">
                </div>
            </div>
            <div class="form-group">
                <label for="language" class="col-sm-3 control-label">Language</label>
                <div class="col-sm-9">
                    <input type="text" minlength="1" maxlength="20" value="Unknown_Language" class="form-control"
                           id="language" name="language"
                           placeholder="language">
                </div>
            </div>
            <div class="form-group">
                <label for="country" class="col-sm-3 control-label">Country</label>
                <div class="col-sm-9">
                    <input type="text" minlength="1" maxlength="50" value="Unknown_Country" class="form-control"
                           id="country" name="country"
                           placeholder="country">
                </div>
            </div>
            <div class="form-group">
                <label for="year" class="col-sm-3 control-label">Year</label>
                <div class="col-sm-9">
                    <input type="number" max="2018" min="-2000" minlength="1" maxlength="4" value="2017"
                           required="required"
                           class="form-control"
                           id="year"
                           name="year" placeholder="year">
                </div>
            </div>
            <div class="form-group">
                <label for="pages" class="col-sm-3 control-label">Pages</label>
                <div class="col-sm-9">
                    <input type="number" min="1" minlength="1" maxlength="4" value="15" class="form-control" id="pages"
                           name="pages" placeholder="pages">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Add new book</button>
        </fieldset>
    </form>

</section>

<script src="../resources/js/bookstore.js"></script>
</body>

