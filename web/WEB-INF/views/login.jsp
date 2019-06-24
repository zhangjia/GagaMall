<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<style>
			.layui-input-block {
				margin-left: 82px !important;
				min-height: 36px;
			}
			.jia-login{
				margin-left: 80px !important;
			}
			.jia-login-reset{
				margin-left: 145px !important;
			}

		</style>
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
				layui.use('form', function(){
					var form = layui.form;

					//各种基于事件的操作，下面会有进一步介绍
				});

				$(":submit").click(function () {
					//将表单序列化
					var data = $("form").serialize();
					//发起请求，完成登录
					console.log(data)
					$.ajax({
						url:"${path}/login",
						type:"post",
						data:data,
						success:function (res) {
							console.log(res);
							if(res.result){
								var c = layer.msg('登录成功',{time:1000,anim: 2,icon: 6},function () {

									if(res.uri){
										location = res.uri;
									}else{
										location = "${path}/index";
									}
								});
								console.log(c);
								//成功，跳转到index.jsp


							}else{
								//失败
								if(res.error === "密码错误"){
									layer.tips(res.error, 'input[name=\'userPassword',{
										tips: [1, '#FF5722'],
										anim: 6,
										time:800
									});

								}else if(res.error === "用户名不存在"){
									layer.tips(res.error, 'input[name=\'account',{
										tips: [1, '#FF5722'],
										anim: 6,
										time:800
									});
								}
							}
						}
					});
					return false;
				});


				$("input[type=text]").focus(function () {

					$("input[type=text]").attr("class","form-control");
					$("input[type=text]").next("span").hide();


				});

				$("input[type=text]").blur(function () {


					$("input[type=text]").next("span").show().text("");


				});
			});
		</script>
	</head>
	<body >

		<!-------------------login-------------------------->
		<div class="login">
			<form role="form" action="${path}/login" method="post">
				<h2 style="margin:30px -5px 30px 30px;color:#1e9fff">用户登录</h2>

				<c:if test="${param.uri != null}">
					<input type="hidden" name="uri" value="${param.uri}">
				</c:if>
				<div class="layui-form-item">
					<label class="layui-form-label">账户：</label>
					<div class="layui-input-block">
						<input type="text" name="account" placeholder="请输入用户名/手机号/邮箱" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码：</label>
					<div class="layui-input-block">
						<input type="password" name="userPassword" placeholder="请输入密码" autocomplete="off" class="layui-input">
					</div>
				</div>
<%--				<input class="btn btn-primary btn-lg btn-block submit" type="submit"  value="登  录">--%>
						<button class="layui-btn  layui-btn-normal jia-login" lay-submit lay-filter="formDemo" style="text-align: left">立即登录</button>
						<button type="reset" class="layui-btn  layui-btn-danger jia-login-reset">重置</button>
				<p style="margin-bottom: 20px" class="txt"><a class="" href="${path}/register">免费注册</a><a href="forget.html">忘记密码？</a></p>
				</div>
			</form>
		</div>

	</body>
</html>
