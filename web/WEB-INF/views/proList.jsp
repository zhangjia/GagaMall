<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>全部商品</title>
		<%@ include file="public-static-file.jsp"%>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css"/>
	</head>
	<body>
	<jsp:include page="head.jsp" />
		<!------------------------------banner------------------------------>
		<div class="banner">
			<a href="#"><img src="${path}/static/img/temp/banner1.jpg"/></a>
		</div>
		<!-----------------address------------------------------->
	<%--	<div class="address">
			<div class="wrapper clearfix">
				<a href="index.jsp">首页</a>
				<span>/</span>
				<a href="TS/flowerDer.jsp">装饰摆件</a>
				<span>/</span>
				<a href="proList.html" class="on">干花花艺</a>
			</div>
		</div>--%>
		<!-------------------current---------------------->
		<div class="current">
			<div class="wrapper clearfix">
				<h3 class="fl">全部商品</h3>
				<div class="fr choice">
					<p class="default">排序方式</p>
					<ul class="select">
						<li>新品上市</li>
						<li>销量从高到低</li>
						<li>销量从低到高</li>
						<li>价格从高到低</li>
						<li>价格从低到高</li>
					</ul>
				</div>
			</div>
		</div>
		<!----------------proList------------------------->
		<ul class="proList wrapper clearfix">
			<%--<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro01.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>--%>

			<c:forEach items="${requestScope.commodities}" var="commodity">
				<li>
					<a href="proDetail.jsp">
						<dl>
							<dt>
								<div>
									<c:forEach items="${commodity.commodityImg}" var="ct">
										<c:if test="${ct.IMG_ORDER==1}" >
										<img src="${ct.IMG_URL}"
										</c:if>
									</c:forEach>


								</div>
							</dt>
							<dd>${commodity.commodityName}</dd>
							<dd>￥${commodity.commodityPresentPrice}</dd>
						</dl>
					</a>
				</li>
			</c:forEach>

			<%--<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro02.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro03.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro04.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro05.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro06.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro07.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro08.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro01.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro02.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro03.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro04.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro05.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro06.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro07.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>
			<li>
				<a href="proDetail.jsp">
					<dl>
						<dt><img src="${path}/static/img/temp/pro08.jpg"></dt>
						<dd>【最家】跳舞兰仿真花干花</dd>
						<dd>￥17.90</dd>
					</dl>
				</a>
			</li>--%>
		</ul>
		<!----------------mask------------------->
		<div class="mask"></div>
		<!-------------------mask内容------------------->
		<div class="proDets">
			<img class="off" src="${path}/static/img/temp/off.jpg" />
			<div class="tit clearfix">
				<h4 class="fl">【最家】非洲菊仿真花干花</h4>
				<span class="fr">￥17.90</span>
			</div>
			<div class="proCon clearfix">
				<div class="proImg fl">
					<img class="list" src="${path}/static/img/temp/proDet.jpg"  />
					<div class="smallImg clearfix">
						<img src="${path}/static/img/temp/proDet01.jpg" data-src="${path}/static/img/temp/proDet01_big.jpg">
						<img src="${path}/static/img/temp/proDet02.jpg" data-src="${path}/static/img/temp/proDet02_big.jpg">
						<img src="${path}/static/img/temp/proDet03.jpg" data-src="${path}/static/img/temp/proDet03_big.jpg">
						<img src="${path}/static/img/temp/proDet04.jpg" data-src="${path}/static/img/temp/proDet04_big.jpg">
					</div>
				</div>
				<div class="fr">
					<div class="proIntro">
						<p>颜色分类</p>
						<div class="smallImg clearfix categ">
							<p class="fl"><img src="${path}/static/img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="${path}/static/img/temp/proBig01.jpg"></p>
							<p class="fl"><img src="${path}/static/img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="${path}/static/img/temp/proBig02.jpg"></p>
							<p class="fl"><img src="${path}/static/img/temp/prosmall03.jpg" alt="20支快乐花" data-src="${path}/static/img/temp/proBig03.jpg"></p>
							<p class="fl"><img src="${path}/static/img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="${path}/static/img/temp/proBig04.jpg"></p>
						</div>
						<p>数量&nbsp;&nbsp;库存<span>2096</span>件</p>
						<div class="num clearfix">
							<img class="fl sub" src="${path}/static/img/temp/sub.jpg">
							<span class="fl" contentEditable="true">1</span>
							<img class="fl add" src="${path}/static/img/temp/add.jpg">
							<p class="please fl">请选择商品属性!</p>
						</div>
					</div>
					<div class="btns clearfix">
						<a href="#2"><p class="buy fl">立即购买</p></a>
						<a href="#2"><p class="cart fr">加入购物车</p></a>
					</div>
				</div>
			</div>
			<a class="more" href="proDetail.jsp">查看更多细节</a>
		</div>
		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.jsp">
			<dl class="goCart">
				<dt><img src="${path}/static/img/gt1.png"/></dt>
				<dd>去购<br />物车</dd>
				<span>1</span>
			</dl>
			</a>
			<a href="#" class="dh">
			<dl>
				<dt><img src="${path}/static/img/gt2.png"/></dt>
				<dd>联系<br />客服</dd>
			</dl>
			</a>
			<a href="mygxin.jsp">
			<dl>
				<dt><img src="${path}/static/img/gt3.png"/></dt>
				<dd>个人<br />中心</dd>
			</dl>
			</a>
			<a href="#" class="toptop" style="display: none;">
			<dl>
				<dt><img src="${path}/static/img/gt4.png"/></dt>
				<dd>返回<br />顶部</dd>
			</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		<div class="msk"></div>
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
			违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		<script src="${path}/static/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/public.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/nav.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/cart.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
