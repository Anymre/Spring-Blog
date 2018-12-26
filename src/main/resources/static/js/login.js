function login() {
    $.post("/login",
        {
            username: $("#username").val(),
            password: $("#password").val(),
        },
        function (res) {
        if(res.state=="Access"){
            window.location.replace("/index");
        }
            alert(res.state);
        });
}
