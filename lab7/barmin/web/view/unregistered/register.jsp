<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="../../style/bootstrap-4/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form action="/registerServlet" method="post">
        <div class="form-group">
            <label><b>Login</b></label>
            <input type="text" class="form-control" placeholder="Enter login" name="uname" required><br>
        </div>
        <div class="form-group">
            <label><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="psw" required><br>
        </div>
        <button type="submit" class="btn btn-block btn-danger btn-lg" >Sign Up</button>
    </form>
</div>

</body>
</html>

