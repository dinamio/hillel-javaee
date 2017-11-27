<%@ include file="header.jsp" %>

<head>
    <title>New user</title>
</head>


<body>

<div class="jumbotron">
    <div class="container" id="formHeader" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div><h1>Register new user</h1></div>
    </div>
</div>


<section class="container col-md-5 col-md-offset-2">
    <c:if test="${message!=null && message.value!=null}">
        <span class="label label-danger">${message.value}</span>
    </c:if>

    <form:form method="post" action="${pageContext.request.contextPath}/user/register" modelAttribute="user"
               cssClass="form-horizontal" enctype="multipart/form-data">
        <fieldset>
            <legend>Please enter credentials</legend>
            <div class="form-group">
                <label for="firstName" class="col-sm-3 control-label">First name</label>
                <div class="col-sm-9">
                    <form:input id="firstName" path="firstName" type="text" value="${user.firstName}"
                                cssClass="form-control"/>
                    <form:errors path="firstName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-sm-3 control-label">Second Name</label>
                <div class="col-sm-9">
                    <form:input id="lastName" path="lastName" type="text" value="${user.lastName}"
                                cssClass="form-control"/>
                    <form:errors path="lastName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-sm-3 control-label">Login</label>
                <div class="col-sm-9">
                    <form:input id="login" path="loginName" type="text" value="${user.loginName}"
                                cssClass="form-control"/>
                    <form:errors path="loginName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Password</label>
                <div class="col-sm-9">
                    <form:password id="password" path="encodedPassword" value="${user.encodedPassword}"
                                   cssClass="form-control" maxlength="50"/>
                    <form:errors path="encodedPassword" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Confirm Password</label>
                <div class="col-sm-9">
                    <input type="password" minlength="1" maxlength="50" required="required" class="form-control"
                           id="confirm_password"
                           placeholder="confirm password">
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Register</button>
        </fieldset>
    </form:form>

</section>
<script>
    document.getElementById("formHeader").style.cursor = "pointer";

    function sendRedirectRequest(url) {
        window.location = url
    }

    let password = document.getElementById("password");
    let confirm_password = document.getElementById("confirm_password");

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

