<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="utf-8" />
		<title>cart</title>
		<jsp:include page="public-static-file.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css" />
		<script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
		<script src="${path}/static/js/cart.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<!--------------------------------------cart--------------------->
		<jsp:include page="head.jsp"></jsp:include>

		<div class="cart mt">
			<!-----------------logo------------------->
			<!--<div class="logo">
				<h1 class="wrapper clearfix">
					<a href="index.html"><img class="fl" src="${path}/static/img/temp/logo.png"></a>
					<img class="top" src="${path}/static/img/temp/cartTop01.png">
				</h1>
			</div>-->
			<!-----------------site------------------->
			<div class="site">
				<p class=" wrapper clearfix">
					<span class="fl">购物车</span>
					<img class="top" src="${path}/static/img/temp/cartTop01.png">
					<a href="index.html" class="fr">继续购物&gt;</a>
				</p>
			</div>
			<!-----------------table------------------->
			<div class="table wrapper">
				<div class="tr">
					<div>商品</div>
					<div>单价</div>
					<div>数量</div>
					<div>小计</div>
					<div>操作</div>
				</div>
				<c:forEach items="${requestScope.commodities}" var="commodity">

					<c:if test="${commodity.SPU_INVENTORY > 0}">
					<div class="th">
						<div class="pro clearfix">
							<label class="fl">
								<input type="checkbox"/>
								<span></span>
							</label>
							<a class="fl" href="#">
								<dl class="clearfix">
									<dt class="fl"><img src="${commodity.IMG_URL}" class="cart-commodity-img"></dt>
									<dd class="fl">
										<p class="cart-commodity-name">${commodity.COMMODITY_NAME}</p>
										<p>
												${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(commodity.SPU_VALUE, '{', ''),'}' ,'' ),'"' , ''),',' ,'<br />' ),':' ,'：    ' )}
										</p>
<%--										<p>白色瓷瓶+白色串枚</p>--%>
									</dd>
								</dl>
							</a>
						</div>
						<div class="price">￥${commodity.SPU_PRESENT_PRICE}</div>
						<div class="number">
							<p class="num clearfix">
								<img class="fl sub" src="${path}/static/img/temp/sub.jpg">
								<span class="fl">${commodity.COMMODITY_COUNT}</span>
								<img class="fl add" src="${path}/static/img/temp/add.jpg">
							</p>
						</div>
						<div class="price sAll">￥${commodity.SPU_PRESENT_PRICE * commodity.COMMODITY_COUNT} </div>
						<div class="price"><a class="del" href="#2">删除</a></div>
					</div>
					</c:if>



				</c:forEach>

				<div class="goOn">空空如也~<a href="index.html">去逛逛</a></div>
				<div class="tr clearfix">
					<label class="fl">
						<input class="checkAll" type="checkbox"/>
						<span></span>
					</label>
					<p class="fl">
						<a href="#">全选</a>
						<a href="#" class="del">删除</a>
					</p>
					<p class="fr">
						<span>共<small id="sl">0</small>件商品</span>
						<span>合计:&nbsp;<small id="all">￥0.00</small></span>
						<a href="order.html" class="count">结算</a>
					</p>
				</div>
			</div>
		</div>
		<div class="cart mt">

			<!-----------------site------------------->
			<div class="site">

			</div>
			<!-----------------table------------------->
			<div class="table wrapper">
				<h4>失效商品</h4>
				<c:forEach items="${requestScope.commodities}" var="commodity">

					<c:if test="${commodity.SPU_INVENTORY <= 0}">

						<hr />
						<%--库存不足--%>
						<div class="th grayness">
							<div class="pro clearfix">
								<label class="fl">
									<input disabled="disabled" type="checkbox"/>
									<span></span>
								</label>
								<a class="fl" href="#">
									<dl class="clearfix">
										<dt class="fl"><img src="${commodity.IMG_URL}" class="cart-commodity-img"></dt>
										<dd class="fl">
											<p class="cart-commodity-name cart-understock">${commodity.COMMODITY_NAME}</p>
											<p>
													${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(commodity.SPU_VALUE, '{', ''),'}' ,'' ),'"' , ''),',' ,'<br />' ),':' ,'：    ' )}
															</p>
													<%--										<p>白色瓷瓶+白色串枚</p>--%>
										</dd>
									</dl>
								</a>
							</div>
							<div class="price">￥${commodity.SPU_PRESENT_PRICE}</div>
							<div class="number">
								<p class="num clearfix">
									<img class="fl sub" src="${path}/static/img/temp/sub.jpg">
									<span class="fl">0</span>
									<img class="fl add" src="${path}/static/img/temp/add.jpg">
								</p>
							</div>
							<div class="price sAll">￥${commodity.SPU_PRESENT_PRICE * commodity.COMMODITY_COUNT} </div>
							<div class="price"><a class="del" href="#2">删除</a></div>
						</div>

					</c:if>
					<c:if test="${length == 1}">

					</c:if>

				</c:forEach>

				<div class="goOn">空空如也~<a href="index.html">去逛逛</a></div>
				<div class="xxx">

				</div>
			</div>
		</div>





		<%-------------------------------------------------------%>
		<div class="mask"></div>
		<div class="tipDel">
			<p>确定要删除该商品吗？</p>
			<p class="clearfix">
				<a class="fl cer" href="#">确定</a>
				<a class="fr cancel" href="#">取消</a>
			</p>
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
			<a href="#" class="toptop" style="display: none;">
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
			违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
		</div>
		<!----------------mask------------------->
		<div class="mask"></div>
		<!-------------------mask内容------------------->
		<div class="proDets">
			<img class="off" src="${path}/static/img/temp/off.jpg" />
			<div class="proCon clearfix">
				<div class="proImg fr">
					<img class="list" src="${path}/static/img/temp/proDet.jpg"  />
					<div class="smallImg clearfix">
						<img src="${path}/static/img/temp/proDet01.jpg" data-src="${path}/static/img/temp/proDet01_big.jpg">
						<img src="${path}/static/img/temp/proDet02.jpg" data-src="${path}/static/img/temp/proDet02_big.jpg">
						<img src="${path}/static/img/temp/proDet03.jpg" data-src="${path}/static/img/temp/proDet03_big.jpg">
						<img src="${path}/static/img/temp/proDet04.jpg" data-src="${path}/static/img/temp/proDet04_big.jpg">
					</div>
				</div>
				<div class="fl">
					<div class="proIntro change">
						<p>颜色分类</p>
						<div class="smallImg clearfix">
							<p class="fl on"><img src="${path}/static/img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="${path}/static/img/temp/proBig01.jpg"></p>
							<p class="fl"><img src="${path}/static/img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="${path}/static/img/temp/proBig02.jpg"></p>
							<p class="fl"><img src="${path}/static/img/temp/prosmall03.jpg" alt="20支快乐花" data-src="${path}/static/img/temp/proBig03.jpg"></p>
							<p class="fl"><img src="${path}/static/img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="${path}/static/img/temp/proBig04.jpg"></p>
						</div>
					</div>
					<div class="changeBtn clearfix">
						<a href="#2" class="fl"><p class="buy">确认</p></a>
						<a href="#2" class="fr"><p class="cart">取消</p></a>
					</div>
				</div>
			</div>
		</div>
		<div class="pleaseC">
			<p>请选择宝贝</p>
			<img class="off" src="${path}/static/img/temp/off.jpg" />
		</div>

	</body>
</html>
