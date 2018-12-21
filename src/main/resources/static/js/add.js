function confirm() {
    event.preventDefault();
    $.post("http://127.0.0.1:8080/message/add",
        {
            Id: $("#id").val(),
            Title: $("#title").val(),
            Context:$("#context").val(),
        },
        function (data) {
        alert("add success！")
        });
}
