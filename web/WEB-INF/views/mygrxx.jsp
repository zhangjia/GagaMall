<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>个人信息</title>
		<jsp:include page="public-static-file.jsp" />
		<link rel="stylesheet" type="text/css" href="${path}/static/layui/css/layui.css"/>
		<script src="${path}/static/layui/layui.js " type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/mygrxx.css" />
		<script>
			$(function () {
				//取消修改
				$(".jia-btn-cancle").click(function(){
					$(".mask").hide();
					$(".bj").hide();
					$(".xg").hide();
					$(".remima").hide();
					$(".avatar").hide();
					$(".pj").hide();
					$(".chak").hide();
				});
				$(".jia-user-information-save").click(function(){
					$(".mask").hide();
					$(".bj").hide();
					$(".xg").hide();
					$(".remima").hide();
					$(".avatar").hide();
					$(".pj").hide();
					$(".chak").hide();
				});


				$(".jia-div-code").hide()
				$("input[name='tel']").keyup(function () {
					$(".jia-div-code").show()
				});


/*-----------------------------------更改个人信息开始-------------------------------------------------*/


/*-----------------------------------更改个人信息结束-------------------------------------------------*/

			})
		</script>
	</head>
	<body>
	<jsp:include page="head.jsp" />
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html" class="on">个人信息</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">

					<jsp:include page="personal-left.jsp"/>
				</div>
				<div class="you fl">
					<h2>个人信息</h2>
					<div class="gxin">
						<div class="tx"><a href="#"><img class="jia-userAvatar" src="${sessionScope.userInformation.imgUrl}"/><p id="avatar">修改头像</p></a></div>
						<div class="xx">
							<h3 class="clearfix"><strong class="fl">基础资料</strong><a href="#" class="fr" id="edit1">编辑</a></h3>
							<div>用户名：${sessionScope.userInformation.userName}</div>
							<div>昵  称：${sessionScope.userInformation.userNick}</div>
							<div>性  别：${sessionScope.userInformation.userGender}</div>
							<div>等  级：<fmt:formatNumber
									value="${sessionScope.userInformation.userGrade /100}"
									type="NUMBER"/></div>
							<div>生  日：<fmt:formatDate value="${sessionScope.userInformation.userBirthday}" type="DATE" /></div>
							<div>手机号：${sessionScope.userInformation.userTel}</div>
							<div>邮  箱：${sessionScope.userInformation.userMail}</div>
							<div>注册时间：<fmt:formatDate value="${sessionScope.userInformation.userRegisterTime}" type="both" /></div>
							<h3>高级设置</h3>
							<!--<div><span class="fl">银行卡</span><a href="#" class="fr">管理</a></div>-->
							<div><span class="fl">登录密码</span><a href="#" class="fr" id="edit2">修改</a></div>
							<div><span class="fl">支付密码</span><a href="#" class="fr" id="edit2">修改</a></div>
						</div>
					</div>			
				</div>
			</div>
		</div>
		<!--遮罩-->
		<div class="mask"></div>
		<!--编辑弹框-->
		<div class="bj">
<%--			<div class="clearfix"><a href="#" class="fr gb"><img src="${path}/static/img/icon4.png"/></a></div>--%>
			<h3 style="margin: 10px">编辑基础资料</h3>
	<hr>
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">昵称</label>
					<div class="layui-input-block">
						<input type="text" name="nickname" required  lay-verify="required" placeholder="${sessionScope.userInformation.userNick}" autocomplete="off" class="layui-input">
					</div>
				</div>


				<div class="layui-form-item">
					<label class="layui-form-label">生日</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input" id="jia-user-birthday"
placeholder="<fmt:formatDate value="${sessionScope.userInformation.userBirthday}" type="DATE" />">
						<input type="hidden" class="jia-select-birthday" value="" name="birthday">
					</div>
				</div>
				<script>
					layui.use('laydate', function(){
						var laydate = layui.laydate;

						//执行一个laydate实例
						laydate.render({
							elem: '#jia-user-birthday' ,//指定元素
							position:'fixed',
							value: '2019-06-25',
							isInitValue: false //是否允许填充初始值，默认为 true
							,done: function(value, date, endDate){
								$('.jia-select-birthday').val(value)
								console.log(value); //得到日期生成的值，如：2017-08-18
								console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
								console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
							}
						});

					});
				</script>
				<div class="layui-form-item">
					<label class="layui-form-label">单选框</label>
					<div class="layui-input-block">
						<input type="radio" name="sex" value="男" title="男">
						<input type="radio" name="sex" value="女" title="女" checked>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn jia-user-information-save" lay-submit lay-filter="formDemo" lay-filter="jia-edit-user-information">保存</button>
						<button type="button" class="layui-btn layui-btn-primary jia-btn-cancle">取消</button>
					</div>
				</div>
			</form>

			<script>
				//Demo
				layui.use('form', function(){
					var form = layui.form;

					//监听提交
					form.on('submit(formDemo)', function(data){

						console.log(data.field)
						$.ajax({
							url:"${path}/editUserInformation",
							type:"get",
							data:{
								nickname:data.field.nickname,
								gender:data.field.sex,
								birthday:data.field.birthday
							},
							success:function (res) {
								if(res.success === true){
									layer.msg("修改成功")
									location.reload();
								} else {
									layer.msg("修改失败")
								}

							}



						})
						console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
						return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
					});
				});
			</script>
		</div>
		<!--高级设置修改-->
		<div class="xg">
			<div class="clearfix"><a href="#" class="fr gb"><img src="${path}/static/img/icon4.png"/></a></div>
			<h3>切换账号地区</h3>
			<form action="#" method="get">
				<p><label>姓名：</label><input type="text"  value="六六六" /></p>
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--修改头像-->
		<div class="avatar">
			<div class="clearfix"><a href="#" class="fr gb"><img src="${path}/static/img/icon4.png"/></a></div>
			<h3>修改头像</h3>
			<form action="#" method="get">
				<h4>请上传图片</h4>
				<input type="button" value="上传头像" />
				<p>jpg或png，大小不超过2M</p>
				<input type="submit" value="提交" />
			</form>
		</div>
		
		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
			<dl>
				<dt><img src="${path}/static/img/gt1.png"/></dt>
				<dd>去购<br />物车</dd>
			</dl>
			</a>
			<a href="#" class="dh">
			<dl>
				<dt><img src="${path}/static/img/gt2.png"/></dt>
				<dd>联系<br />客服</dd>
			</dl>
			</a>
			<a href="mygxin.html">
			<dl>
				<dt><img src="${path}/static/img/gt3.png"/></dt>
				<dd>个人<br />中心</dd>
			</dl>
			</a>
			<a href="#" class="toptop" style="display: none">
			<dl>
				<dt><img src="${path}/static/img/gt4.png"/></dt>
				<dd>返回<br />顶部</dd>
			</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		<!--footer-->
		<div class="footer">
			<div class="top">
				<div class="wrapper">
					<div class="clearfix">
						<a href="#2" class="fl"><img src="${path}/static/img/foot1.png"/></a>
						<span class="fl">7天无理由退货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="${path}/static/img/foot2.png"/></a>
						<span class="fl">15天免费换货</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="${path}/static/img/foot3.png"/></a>
						<span class="fl">满599包邮</span>
					</div>
					<div class="clearfix">
						<a href="#2" class="fl"><img src="${path}/static/img/foot4.png"/></a>
						<span class="fl">手机特色服务</span>
					</div>
				</div>
			</div>
			<p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br />
			违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		<script src="${path}/static/js/public.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/user.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
