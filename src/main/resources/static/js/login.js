function confirm() {
    event.preventDefault();
    $.post("http://" + window.location.host + "/user/login",
        {
            phone: $("#username").val(),
            password: $("#password").val(),
        },
        function (data) {
            if (data == 'Access') {
                window.location.href = "http://127.0.0.1:8080/message/1"
            } else {
                alert(data);
            }
        });
}
