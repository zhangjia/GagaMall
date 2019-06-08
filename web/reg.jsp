<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册</title>
		<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
		<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
		<link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/public.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css"/>
	</head>
	<body>
		<!-------------------reg-------------------------->
		<div class="reg">
			<form   method="get">


				<h1><a href="index.html"><img src="${pageContext.request.contextPath}/static/img/temp/logo.png"></a></h1>

				<div  class="form-group input-group">

					<div class="input-group-prepend">
							<select class="input-group-text form-control" name="acc" id="sel1">
							<option value="userName">用户名</option>
							<option value="userTel">手机号</option>
							<option value="userMail">邮箱</option>
						</select>
					</div>

					<input class="form-control " type="text" name="account" value="" placeholder="用户名">
					<span></span>
				</div>

				<div  class="form-group ">

					<input class="form-control " type="text" name="userPassword" value="" placeholder="密码">
					<span></span>
				</div>

				<div  class="form-group ">
					<input class="form-control " type="text"  value="" placeholder="确认密码">
					<span></span>
				</div>
<%--				<p class="txtL txt"><input class="code" type="text" name="" value="" placeholder="验证码"><img src="${pageContext.request.contextPath}/static/img/temp/code.jpg"></p>--%>
				<input class="btn btn-primary btn-lg btn-block submit" type="submit"  value="注  册">
				<p class="txtL txt">完成此注册，即表明您同意了我们的<a href="#">使用条款和隐私策略</a></p>
				<p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
				<!--<a href="#" class="off"><img src="img/temp/off.png"></a>-->

			</form>
		</div>

	</body>
</html>
