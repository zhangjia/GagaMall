<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>最家</title>
		<jsp:include page="public-static-file.jsp" />
		<link rel="stylesheet" type="text/css" href="${path}/static/css/mygxin.css" />
		<script src="${path}/static/js/user.js" type="text/javascript" charset="utf-8"></script>

		<link href="${path}/static/address/css/main.css" rel="stylesheet" type="text/css" />

		<link href="${path}/static/address/css/city-picker.css" rel="stylesheet" type="text/css" />
		<script src="${path}/static/address/js/city-picker.data.js"></script>
		<script src="${path}/static/address/js/city-picker.js"></script>
		<script src="${path}/static/address/js/main.js"></script>

		<script>
			$(function () {

				$(".dizhi").hover(function(){
					var txt="";
					txt='<p class="addp"><a href="#"  id="readd">修改</a><a href="#" id="deladd">删除</a></p>'
					$(this).append(txt);
					$("#readd").click(function(){

						var addId = $(this).parent().siblings("p:hidden").text();
						<c:forEach items="${requestScope.userAddress}" var="address">

						if(${address.RECIPIENT_ADDRESS_ID }) {
						var divs = '<div class="readd">' +
								'<form action="#" method="get">' +
								'<input type="text"  class="on" value="${address.RECIPIENT_NAME}" />' +
								'<input type="text" value="${address.RECIPIENT_TEL}" />' +

								'<textarea name="" rows="" cols="" placeholder="详细地址">${address.RECIPIENT_DETAILED_ADDRESS}</textarea>' +
								'<input type="text" placeholder="邮政编码" value="${address.RECIPIENT_POSTCODE}"/>' +
								'<div class="bc">' +
								'<input type="button" value="保存" />' +
								'<input type="button" value="取消" />' +
								'</div>' +
								'</form>' +
								'</div>'


						$(".mask").append(divs);
						}

						</c:forEach>

						$(".mask").show();
						$(".readd").show();
					});
					$("#deladd").click(function(){
						$(this).parents(".dizhi").remove();
					});
				},function(){
					$(".bc>input").click(function(){
						if($(this).val()=="保存"){
							$(".mask").hide();
							$(".readd").hide();
						}else{
							$(".mask").hide();
							$(".readd").hide();
						}
					});
					$(".addp").remove();
				});


				//	---------------------------------------
				$(".bc>input").click(function(){
					if($(this).val()=="保存"){
						var data = {
							name:$("input[placeholder='姓名']").val(),
							postcode:$("input[placeholder='邮政编码']").val(),
							tel:$("input[placeholder='手机号']").val(),
							detailedAddress:$("input[placeholder='详细地址']").val(),
						};
						console.log(data);
						$.ajax({
							url:"${path}/addAddress",
							type:"get",
							data:data,
							success:function (res) {

								console.log(res)
							}
						});
						console.log(11)
						$(".mask").hide();
						$(".adddz").hide();
						$(".bj").hide();
						$(".xg").hide();
						$(".remima").hide();
						$(".pj").hide();
						$(".chak").hide();

					}else{
						$(".mask").hide();
						$(".adddz").hide();
						$(".bj").hide();
						$(".xg").hide();
						$(".remima").hide();
						$(".pj").hide();
						$(".chak").hide();
					}
				});
			});





		</script>

	</head>
	<body>
	<jsp:include page="head.jsp" />
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="mygxin.html">个人中心</a>
				<span>/</span>
				<a href="address.html" class="on">地址管理</a>
			</div>
		</div>
		
		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">

					<jsp:include page="personal-left.jsp" />
				</div>
				<div class="you fl">
					<h2>收货地址</h2>
					<div class="add">
						<%--显示添加地址--%>
						<div>
							<a href="#2" id="addxad"><img src="${path}/static/img/jia.png"/></a>
							<span>添加新地址</span>
						</div>

						<c:forEach items="${requestScope.userAddress}" var="address">
							<%--显示所有的地址--%>
							<div class="dizhi dizhis">
								<p>姓名：${address.RECIPIENT_NAME}</p>
								<p>手机：${address.RECIPIENT_TEL}</p>
								<p>地址：${address.RECIPIENT_DETAILED_ADDRESS}</p>
								<p>邮编：${address.RECIPIENT_POSTCODE}</p>
								<p hidden>${address.RECIPIENT_ADDRESS_ID}</p>
							</div>

						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!--编辑弹框-->
		<!--遮罩-->
		<div class="mask"></div>
	<c:forEach items="${requestScope.userAddress}" var="address">

				<div class="adddz">
					<form action="#" method="get">
						<input type="text" placeholder="姓名" class="on" />
						<input type="text" placeholder="手机号" />

						<div class="docs-methods">

							<div class="distpicker">
								<div class="form-group">
									<div style="position: relative;">
										<input class="form-control city-picker3" readonly type="text" value="" data-toggle="city-picker">
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-warning reset"  type="button">重置</button>
									<button class="btn btn-danger destroy"  type="button">确定</button>
								</div>
							</div>

						</div>
						<textarea name="" rows="" cols="" placeholder="详细地址"></textarea>
						<input type="text" placeholder="邮政编码" />
						<div class="bc">
							<input type="button" value="保存" class="submits" />
							<input type="button" value="取消" />
						</div>
					</form>
				</div>

		</c:forEach>

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
	</body>
</html>
