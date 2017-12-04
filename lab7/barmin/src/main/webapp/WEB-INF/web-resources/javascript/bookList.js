$(document).ready(function(){
    $.get("/booksController", function(responseJson) {
        $.each(responseJson, function(index, book) {
            console.log(book);
            var bookName = book.name;
            var authorsString = "";
            $.each(book.authors, function (i, author) {
                authorsString += author.name + " " + author.surname + ", ";
            });
            $("<tr>", bookName).appendTo('#bookTable')
                .append($("<td hidden>").text(book.id))
                .append($("<td>").text(book.name))
                .append($("<td>").text(authorsString))
                .append($("<td>").text(book.userID.username))
                .append($("<td>").append($('<button type="submit" class="delete btn btn-info btn-xs">').text('delete')));
        });
    });
});

$(document).ready(function () {

    $('table').on("click", "button.delete",(function () {
        if (confirm("Are you sure you want to delete this row?")) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var parent = $(this).parent().parent();
            var data = {
                id: $(this).parent().parent().children().eq(0).text()
            };
            $.ajax(
                {
                    type: "Delete",
                    url: "/booksController",
                    data: JSON.stringify(data),
                    cache: false,
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    success: function () {
                        parent.fadeOut('slow', function () {
                            $(this).remove();
                        });
                    },
                    error: function() {
                        alert("FAILURE !");
                    }
                })
        }
    }));
});

