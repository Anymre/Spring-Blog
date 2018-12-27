function confirm() {
    event.preventDefault();
    $.post("http://" + window.location.host + "/user/change",
        {
            Phone: $("#username").val(),
            Email:$("#email").val(),
            PassWord: $("#password").val(),
        },
        function (data) {
            if (data == 'Access') {
                alert("Success,you can login with your new password!")
                window.location.href = "http://127.0.0.1:8080/user/login"
            } else {
                alert(data);
            }
        });
}
