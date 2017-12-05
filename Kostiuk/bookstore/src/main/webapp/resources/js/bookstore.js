function updateUserBooks(userID) {
    let booksUrl = connectionOptions.url + ":" + connectionOptions.port + "/rest/users/" + userID + "/books";
    console.log(booksUrl);
    let container = $("#table_container");
    container.fadeOut(200, function () {
        $.ajax({
            type: 'GET',
            url: booksUrl,
            dataType: 'json',
            contentType: "application/json",
            async: false,
            success: function (data) {
                let message_data = '';
                console.log(data);
                container.empty();
                message_data += "<table class=\"table table-hover table-responsive table-condensed table-striped table-bordered\">";
                message_data += "<thead class=\"thead-dark\">";
                message_data += "<tr>";
                message_data += "<th><h5><b>Title</b></h5></th>";
                message_data += "<th><h5>Author</h5></th>";
                message_data += "<th><h5>Year</h5></th>";
                message_data += "<th><h5>Pages</h5></th>";
                message_data += "</tr>";
                message_data += "</thead>";
                message_data += "<tbody>";
                $.each(data, function (key, value) {
                    let bookURL = connectionOptions.url + ":" + connectionOptions.port + "/books/" + value.id;
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
                container.fadeIn(300);
            }
        });
    });
}

function updateWriters() {
    let writerUrl = connectionOptions.url + ":" + connectionOptions.port + "/rest/writers";
    $.ajax({
        type: 'GET',
        url: writerUrl,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        async: true,
        success: function (data) {
            console.log(data);
            let list = data.map(function (i) {
                return i.fullName;
            });
            new Awesomplete(document.getElementById('author'), {
                minChars: 1,
                list: list
            });
            multiAwesomplete('reviewers', list);
        }
    });
}

function updateCountries() {
    let countryUrl = connectionOptions.url + ":" + connectionOptions.port + "/rest/countries";
    $.ajax({
        type: 'GET',
        url: countryUrl,
        dataType: 'json',
        contentType: "application/json",
        async: true,
        success: function (data) {
            console.log(data);
            let list = data.map(function (i) {
                return i.name;
            });
            multiAwesomplete('countries', list);
        }
    });
}

let multiAwesomplete = function (element, list) {
    new Awesomplete(document.getElementById(element), {
        minChars: 1,
        list: list,
        filter: function (text, input) {
            return Awesomplete.FILTER_CONTAINS(text, input.match(/[^,]*$/)[0]);
        },

        item: function (text, input) {
            return Awesomplete.ITEM(text, input.match(/[^,]*$/)[0]);
        },

        replace: function (text) {
            let before = this.input.value.match(/^.+,\s*|/)[0];
            this.input.value = before + text + ", ";
        }
    })
};


function sendDeleteRequest(url) {
    console.log('DELETE BOOk' + url);
    $.ajax({
        type: "DELETE",
        url: url,
        success: function () {
            location.reload();
        }
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


