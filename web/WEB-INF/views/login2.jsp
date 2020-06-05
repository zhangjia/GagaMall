<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>

    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${path}/static/layui/css/layui.css"/>
    <script src="${path}/static/layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <%--		<link rel="stylesheet" href="${path}/static/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/login.css"/>
    <script src="https://cdn.bootcss.com/layer/2.3/layer.js"></script>
    <%--		<script type="text/javascript" src="${path}/static/js/login.js"></script>--%>
    <script type="text/javascript">
        $(function () {
            $(":submit").click(function () {
                //将表单序列化
                var data = $("form").serialize();
                //发起请求，完成登录
                $.ajax({
                    url: "${path}/login",
                    type: "post",
                    data: data,
                    success: function (res) {
                        console.log(res);
                        if (res.result) {
                            var c = layer.msg('登录成功', {time: 1000, anim: 2, icon: 6}, function () {

                                if (res.uri) {
                                    location = res.uri;
                                } else {
                                    location = "${path}/index";
                                }
                            });
                            console.log(c);
                            //成功，跳转到index.jsp


                        } else {
                            //失败
                            if (res.error === "密码错误") {
                                $("input[name='userPassword']").addClass("is-invalid");
                                $("input[name='userPassword']").next("span").addClass("text-danger").text(res.error);
                            } else if (res.error === "用户名不存在") {
                                $("input[name='account']").addClass("is-invalid");
                                $("input[name='account']").next("span").addClass("text-danger").text(res.error);
                            }
                        }
                    }
                });
                return false;
            });


            $("input[type=text]").focus(function () {

                $("input[type=text]").attr("class", "form-control");
                $("input[type=text]").next("span").hide();


            });

            $("input[type=text]").blur(function () {


                $("input[type=text]").next("span").show().text("");


            });
        });
    </script>
</head>
<body>

<!-------------------login-------------------------->
<div class="login">
    <form role="form" action="${path}/login" method="post">
        <h1><a href="index.jsp"><img src="${path}/static/img/temp/logo.png"></a></h1>

        <c:if test="${param.uri != null}">
            <input type="hidden" name="uri" value="${param.uri}">
        </c:if>
        <div class="form-group ">
            <input class="form-control " type="text" name="account" value="" placeholder="用户名/邮箱/手机号">
            <span></span>
        </div>
        <div class="form-group">
            <input class="form-control" type="password" name="userPassword" value="" placeholder="密码"><span></span>

        </div>
        <input class="btn btn-primary btn-lg btn-block submit" type="submit" value="登  录">
        <p class="txt"><a class="" href="register.jsp">免费注册</a><a href="forget.html">忘记密码？</a></p>
    </form>
</div>

</body>
</html>
