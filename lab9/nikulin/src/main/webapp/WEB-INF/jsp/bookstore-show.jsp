<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/23/17
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="">--%>
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>Books</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%--<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">--%>

    <!-- Custom styles for this template -->
    <%--<link href="/css/jumbotron-narrow.css" rel="stylesheet">--%>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="/books/menu">Home</a></li>
                <li role="presentation"><a href="/order">Basket</a></li>
                <li role="presentation"><a href="/profile">Profile</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Books</h3>
    </div>



    <div class="jumbotron">
        <div class="form-group">

            <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#searchText').keyup(function () {
                        var genre = $('#searchText').val();
                        console.log(genre);
                        $.ajax({
                            type: 'GET',
                            data: {genre: genre},
                            url: 'showhints',
                            success: function (result) {
                                $('#result1').html(result);
                                document.getElementById('result1').innerHTML = result;
                            }
                        })
                    })
                })
            </script>

            <form:form modelAttribute="searchForm">
                <form:input path="searchText" name="searchText" id="searchText" placeholder="Comedy, Tragicomedy, Satire, Horror Fiction, Fantasy, Drama, Mythology, Romance, Manga, Programming,  Scientific, Literary realism" cssClass="form-control"/>
                <input type="submit" value="Search" class="btn btn-lg btn-success"/>
            </form:form>
            <p id="result1"></p>
        </div>
    </div>

    <c:forEach items="${products}" var="prod">
        <div class="jumbotron">
            <h1>
                    ${prod.name}
            </h1>
            <p class=\"lead\">Id: ${prod.id} </p>
            <p>Pages: ${prod.pages}</p>
            <p>Publisher: ${prod.publisher}</p>
            <p>Price: ${prod.price}</p>
            <p>Authors:
                <c:forEach items="${prod.authors}" var="author">
                    ${author.name},
                </c:forEach>
            </p>
            <p>Genres:
                <c:forEach items="${prod.genres}" var="genre">
                    ${genre.genrename},
                </c:forEach>
            </p>
            <p><a class="btn btn-lg btn-success" role="button" href="/books/${prod.id}" type="submit">Edit</a></p>
            <p><a class="btn btn-lg btn-success" role="button" href="/books/remove/${prod.id}" type="submit">Remove</a></p>
            <p><a class="btn btn-lg btn-success" role="button" href="/order/${prod.id}" type="submit">Add to bucket</a></p>
        </div>
    </c:forEach>


</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
