<%@ include file="header.jsp" %>

<head>
    <title>New user</title>
</head>


<body>

<div class="jumbotron">
    <%@ include file="language_login.jsp" %>
    <div class="container" id="formHeader" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div><h1>${register_new_user}</h1></div>
    </div>
</div>


<section class="container col-md-5 col-md-offset-2">
    <c:if test="${message!=null && message.value!=null}">
        <span class="label label-danger">${message.value}</span>
    </c:if>

    <form role="form" class="form-horizontal" name="bookFormDto" method="post"
          action="${pageContext.request.contextPath}/user/register">
        <fieldset>
            <legend>${enter_credentials}</legend>
            <div class="form-group">
                <label for="firstName" class="col-sm-3 control-label">${first_name}</label>
                <div class="col-sm-9">
                    <input type="text" minlength="3" maxlength="50" required="required" class="form-control"
                           id="firstName"
                           name="firstName"
                           placeholder="first name">
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-sm-3 control-label">${second_name}</label>
                <div class="col-sm-9">
                    <input type="text" minlength="3" maxlength="50" required="required" class="form-control"
                           id="lastName"
                           name="lastName"
                           placeholder="second name">
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-sm-3 control-label">${login}</label>
                <div class="col-sm-9">
                    <input type="text" minlength="4" maxlength="20" required="required" class="form-control" id="login"
                           name="loginName"
                           placeholder="login">
                </div>
            </div>
            <%--<sec:authorize access="hasAuthority('ADMIN')">--%>
            <%--<div class="form-check form-check-inline">--%>
            <%--<label class="form-check-label">--%>
            <%--<input class="form-check-input" type="checkbox"  name="admin_role"> ADMIN--%>
            <%--</label>--%>
            <%--</div>--%>
            <%--</sec:authorize>--%>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">${password}</label>
                <div class="col-sm-9">
                    <input type="password" minlength="4" maxlength="20" class="form-control" id="password"
                           name="password"
                           placeholder="password">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">${password}</label>
                <div class="col-sm-9">
                    <input type="password" minlength="1" maxlength="50" required="required" class="form-control"
                           id="confirm_password"
                           placeholder="confirm password">
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Register</button>
        </fieldset>
    </form>

</section>

<spring:url value="/js/bookstore.js" var="bookstore_js"/>
<script src="${bookstore_js}"></script>
<script>
    document.getElementById("formHeader").style.cursor = "pointer";

    let password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords don't match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>

