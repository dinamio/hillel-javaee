$(document).ready(updateMessages());


function updateMessages() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/blog',
        dataType: 'json',
        async: true,
        success: function (data) {
            let container = $("#blog_container");
            let message_data = '';
            let ids = new Map();
            $.each(data, function (key, value) {
                let deleteURL = "http://localhost:8080/blog?id=" + value.id;
                let author = value.author;
                let date = new Date(value.time);
                let idM = "panel" + value.id;
                message_data += "<div class=\"panel panel-default\">";
                message_data += "<div class=\"panel-heading\">";
                message_data += "<div class=\"panel-title\">";
                message_data += "<h3>";
                message_data += value.title;
                message_data += "<div class='btn btn-default pull-right' onclick='sendDeleteRequest(" + "\"" + deleteURL + "\"" + ")'>";
                message_data += "Delete";
                message_data += "</div>";
                message_data += "</h3>";
                message_data += "</div>";
                message_data += "</div>";
                message_data += "<div class='message_body_panel'  id= " + idM + " >";
                message_data += "</div>";
                message_data += "<div class=\"panel-footer\">";
                message_data += "<h6>";
                message_data += "Posted by: " + author.name + ", date: " + date.toLocaleString();
                message_data += "</h6>";
                message_data += "</div>";
                message_data += "</div>";
                ids.set("#" + idM, value.body);
            });
            container.empty();
            container.append(message_data);

            for (let [key, value] of ids) {
                const q = new Quill(key, {
                    readOnly: true,
                    strict: false
                });
                if (value.startsWith("\"<")) {
                    q.root.innerHTML = value.substring(1, value.length - 1);
                } else {
                    q.root.innerHTML = value;
                }
            }
            container.slideDown();
        }
    });
}

function sendDeleteRequest(url) {
    $.ajax({
        type: "DELETE",
        url: url,
        success: function () {
            updateMessages();
        }
    });
}


let quill = new Quill('#message_body', {
    placeholder: 'The best message...',
    theme: 'snow'
});

function sendMessage() {
    let titleText = document.getElementById('title').value;
    let author = document.getElementById('author').value;
    let body = JSON.stringify(quill.root.innerHTML);
    if (titleText.length > 0 && author.length > 0) {
        if (quill.getLength() <= 1) {
            body = "Empty message O_o";
        }
        sendMessageToServer(titleText, body, author);
        cleanForm();
    }
}


function cleanForm() {
    quill.root.innerHTML = '';
    document.getElementById('title').value = '';
    document.getElementById('author').value = '';
    $("#message_form").fadeOut();
    // document.getElementById('message_form').style.display = 'none';

}

function sendMessageToServer(title, body, author) {
    let arr = {title: title, body: body, author: author};
    $.ajax({
        url: 'http://localhost:8080/blog',
        type: "POST",
        data: JSON.stringify(arr),
        contentType: 'application/json; charset=utf-8',
        async: false,
        success: function () {
            updateMessages();
        }
    });
}

$('#top').click(function () {
    $("html, body").animate({scrollTop: 0});
    return false;
});

$.each(document.getElementsByClassName("navbar-brand"), function (key, value) {
    value.style.cursor = "pointer";
});
