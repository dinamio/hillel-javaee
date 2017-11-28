<%@ include file="header.jsp" %>

<head>
    <title>New user</title>
</head>


<body>

<div class="jumbotron">
    <%@ include file="language_login.jsp" %>
    <div class="container" id="formHeader" onclick=sendRedirectRequest("<c:url value="/"/>")>
        <div><h1>Register new user</h1></div>
    </div>
</div>


<section class="container col-md-5 col-md-offset-2">
    <c:if test="${message!=null && message.value!=null}">
        <span class="label label-danger">${message.value}</span>
    </c:if>

    <form role="form" class="form-horizontal" name="bookFormDto" method="post"
          action="${pageContext.request.contextPath}/user/register">
        <fieldset>
            <legend>Please enter credentials</legend>
            <div class="form-group">
                <label for="firstName" class="col-sm-3 control-label">First name</label>
                <div class="col-sm-9">
                    <input type="text" minlength="3" maxlength="50" required="required" class="form-control"
                           id="firstName"
                           name="firstName"
                           placeholder="first name">
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-sm-3 control-label">Second Name</label>
                <div class="col-sm-9">
                    <input type="text" minlength="3" maxlength="50" required="required" class="form-control"
                           id="lastName"
                           name="lastName"
                           placeholder="second name">
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-sm-3 control-label">Login</label>
                <div class="col-sm-9">
                    <input type="text" minlength="4" maxlength="20" required="required" class="form-control" id="login"
                           name="loginName"
                           placeholder="login">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Password</label>
                <div class="col-sm-9">
                    <input type="password" minlength="4" maxlength="20" class="form-control" id="password"
                           name="encodedPassword"
                           placeholder="password">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Password</label>
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
<script>
    document.getElementById("formHeader").style.cursor = "pointer";

    function sendRedirectRequest(url) {
        window.location = url
    }

    var password = document.getElementById("password")
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

