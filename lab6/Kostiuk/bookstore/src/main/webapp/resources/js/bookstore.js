function hideTableContainer() {
    let container = $("#table_container");
    container.hide();
}

function updateUserBooks(userID) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/users?id=' + userID,
        dataType: 'json',
        contentType: "application/json",
        async: false,
        success: function (data) {
            let container = $("#table_container");
            let message_data = '';
            console.log(data);
            // container.hide(0);
            container.empty();
            message_data += "<table id=\"users_table\" class=\"table table-hover table-condensed table-striped\">";
            message_data += "<thead>";
            message_data += "<tr>";
            message_data += "<th><h5><b>Title</b></h5></th>";
            message_data += "<th><h5>Author</h5></th>";
            message_data += "<th><h5>Year</h5></th>";
            message_data += "<th><h5>Pages</h5></th>";
            message_data += "</tr>";
            message_data += "</thead>";
            message_data += "<tbody>";
            $.each(data, function (key, value) {
                console.log(key + "  " + value.id);
                let bookURL = "http://localhost:8080/books?id=" + value.id;
                console.log(bookURL);
                let author = value.author;
                let title = value.title;
                let year = value.year;
                let pages = value.pages;
                message_data += "<tr onclick=\"sendRedirectRequest(" + "\"" + bookURL + "\"" + ")\">";
                message_data += "<td><h6>" + title + "</h6></td>";
                message_data += "<td><h6>" + author + "</h6></td>";
                message_data += "<td><h6>" + year + "</h6></td>";
                message_data += "<td><h6>" + pages + "</h6></td>";
                message_data += "</tr>";
            });
            message_data += "</tbody>";
            message_data += "</table>";

            container.append(message_data);
            // container.slideDown();
        }
    });
}

function sendRedirectRequest(url) {
    window.location = url;
}

$('tr').css('cursor', 'pointer');
