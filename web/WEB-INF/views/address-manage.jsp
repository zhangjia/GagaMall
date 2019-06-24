<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8" />
    <title>最家</title>
    <jsp:include page="public-static-file.jsp" />
    <link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css" />
    <link rel="stylesheet" type="text/css" href="${path}/static/css/mygxin.css" />
    <script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${path}/static/js/user.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=b03bbaf1ff4c80e018561e5eb1210906"></script>
    <script src="${path}/static/js/city.js" type="text/javascript" charset="utf-8"></script>


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
<div class="Bott order cart mt">
    <div class="wrapper clearfix orderCon ">
        <div class="zuo fl">

            <jsp:include page="personal-left.jsp"/>
        </div>
        <div class="you fl orderL fl">
            <jsp:include page="address.jsp"/>
        </div>
    </div>
</div>


<!--返回顶部-->
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
</body>
</html>
