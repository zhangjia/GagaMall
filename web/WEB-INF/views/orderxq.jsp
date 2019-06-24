<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="order" value="${requestScope.order}"/>
<!DOCTYPE html>
<html>
<style>
	.orderList1 p{
		margin-bottom: 20px;
	}
</style>
	<head lang="en">
		<meta charset="utf-8" />
		<title>个人信息</title>
		<jsp:include page="public-static-file.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/myorder.css" />
	</head>
	<body>
	<jsp:include page="head.jsp"></jsp:include>

		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a href="index.html" class="fl">首页</a>
				<span>/</span>
				<a href="myorderq.html" class="on">我的订单</a>
				<span>/</span>
				<a href="#" class="on">订单详情</a>
			</div>
		</div>

		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">

					<jsp:include page="personal-left.jsp" />
				</div>
				<div class="you fl">
					<div class="my clearfix">
						<h2 style="margin-bottom: 10px ">订单详情<%--<a href="#">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a>--%></h2>

					</div>
					<div class="orderList">
						<div class="orderList1">
							<div class="jia-h3">
							<h3 style="margin-top: 20px ">订单号：<span>${order.ORDER_ID}</span></h3>
							<c:if test="${order.ORDER_STATUS == 1}">
								<h3>待发货</h3>
							</c:if>
							<c:if test="${order.ORDER_STATUS == 2}">
								<h3>已发货</h3>
							</c:if>
							<c:if test="${order.ORDER_STATUS == 3}">
								<h3>待评价</h3>
							</c:if>
							<c:if test="${order.ORDER_STATUS == 4}">
								<h3>待支付</h3>
							</c:if>
							<c:if test="${order.ORDER_STATUS == 5}">
								<h3>已关闭</h3>
							</c:if>
								<h3>物流：${order.ORDER_LOGISTICS}</h3>
							</div>
							<c:forEach items="${order.COMMODITIES}" var="commodity" >
							<div class="clearfix">
								<a href="#" class="fl"><img class="spxqtsmall" src="${commodity.ORDER_DETAILS_COMMODITY_IMG}"/></a>
								<p class="settlement-ellipsis-order-detail">
                                    <a href="#">${commodity.ORDER_DETAILS_COMMODITY_NAME}</a>
                                    <a href="#">¥${commodity.ORDER_DETAILS_COMMODITY_PRICE}×${commodity.ORDER_DETAILS_COMMODITY_COUNT} </a>

                                 </p>
                                <span class="sku-value">${commodity.ORDER_DETAILS_SKU_VALUE}</span>

							</div>
							</c:forEach>

						</div>
						<div class="orderList1">
							<h3>收货信息</h3>
							<p>姓 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${order.ADDRESS.ADDRESS_NAME}</span></p>
							<p>联系电话：<span>${order.ADDRESS.ADDRESS_TEL}</span></p>
							<p>收货地址：<span>${order.ADDRESS.ADDRESS_DETAIL}</span></p>
						</div>
						<div class="orderList1">
							<h3>支付方式/备注</h3>
							<p>支付方式：<span>${order.ORDER_PAY_TYPE}</span></p>
							<p>备注：<span>${order.ORDER_NOTE}</span></p>
						</div>
						<div class="orderList1 hei">
							<h3><strong>商品原价：</strong><span>${order.ORIGINALPRICE}</span></h3>
							<p>运费：<span>¥${order.ORDER_FREIGHT_PRICE}</span></p>
							<p>优惠：<span>¥${order.DISCOUNTPRICE}</span></p>

							<p>实付金额：<span>¥${order.ORDERPRICE}</span></p>
						</div>

					</div>


				</div>
			</div>
		</div>
		<

	<jsp:include page="right-sidebar.jsp"></jsp:include>
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
