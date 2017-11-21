<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <link rel="stylesheet" type="text/css" href="../../style/bootstrap-4/css/bootstrap.min.css">
    <script type="text/javascript" src="/javascript/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $.get("/booksServlet", function(responseJson) {
                $.each(responseJson, function(index, book) {
                    var bookName = book.name;
                    var authorsString = "";
                    $.each(book.authors, function (i, author) {
                        authorsString += author.name + " " + author.surname + ", ";
                    });
                    $("<tr>", bookName).appendTo('#bookTable')
                        .append($("<td hidden>").text(book.id))
                        .append($("<td>").text(book.name))
                        .append($("<td>").text(authorsString))
                        .append($("<td>").text(book.postedBy))
                        .append($("<td>").append($('<button class="delete btn btn-info btn-xs">').text('delete')));
                });
            });
        })
    </script>
    <script>
        console.log("Loading")
        $(document).ready(function () {
            console.log("Loading 2")
            $('table').on("click", "button.delete",(function () {
                console.log("DELETE FUNC")
                if (confirm("Are you sure you want to delete this row?")) {
                    var parent = $(this).parent().parent();
                    var data = {
                        id: $(this).parent().parent().children().eq(0).text(),
                    };
                    $.ajax(
                        {
                            type: "Delete",
                            url: "/booksServlet",
                            data: JSON.stringify(data),
                            cache: false,

                            success: function () {
                                console.log("Success");
                                parent.fadeOut('slow', function () {
                                    $(this).remove();
                                });
                            }
                        });
                }
            }));
        });
    </script>
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