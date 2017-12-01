<%@ include file="header.jsp" %>

<head>
    <title>${login}</title>
</head>


<body>

<div class="jumbotron">
    <%@ include file="language_login.jsp" %>
    <div class="container" id="formHeader" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div><h1>${login_to_bookstore}</h1></div>
    </div>
</div>

<section class="container col-md-5 col-md-offset-2">
    <c:if test="${loginMessage!=null}">
        <span class="label label-danger">${loginMessage}</span>
    </c:if>
    <form role="form" class="form-horizontal" name="userSecurityDto" method="post"
          action="${pageContext.request.contextPath}/login">
        <fieldset>
            <legend>${enter_credentials}</legend>
            <div class="form-group">
                <label for="login" class="col-sm-3 control-label">${login}</label>
                <div class="col-sm-9">
                    <input type="text" minlength="1" maxlength="50" required="required" class="form-control" id="login"
                           name="login"
                           placeholder="${login}">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">${password}</label>
                <div class="col-sm-9">
                    <input type="password" minlength="1" maxlength="50" required="required" class="form-control"
                           id="password"
                           name="password"
                           placeholder="${password}">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">${login}</button>
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

