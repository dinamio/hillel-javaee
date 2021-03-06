<%@ include file="header.jsp" %>

<head>
    <title>Login</title>
</head>


<body>

<div class="jumbotron">
    <div class="container" id="formHeader" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div><h1>Login to bookstore</h1></div>
    </div>
</div>

<section class="container col-md-5 col-md-offset-2">
    <c:if test="${message!=null && message.value!=null}">
        <span class="label label-danger">${message.value}</span>
    </c:if>
    <form role="form" class="form-horizontal" name="bookForm" method="post"
          action="${pageContext.request.contextPath}/login">
        <fieldset>
            <legend>Enter the valid credentials.</legend>
            <div class="form-group">
                <label for="login" class="col-sm-3 control-label">Login</label>
                <div class="col-sm-9">
                    <input type="text" minlength="1" maxlength="50" required="required" class="form-control" id="login"
                           name="login"
                           placeholder="login">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Password</label>
                <div class="col-sm-9">
                    <input type="password" minlength="1" maxlength="50" required="required" class="form-control"
                           id="password"
                           name="password"
                           placeholder="password">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </fieldset>
    </form>

</section>
<script>
    document.getElementById("formHeader").style.cursor = "pointer";

    function sendRedirectRequest(url) {
        window.location = url
    }

</script>
</body>

