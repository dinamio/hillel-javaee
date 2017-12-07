<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>Books</title>

    <link rel="stylesheet" type="text/css" href="/resources/style/bootstrap-4/css/bootstrap.min.css">
    <script type="text/javascript" src="/resources/javascript/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/resources/javascript/bookList.js"></script>

</head>
<body>

<table id="bookTable" align="center" style="max-width:500px;" border="1px">
    <tr>
        <th colspan="5" >Books list</th>
    </tr>
    <tr>
        <th hidden>id</th>
        <th>Name</th>
        <th>Author</th>
        <th>Added by</th>
        <th></th>
    </tr>
</table>



</body>
</html>