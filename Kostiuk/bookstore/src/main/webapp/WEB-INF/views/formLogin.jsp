<%@ include file="header.jsp" %>

<head>
    <title>${login}</title>
</head>


<body ng-app="bookstoreLogin" ng-controller="reCaptchaController">

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
    <form role="form" name="form" class="form-horizontal" method="post"
          ng-submit="form.$valid && sendForm(auth)">
        <fieldset>
            <legend>${enter_credentials}</legend>
            <%--<input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">--%>
            <div class="form-group">
                <label for="login" class="col-sm-3 control-label">${login}</label>
                <div class="col-sm-9">
                    <input type="text"
                           ng-minlength="1" ng-maxlength="50"
                           required ng-model="auth.login"
                           class="form-control"
                           id="login"
                           name="login"
                           placeholder="${login}">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">${password}</label>
                <div class="col-sm-9">
                    <input type="password"
                           ng-minlength="1" ng-maxlength="50"
                           required ng-model="auth.password"
                           class="form-control"
                           id="password"
                           name="password"
                           placeholder="${password}">
                </div>
            </div>
            <recaptcha sitekey="6Lc6PRsUAAAAAB_mT5nz_ymqm5yb_etcOu7ZB2HV"
                       ng-model="auth.reCaptchaResponse">
            </recaptcha>
            <button type="submit" class="btn btn-primary" ng-disabled="form.$invalid">${login}</button>
        </fieldset>
    </form>

</section>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js"></script>
<script type="text/javascript" src="https://www.google.com/recaptcha/api.js" async defer></script>
<spring:url value="/js/login.js" var="loginJS"/>
<spring:url value="/js/config.js" var="config"/>
<spring:url value="/js/bookstore.js" var="bookstore_js"/>
<script type="text/javascript" src="${loginJS}"></script>
<script type="text/javascript" src="${config}"></script>
<script type="text/javascript" src="${bookstore_js}"></script>
<script>
    document.getElementById("formHeader").style.cursor = "pointer";
</script>
</body>

