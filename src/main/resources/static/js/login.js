function login() {
    $.post("/login",
        {
            username: $("#username").val(),
            password: $("#password").val(),
        },
        function (res) {
        if(res.state=="Access"){
            window.location.replace("/message/index/6");
        }
            alert(res.state);
        });
}
