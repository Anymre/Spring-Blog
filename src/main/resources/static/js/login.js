function login() {
    $.post("/login",
        {
            username: $("#username").val(),
            password: $("#password").val(),
        },
        function (res) {
        if(res.state=="Access"){
            window.location.replace("/message/all");
        }
            alert(res.state);
        });
}
