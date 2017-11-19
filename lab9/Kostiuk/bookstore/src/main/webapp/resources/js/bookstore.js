function hideTableContainer() {
    let container = $("#table_container");
    container.hide();
}

function updateUserBooks(userID) {
    let booksUrl = connectionOptions.url + ":" + connectionOptions.port + "/users?id=" + userID;
    $.ajax({
        type: 'GET',
        url: booksUrl,
        dataType: 'json',
        contentType: "application/json",
        async: false,
        success: function (data) {
            let container = $("#table_container");
            let message_data = '';
            console.log(data);
            container.empty();
            message_data += "<table id=\"users_table\" class=\"table table-hover table-responsive table-condensed table-striped table-bordered\">";
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
                let bookURL = connectionOptions.url + ":" + connectionOptions.port + "/books?id=" + value.id;
                let author = value.author;
                let title = value.title;
                let year = value.year;
                let pages = value.pages;
                message_data += "<tr style='cursor: hand' onclick='sendRedirectRequest(" + "\"" + bookURL + "\"" + ")'>";
                message_data += "<td><h6>" + title + "</h6></td>";
                message_data += "<td><h6>" + author + "</h6></td>";
                message_data += "<td><h6>" + year + "</h6></td>";
                message_data += "<td><h6>" + pages + "</h6></td>";
                message_data += "</tr>";
            });
            message_data += "</tbody>";
            message_data += "</table>";

            container.append(message_data);
        }
    });
}

var xhr;
let writersUrl = connectionOptions.url + ":" + connectionOptions.port + "/rest/writers";
new autoComplete({
    selector: 'input[name="author"]',
    minChars: 1,
    source: function (term, response) {
        try {
            xhr.abort();
        } catch (e) {
        }
        xhr = $.getJSON(writersUrl, function (data) {
            response(data);
            console.log(data)
        });
    }
});


let writerNames;

function updateWriters() {
    let writerUrl = connectionOptions.url + ":" + connectionOptions.port + "/rest/writers";
    $.ajax({
        type: 'GET',
        url: writerUrl,
        dataType: 'json',
        contentType: "application/json",
        async: false,
        success: function (data) {
            console.log(data);

            // $("#author").autocomplete({
            //     source: data,
            //     focus: function (event, ui) {
            //         console.log(ui);
            //         $("#author").val(ui.item.fullName);
            //     },
            //     select: function (event, ui) {
            //         $("#author_id").val(ui.item.value);
            //         $("#author").val(ui.item.fullName);
            //     }
            // });
        }
    });


}


function writersName(inputField, input_id) {
    console.log(inputField);

    $("#" + inputField).autocomplete({
        source: writerNames,
        focus: function (event, ui) {
            console.log(ui);
            $("#" + inputField).val(ui.item.fullName);
        },
        select: function (event, ui) {
            $("#" + input_id).val(ui.item.value);
            $("#" + inputField).val(ui.item.fullName);
        }
    });
}


function sendDeleteRequest(url) {
    $.ajax({
        type: "DELETE",
        url: url
    });
}

function sendRedirectRequest(url) {
    window.location = url;
}

$('tr').css('cursor', 'pointer');


function toBottom() {
    $("html, body").animate({scrollTop: document.body.scrollHeight});
}


function toTop() {
    $("html, body").animate({scrollTop: 0});
}


