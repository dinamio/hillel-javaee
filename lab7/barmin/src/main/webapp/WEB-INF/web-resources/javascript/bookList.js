$(document).ready(function(){
    $.get("/booksController", function(responseJson) {
        $.each(responseJson, function(index, book) {
            var bookName = book.name;
            var authorsString = "";
            $.each(book.authors, function (i, author) {
                authorsString += author.name + " " + author.surname + ", ";
            });

            var lastComma = authorsString.lastIndexOf(', ');
            authorsString = authorsString.substring(0, lastComma) +  "" +authorsString.substring(lastComma + 1);

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
        if (confirm("Are you sure you want to delete this book?")) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var currentRow = $(this).parent().parent();
            var id = currentRow.children().eq(0).text();

            $.ajax({
                type: 'Delete',
                cache: false,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                url: '/booksController?id=' + id,
                success: function () {
                    currentRow.fadeOut('slow', function () {
                        $(this).remove();
                    });
                },
                error: function() {
                    alert("FAILURE !");
                }
            });
        }
    }));
});

