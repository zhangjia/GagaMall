<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>ok</title>
		<jsp:include page="public-static-file.jsp" />
		<link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css" />
	</head>
	<body>
		<!----------------------------------------order------------------>
		<jsp:include page="head.jsp" />
		<div class="order mt cart">
			<div class="site">
				<p class="wrapper clearfix">
					<span class="fl">支付成功</span>
					<img class="top" src="${path}/static/img/temp/cartTop03.png">
				</p>
			</div>
			<p class="ok">支付成功！剩余<span>5</span>秒<a href="myorderq.html">返回订单页</a></p>
		</div>
		<div class="like">
			<h4>猜你喜欢</h4>
			<div class="bottom">
				<div class="hd">
					<span class="prev"><img src="${path}/static/img/temp/prev.png"></span>
					<span class="next"><img src="${path}/static/img/temp/next.png"></span>
				</div>
				<div class="imgCon wrapper bd">
					<div class="likeList clearfix">
						<div>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like01.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like02.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like03.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like04.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html" class="last">
								<dl>
									<dt><img src="${path}/static/img/temp/like05.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
						</div>
						<div>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like01.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like02.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like03.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html">
								<dl>
									<dt><img src="${path}/static/img/temp/like04.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
							<a href="proDetail.html" class="last">
								<dl>
									<dt><img src="${path}/static/img/temp/like05.jpg"></dt>
									<dd>【最家】复古文艺风玻璃花瓶</dd>
									<dd>￥193.20</dd>
								</dl>
							</a>
						</div>
					</div>
				</div>
			</div>
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
			违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		<script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			jQuery(".bottom").slide({titCell:".hd ul",mainCell:".bd .likeList",autoPage:true,autoPlay:false,effect:"leftLoop",autoPlay:true,vis:1});
		</script>
	</body>
</html>
