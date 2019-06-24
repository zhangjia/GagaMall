<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>嘎嘎商城|家庭必需品</title>

    <%-------------------------------------------------------其他页面导入----------------------------------------------%>
    <jsp:include page="public-static-file.jsp"/>
    <%--		<%@ include file="public-static-file.jsp"%>--%>
    <%--		-----------------------------------------------------css----------------------------------------------%>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/index.css"/>


    <%-------------------------------------------------------js----------------------------------------------%>

    <script src="https://cdn.bootcss.com/flexslider/2.7.2/jquery.flexslider-min.js"></script>


    <script type="text/javascript">
        $(function () {
            $('#home_slider').flexslider({
                animation: 'slide',
                controlNav: true,
                directionNav: true,
                animationLoop: true,
                slideshow: true,
                slideshowSpeed: 2000,
                useCSS: false
            });

        });
    </script>

</head>
<body>
<%--		<%@ include file="head.jsp"%>--%>
<jsp:include page="head.jsp"/>


<!-------------------------banner--------------------------->
<div class="block_home_slider">
    <div id="home_slider" class="flexslider">
        <ul class="slides">
            <li>
                <div class="slide">
                    <img src="https://zhangjia.oss-cn-qingdao.aliyuncs.com/img/web/banner1.png"/>
                </div>
            </li>
            <li>
                <div class="slide">
                    <img src="https://zhangjia.oss-cn-qingdao.aliyuncs.com/img/web/banner3.png"/>
                </div>
            </li>
        </ul>
    </div>
</div>

<!------------------------------thImg------------------------------>
<%--<div class="thImg">
    <%--<div class="clearfix">
        <a href="vase_proList.jsp"><img src="${path}/static/img/i1.jpg"/></a>
        <a href="proList.jsp"><img src="${path}/static/img/i2.jpg"/></a>
        <a href="#2"><img src="${path}/static/img/i3.jpg"/></a>
    </div>
</div>--%>


<!------------------------------people------------------------------>
<div class="people">
    <div class="wrapper">
        <h2><img src="${path}/static/img/ih3.jpg"/></h2>
        <div class="pList clearfix tran">
            <c:forEach items="${commodities4Index}" var="com" varStatus="i">


                <c:if test="${i.count <= 4}">
                    <a href="${path}/commodityDetail?commodityId=${com.commodityId}">
                        <dl>
                            <dt>
                                <span class="abl"></span>
                                <img style="width: 268px;height: auto" src="${com.commodityImg[0].IMG_URL}"/>
                                <span class="abr"></span>
                            </dt>
                            <dd class="jia-index-commodity-name">${com.commodityName}</dd>
                            <dd><span>￥${com.commodityMinPresentPrice}</span></dd>
                        </dl>
                    </a>
                </c:if>

            </c:forEach>
        </div>

        <div class="pList clearfix tran">
            <c:forEach items="${commodities4Index}" var="com" varStatus="i">


                <c:if test="${i.count > 4 && i.count <=8}">
                    <a href="${path}/commodityDetail?commodityId=${com.commodityId}">
                        <dl>
                            <dt>
                                <span class="abl"></span>
                                <img style="width: 268px;height: auto" src="${com.commodityImg[0].IMG_URL}"/>
                                <span class="abr"></span>
                            </dt>
                            <dd class="jia-index-commodity-name">${com.commodityName}</dd>
                            <dd><span>￥${com.commodityMinPresentPrice}</span></dd>
                        </dl>
                    </a>
                </c:if>

            </c:forEach>
        </div>
        <div class="pList clearfix tran">
            <c:forEach items="${commodities4Index}" var="com" varStatus="i">


                <c:if test="${i.count > 8 && i.count <= 12}">
                    <a href="${path}/commodityDetail?commodityId=${com.commodityId}">
                        <dl>
                            <dt>
                                <span class="abl"></span>
                                <img style="width: 268px;height: auto" src="${com.commodityImg[0].IMG_URL}"/>
                                <span class="abr"></span>
                            </dt>
                            <dd class="jia-index-commodity-name">${com.commodityName}</dd>
                            <dd><span>￥${com.commodityMinPresentPrice}</span></dd>
                        </dl>
                    </a>
                </c:if>

            </c:forEach>
        </div>
    </div>
</div>
<!--返回顶部-->
<jsp:include page="right-sidebar.jsp"></jsp:include>

<!-------------------login-------------------------->
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
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div>


</body>
</html>
