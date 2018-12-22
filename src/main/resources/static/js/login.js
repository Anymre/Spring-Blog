function login() {
    $.post("/login",
        {
            username: $("#username").val(),
            password: $("#password").val(),
        },
        function (res) {
            alert(res.state);
        });
}
