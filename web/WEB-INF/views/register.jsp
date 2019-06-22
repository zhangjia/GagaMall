<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <style>
        .help-block {
            margin: 5px;
            color: red;
        }

        .jia-acc input {
            width: 260px;
        }

        .jia-register-code input {
            width: 230px;
            display: inline;
        }

        .jia-register-get-code {
            width: 101px;
        }


    </style>

    <%------------------------------------各种资源引入-----------------------------------%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/layer/2.3/layer.js"></script>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/login.css"/>
    <script src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
    <link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"
          rel="stylesheet">
    <%------------------------------------资源引入结束-----------------------------------%>
    <script>
        function IsphoneCodeRight() {
            var tel = $("input[name='userTel']").val();
            var inputCode = $(".jia-register-input-code").val();
            if (inputCode == null || inputCode == '') {
                layer.msg("请输入验证码");
                return false;
            }
            if (tel == null || inputCode == '') {
                layer.msg("请输入手机号");
                return false;
            }
            var userInputPhoneCode = $("input[name='verificationCode']").val();
            console.log("ID = " + ${sessionScope.phoneCode});
            if (userInputPhoneCode == ${sessionScope.phoneCode}) {
                return true
            } else {
                return false;
            }


        }
        $(function () {

            var index;



            $(":submit").click(function () {

                IsphoneCodeRight();
                //将表单序列化
                var data = $("form").serialize();
                data.put("action","userTel");
                //发起请求，完成登录
                $.ajax({
                    url: "${path}/register",
                    type: "post",
                    data: data,
                    success: function (res) {


                        if (res.result) {
                            //成功，跳转到index.jsp
                            var c = layer.msg('注册成功', {time: 1000, anim: 2, icon: 6}, function () {
                                if (res.uri) {
                                    location = res.uri;
                                } else {
                                    location = "index.jsp";
                                }
                            })


                        } else {
                            //失败
                            if (res.error === "注册失败") {
                                $("input[name='userPassword']").addClass("is-invalid");
                                $("input[name='userPassword']").next("span").addClass("text-danger").text(res.error);
                            } else if (res.error === "用户名已存在") {
                                index = layer.tips('用户名已存在', $("input[name='account']"), {
                                    time: 0
                                });
                                $("input[name='account']").addClass("is-invalid");
                                // $("input[name='account']").next("span").addClass("text-danger").text(res.error);
                            }
                        }
                    }
                });
                return false;
            });


            $("input[type=text]").focus(function () {

                $("input[type=text]").attr("class", "form-control");
                $("input[type=text]").next("span").hide();
                layer.close(index);


            });

            $("input[type=text]").blur(function () {


                $("input[type=text]").next("span").show().text("");


            });

            /*---------------------------------表单验证开始------------------------------------*/
            $('form').bootstrapValidator({
                fields: {
                    //验证手机
                    userTel: {          //input中的name 值
                        validators: {
                            notEmpty: {
                                message: '请输入手机号'
                            },
                            regexp: {
                                regexp: /^1\d{10}$/,
                                message: '请输入正确的11位手机号'
                            }

                        }
                    },

                    //验证邮箱
                    userEmail: {
                        validators: {
                            notEmpty: {
                                message: '请输入邮箱'
                            },
                            regexp: {
                                regexp: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
                                message: '请输入正确的邮箱'
                            }

                        }
                    },
                    userPassword: {
                        validators: {
                            notEmpty: {
                                message: '请输入密码'
                            },
                            identical: {
                                field: 'password2',
                                message: '两次输入的密码不相符'
                            }
                        }
                    },
                    userPassword2: {
                        validators: {
                            notEmpty: {
                                message: '请再次输入密码'
                            },
                            identical: {
                                field: 'userPassword',
                                message: '两次输入的密码不相符'
                            }
                        }
                    },
                    userName: {
                        validators: {
                            stringLength: {
                                max: 4,
                                message: '不超过4个字符'
                            },
                            notEmpty: {
                                message: '用户名不能为空'
                            },

                        }
                    },
                }

            });
            /*---------------------------------表单验证结束------------------------------------*/
            /*---------------------------------选择用户名开始------------------------------------*/
            $("input[name='userEmail']").hide();
            $(".jia-register-get-code").hide();
            $("input[name='verificationCode']").hide();
            $("input[name='userTel']").hide();

            $("select").change(function () {
                $("small[data-bv-result='INVALID']").attr("style", 'display: none');

                var value = $(":selected").val();
                if (value === 'userName') {
                    $(".jia-register-get-code").hide();
                    $("input[name='userEmail']").hide();
                    $("input[name='verificationCode']").hide();
                    $("input[name='userTel']").hide();
                    $("input[name='userName']").attr("name", value);
                    $("input[name='userName']").show()

                }
                if (value === 'userTel') {

                    $("input[name='userName']").hide();
                    $("input[name='userEmail']").hide();
                    $("input[name='userTel']").attr("name", value);
                    $("input[name='verificationCode']").show();
                    $("input[name='userTel']").show();
                    $(".jia-register-get-code").show();
                }
                if (value === 'userEmail') {
                    $("input[name='userName']").hide();
                    $("input[name='userTel']").hide();
                    $("input[name='userEmail']").attr("name", value);
                    $("input[name='verificationCode']").show();
                    $("input[name='userEmail']").show()
                    $(".jia-register-get-code").show();
                }
            });
            /*---------------------------------选择用户名结束------------------------------------*/
            /*---------------------------------获取验证码开始------------------------------------*/
            $(".jia-register-get-code").click(function () {
                countDown();
                var tel = $("input[name='userTel']").val();
                console.log("tel" + tel);
                $.ajax({
                    url: "${path}/getPhoneCode",
                    type: "get",
                    data: {
                        tel: tel
                    },
                    success: function (res) {
                        console.log(res)
                        if (res.success === true) {
                            layer.msg("发送成功")
                        }


                    }
                });
            });

            function countDown() {
                $(this).addClass("on");
                var time = 5;
                $(this).attr("disabled", true);
                var timer = setInterval(function () {
                    if (time == 0) {
                        clearInterval(timer);
                        $(".jia-register-get-code").attr("disabled", false);
                        $(".jia-register-get-code").text("获取验证码");
                        $(".jia-register-get-code").removeClass("on");
                    } else {
                        $('.jia-register-get-code').text(time + "     秒");
                        time--;
                    }
                }, 1000);
            }


            /*---------------------------------获取验证码结束------------------------------------*/

        });
    </script>

</head>


<body>
<!-------------------reg-------------------------->
<div class="reg">
    <form method="post">


        <h1><a href="index.html"><img src="${path}/static/img/temp/logo.png"></a></h1>

        <div class="form-group input-group">

            <div class="input-group-prepend">
                <select class="input-group-text form-control jia-register-select-account" name="acc" id="sel1">
                    <option value="userName">用户名</option>
                    <option value="userTel">手机号</option>
                    <option value="userEmail">邮箱</option>
                </select>
            </div>
            <div class="jia-acc">

                <input class="form-control jia-register-user-name" id="jia-register-username" type="text"
                       name="userName" value="" placeholder="用户名">
                <input class="form-control jia-register-user-tel" type="text" name="userTel" value="" placeholder="手机号">
                <input class="form-control jia-register-user-email" type="text" name="userEmail" value=""
                       placeholder="邮箱">
            </div>

        </div>

        <div class="form-group ">

            <input class="form-control " type="text" name="userPassword" value="" placeholder="密码">
        </div>

        <div class="form-group ">
            <input name="userPassword2" class="form-control " type="text" value="" placeholder="确认密码">
        </div>
        <div class="form-group jia-register-code">

            <input class="form-control jia-register-input-code" type="text" name="verificationCode" value=""
                   placeholder="验证码">
            <button type="button" class="btn btn-primary btn-lg jia-register-get-code ">获取验证码</button>
        </div>
        <%--				<p class="txtL txt"><input class="code" type="text" name="" value="" placeholder="验证码"><img src="${path}/static/img/temp/code.jpg"></p>--%>
        <input class="btn btn-primary btn-lg btn-block submit" type="submit" value="注  册">
        <p class="txtL txt">完成此注册，即表明您同意了我们的<a href="#">使用条款和隐私策略</a></p>
        <p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>-->

    </form>
</div>

</body>
</html>
