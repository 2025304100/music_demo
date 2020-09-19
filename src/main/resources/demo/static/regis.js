$(function () {
    $('body').bootstrapMaterialDesign();

    var is = true;
    $("#vir_email").on("click", function () {
        var ddm = false;
        var form = new FormData(document.getElementById("form_user"));
        if (form.get("email") == "") {
            document.getElementsByClassName("mms")[2].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(2)").text("请输入邮箱");
        } else {
            var z = /^[\w.\-]+@(?:[a-z0-9]+(?:-[a-z0-9]+)*\.)+[a-z]{2,3}$/
            if (!(z.test(form.get("email")))) {
                document.getElementsByClassName("mms")[2].setAttribute("class", "mms v-loact");
                $(".text_demo:eq(2)").text("邮箱输入不合法");
                is = false;
                return;
            }

            $.post("/email", {"email": $("#email").val()},
                function (data) {
                    if (data == "发送失败!") {
                        document.getElementsByClassName("mms1")[0].setAttribute("class", "mms1 v-loact");
                        $(".text_demol").text("发送失败");
                        is = false;
                    } else if (data == "邮箱以绑定") {
                        document.getElementsByClassName("mms1")[0].setAttribute("class", "mms1 v-loact");
                        $(".text_demol").text(data);
                        ddm = true;
                    } else {
                        $(".mms1").css("background-color", "rgba(180, 255, 186, 0.98)")
                        document.getElementsByClassName("mms1")[0].setAttribute("class", "mms1 v-loact");
                        $(".text_demol").text("发送成功");
                        is = true;
                    }

                    if (ddm)
                        return;
                }
            ).error(function (error) {
                document.getElementsByClassName("mms1")[0].setAttribute("class", "mms1 v-loact");
                $(".text_demol").text("发送失败");
                is = false;
                console.log(error);
            })
            var i = 60;
            let tim = window.setInterval(function () {

                $("#vir_email").text("重新发送(" + (--i) + "s)");

                if (i == 0) {
                    $("#vir_email").text("发送");
                    $("#vir_email").removeAttr("disabled")
                    window.clearInterval(tim);

                } else
                    $("#vir_email").attr("disabled", "false");
            }, 1000)

        }
    })

    $("#phon").blur(function () {
        if (!(/^1[3456789]\d{9}$/.test($("#phon").val()))) {
            document.getElementsByClassName("mms")[5].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(5)").text("手机号输入错误");
            is = false;
        } else {
            is = true;
        }
    })
    var pas = false;
    $("#password").blur(function () {
        if ($(this).val().length < 10) {
            document.getElementsByClassName("mms")[3].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(3)").text("密码长度要大于10");
            is = false;
            pas = true;
            return;
        } else
            pas = false;
    })
    $("#password-confirm").blur(function () {
        if (pas) {
            document.getElementsByClassName("mms")[3].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(3)").text("密码长度要大于10");
            is = false;
        }
        if (($("#password-confirm").val()) != ($("#password").val())) {
            document.getElementsByClassName("mms")[4].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(4)").text("密码不匹配");
            is = false;
        } else {
            is = true;
        }
    })
    $("#username").blur(function () {
        if ($(this).val().length < 8) {
            document.getElementsByClassName("mms")[0].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(0)").text("账号长度要大于8");
            is = false;
            return;
        }

        $.post("/user/us",
            {"username": $("#username").val()},
            function (data) {

                if (data == 0) {
                    $(".mms:eq(0)").css("background-color", "rgba(180, 255, 186, 0.98)");
                    document.getElementsByClassName("mms")[0].setAttribute("class", "mms v-loact");
                    $(".text_demo:eq(0)").text("账号有效");
                    is = true;
                    setTimeout(function () {
                        demo_fou(0);
                    }, 5000);
                } else if (data == -1) {
                    document.getElementsByClassName("mms")[0].setAttribute("class", "mms v-loact");
                    $(".text_demo:eq(0)").text("账号已被注册");
                    is = false;
                }

            }
        ).error(function () {
            document.getElementsByClassName("mms")[0].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(0)").text("账号验证失败!");
        })

    })

    $("#but").click(function () {
        let form = new FormData(document.getElementById("form_user"));
        if (!(is = !(form.get("username") == ""))) {
            document.getElementsByClassName("mms")[0].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(0)").text("账号未填");
        }
        if (!(is = !(form.get("name") == ""))) {
            document.getElementsByClassName("mms")[1].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(1)").text("昵称未填");
        }
        if (!(is = !(form.get("email") == ""))) {
            document.getElementsByClassName("mms")[2].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(2)").text("请输入邮箱");
        } else if (form.get("email_verification") == "") {
            document.getElementsByClassName("mms1")[0].setAttribute("class", "mms v-loact");
            $(".text_demol").text("请输入验证码");
            is = false;
        }
        if (!(is = !(form.get("pas") == ""))) {
            document.getElementsByClassName("mms")[3].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(3)").text("请输入密码");
        }
        if (!(is = !(form.get("phon") == ""))) {
            document.getElementsByClassName("mms")[5].setAttribute("class", "mms v-loact");
            $(".text_demo:eq(5)").text("请输入手机号");

        }
        if (is) {
            $.ajax({
                contentType: "application/x-www-form-urlencoded",
                type: "POST",
                url: "/user/register",
                data: form,
                processData: false,//默认utf-8编码
                contentType: false,
                success: function (data) {
                    if (data == "注册成功!") {
                        $(".win").css("background-color", "rgba(180, 255, 186, 0.98)");
                        setTimeout(function () {
                            window.location.href = '/user/login';
                        }, 6000);
                    } else
                        $(".win").css("background-color", "red");
                    $(".win_text").html(data);
                    $(".win").addClass("win_bu");

                },
                error: function () {
                    $(".win").css("background-color", "red");
                    $(".win_text").html("注册失败,请检查网络!");
                    $(".win").addClass(" win_bu");
                }
            });
        }
        setTimeout(function () {
            $(".win").attr("class", "win")
        }, 2000)
    })
});

function demo_fou(bt) {
    document.getElementsByClassName("mms")[bt].setAttribute("class", "mms");
}

function demo(bt) {
    document.getElementsByClassName("mms1")[bt].setAttribute("class", "mms1");
}