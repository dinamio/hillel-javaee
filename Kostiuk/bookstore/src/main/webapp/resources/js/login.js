angular.module("bookstoreLogin", [])
    .controller("reCaptchaController", function ($scope, $http) {
        $scope.auth = {};
        $scope.sendForm = function (auth) {
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: {"Content-Type": "application/x-www-form-urlencoded"}
            }).then(
                function (data) {
                    let welcome = connectionOptions.url + ":" + connectionOptions.port + "/welcome";
                    sendRedirectRequest(welcome);
                },
                function (error) {
                    let login = connectionOptions.url + ":" + connectionOptions.port + "/login";
                    sendRedirectRequest(login);
                    window.alert("Unauthenticated")
                }
            )
        }
    })
    .directive("recaptcha", function () {
        return {
            require: "ngModel",
            restrict: "E",
            scope: {
                sitekey: "@",
                ngModel: "="
            },
            link: function (scope, element, attrs, ngModelCtrl) {
                let reCaptcha = document.createElement("script");
                reCaptcha.type = "text/javascript";
                reCaptcha.async = true;
                reCaptcha.src = "https://www.google.com/recaptcha/api.js?onload=onLoadReCaptchaCallback&render=explicit";
                let firstScript = document.getElementsByTagName("script")[0];
                firstScript.parentNode.insertBefore(reCaptcha, firstScript);

                window.onLoadReCaptchaCallback = function () {
                    grecaptcha.render(element.get(0), {
                        "sitekey": scope.sitekey,
                        "callback": onReCaptchaSubmit,
                        "expired-callback": onReCaptchaExpired
                    });
                };

                window.onReCaptchaSubmit = function (gReCaptchaResponse) {
                    scope.ngModel = gReCaptchaResponse;
                    ngModelCtrl.$setViewValue(gReCaptchaResponse);
                };
                window.onReCaptchaExpired = function () {
                    scope.ngModel = "";
                    ngModelCtrl.$setViewValue("");
                };

                ngModelCtrl.$validators.reCaptchaValidate = function (modelValue, viewValue) {
                    return !ngModelCtrl.$isEmpty(scope.ngModel)
                }

            }
        }
    });