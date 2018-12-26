function confirm() {
    event.preventDefault();
    $.post("/user/reg",
        {
            phone: $("#username").val(),
            password: $("#password").val(),
            nickname: $("#nickname").val(),
            email: $("#email").val(),
            Code: $("#code").val(),
        },
        function (res) {
            if (res=="true") {
                alert("reg success！")
                window.location.href = "/index"
            } else if (res=="false") {
                var txt = "user already exist!";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
            } else {
                alert(res)
                SlideVerify()
            }
        });
}


// 点击获取验证码操作
function verify() {
    //发送验证码
    $.get("/user/code/" + $("#username").val(),
        function (data) {
            alert("get mss success！")
        });
    var count = 60;
    var countdown = setInterval(CountDown, 1000);

    function CountDown() {
        $("#getCode").attr("disabled", true);
        $("#getCode").text("请等待" + count + "秒");
        if (count == 0) {
            $("#getCode").text("重新获取").removeAttr("disabled");
            clearInterval(countdown);
        }
        count--;
    }
}

function SlideVerify() {
    $("#mpanel1").empty();
    $("button[name='submit']").attr("disabled", true);
    $('#mpanel1').slideVerify({
        //滑动验证码type=1，拼图验证码type=2
        type: 2,

        //拼图验证码或选择验证码图片名称
        imgName: ['1.jpg'],

        //拼图验证码的图片尺寸
        imgSize: {
            width: '400px',
            height: '200px',
        },
        success: function () {
            $("button[name='submit']").removeAttr("disabled");
        }

    });
}



