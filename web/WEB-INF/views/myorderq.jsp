<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>最家</title>
    <jsp:include page="public-static-file.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/myorder.css"/>
</head>
<body>
<!------------------------------head------------------------------>
<jsp:include page="head.jsp"></jsp:include>
<!------------------------------idea------------------------------>
<div class="address mt">
    <div class="wrapper clearfix">
        <a href="#" class="fl">首页</a>
        <span>/</span>
        <a href="mygxin.html">个人中心</a>
        <span>/</span>
        <a href="myorderq.html" class="on">我的订单</a>
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
                <h2 class="fl">我的订单</h2>
                <a href="#" class="fl">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a>
            </div>
            <div class="dlist clearfix">
                <ul class="fl clearfix" id="wa">
                    <li class="on"><a href="#2">全部有效订单</a></li>
                    <li><a href="#2">待支付</a></li>
                    <li><a href="#2l">待收货</a></li>
                    <li><a href="#2">已关闭</a></li>
                </ul>
                <form action="#" method="get" class="fr clearfix">
                    <input type="text" name="" id="id1" value="" placeholder="请输入商品名称、订单号"/>
                    <input type="button" name="" id="id2" value=""/>
                </form>
            </div>
          <c:forEach items="${requestScope.orders}" var="order" varStatus="i">
              <div class="dkuang">
                  <p class="one"></p>
                  <div class="word clearfix">
                      <ul class="fl clearfix">
                          <li><fmt:formatDate value="${order.ORDER_TIME}" type="both" /></li>
                          <li>${order.ADDRESS.ADDRESS_NAME}</li>
                          <li>订单号:${order.ORDER_ID}</li>
                          <li>${order.ORDER_PAY_TYPE}</li>
                      </ul>
                      <p class="fr">订单金额：<span>${order.ORDERPRICE}</span>元</p>

                  </div>
                  <c:forEach items="${order.COMMODITIES}" var="commodity" >
                      <div class="shohou clearfix">
                          <a href="#" class="fl"><img src="${commodity.ORDER_DETAILS_COMMODITY_IMG}"/></a>
                          <p class="fl"><a href="#">${commodity.ORDER_DETAILS_COMMODITY_NAME}</a>
                              <a href="#">
                      ${fn:replace(fn:replace(fn:replace(fn:replace(commodity.ORDER_DETAILS_SKU_VALUE, '"', ''), '{', ''), '}', ''), ',', '    ')}
                              </a>

                              <a href="#">¥${commodity.ORDER_DETAILS_COMMODITY_PRICE}×${commodity.ORDER_DETAILS_COMMODITY_COUNT}</a></p>

                          <p class="fr">
                              <c:if test="${commodity.ORDER_DETAILS_STATUS == 1}">
                                  <a href="myprod.html">待发货</a>
                              </c:if>
                              <c:if test="${commodity.ORDER_DETAILS_STATUS == 2}">
                                  <a href="myprod.html">已发货</a>
                              </c:if>
                              <c:if test="${commodity.ORDER_DETAILS_STATUS == 3}">
                                  <a href="myprod.html">待评价</a>
                              </c:if>
                              <c:if test="${commodity.ORDER_DETAILS_STATUS == 0}">
                                  <a href="myprod.html">已关闭</a>
                              </c:if>
                              <a href="${path}/orderDetails?orderId=${order.ORDER_ID}">订单详情</a>
                          </p>
                      </div>
                  </c:forEach>

              </div>
          </c:forEach>

            <div class="fenye clearfix">
                <a href="#"><img src="${path}/static/img/zuo.jpg"/></a>
                <a href="#">1</a>
                <a href="#"><img src="${path}/static/img/you.jpg"/></a>
            </div>
        </div>
    </div>
</div>
<!--返回顶部-->
<div class="gotop">
    <a href="cart.html">
        <dl>
            <dt><img src="${path}/static/img/gt1.png"/></dt>
            <dd>去购<br/>物车</dd>
        </dl>
    </a>
    <a href="#" class="dh">
        <dl>
            <dt><img src="${path}/static/img/gt2.png"/></dt>
            <dd>联系<br/>客服</dd>
        </dl>
    </a>
    <a href="mygxin.html">
        <dl>
            <dt><img src="${path}/static/img/gt3.png"/></dt>
            <dd>个人<br/>中心</dd>
        </dl>
    </a>
    <a href="#" class="toptop" style="display: none">
        <dl>
            <dt><img src="${path}/static/img/gt4.png"/></dt>
            <dd>返回<br/>顶部</dd>
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
    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div>
<script src="${path}/static/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/static/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/static/js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
