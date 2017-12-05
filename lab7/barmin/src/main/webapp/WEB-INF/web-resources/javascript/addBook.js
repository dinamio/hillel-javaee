var index = 1;
function addField() {
    index++;
    $("<div>").appendTo("#addBookInput")
        .append($("<br>"))
        .append($("<label><b>Author "+index.toString()+": </b></label>)"))
        .append($("<input type=\"text\" class=\"form-control\" placeholder=\"Enter name of the author\" " + "name=\"bauthorName\" required>"))
        .append($("<input type=\"text\" class=\"form-control\" placeholder=\"Enter surname of the author\" name=\"bauthorSurname\" required>"));
    console.log(index)
}