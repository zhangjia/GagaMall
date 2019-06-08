<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<%--
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css"/>

    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/flexslider/2.7.2/jquery.flexslider-min.js"></script>

    <script src="${pageContext.request.contextPath}/static/js/public.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/nav.js" type="text/javascript" charset="utf-8"></script>

--%>

</head>
<body>
<!------------------------------head.jsp------------------------------>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <h1 class="fl"><a href="index.jsp"><img src="${pageContext.request.contextPath}/static/img/logo.png"/></a>
            </h1>


            <div class="fr clearfix" id="top1">
               <c:if test="${sessionScope.user == null}">
                   <p class="fl">
                       <a href="login.jsp" id="login">登录</a>
                       <a href="register.jsp" id="reg">注册</a>
                   </p>
               </c:if>

                <c:if test="${sessionScope.user != null}">
                    <p class="fl">
                      欢迎您：  <a href="mygxin.jsp" id="WelcomeUser">${sessionScope.user.userName}</a>
                                            </p>
                </c:if>
                <form action="#" method="get" class="fl">
                    <input type="text" placeholder="热门搜索：干花花瓶"/>
                    <input type="button"/>
                </form>
                <div class="btn fl clearfix">
                    <a href="mygxin.jsp"><img src="${pageContext.request.contextPath}/static/img/grzx.png"/></a>
                    <a href="#" class="er1"><img src="${pageContext.request.contextPath}/static/img/ewm.png"/></a>
                    <a href="cart.jsp"><img src="${pageContext.request.contextPath}/static/img/gwc.png"/></a>
                    <p><a href="#"><img src="${pageContext.request.contextPath}/static/img/smewm.png"/></a></p>
                </div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="index.jsp">首页</a></li>
            <li>
                <a href="#">数码产品</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="TS/paint.jsp">
                            <dl>
<%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav1.jpg"/></dt>--%>
                                <dd>手机</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
<%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav2.jpg"/></dt>--%>
                                <dd>笔记本</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
<%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav3.jpg"/></dt>--%>
                                <dd>智能手表</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
<%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav6.jpg"/></dt>--%>
                                <dd>平板电脑</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
<%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav7.jpg"/></dt>--%>
                                <dd>相机</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>
            <li>
                <a href="TS/flowerDer.jsp">家用电器</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav1.jpg"/></dt>--%>
                                <dd>洗衣机</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav2.jpg"/></dt>--%>
                                <dd>空调</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav3.jpg"/></dt>--%>
                                <dd>冰箱</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav6.jpg"/></dt>--%>
                                <dd>电视</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav7.jpg"/></dt>--%>
                                <dd>智能家居</dd>
                            </dl>
                        </a>
                    </div>

                </div>
            </li>
            <li>
                <a href="decoration.jsp">居家餐厨</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav1.jpg"/></dt>--%>
                                <dd>客厅家具</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav2.jpg"/></dt>--%>
                                <dd>厨房家具</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav3.jpg"/></dt>--%>
                                <dd>卧室家具</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav6.jpg"/></dt>--%>
                                <dd>儿童玩具</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav7.jpg"/></dt>--%>
                                <dd>生活日用</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>
            <li>
                <a href="decoration.jsp">个护美妆</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav1.jpg"/></dt>--%>
                                <dd>口腔清洁</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav2.jpg"/></dt>--%>
                                <dd>洗护工具</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav3.jpg"/></dt>--%>
                                <dd>彩妆香氛</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav6.jpg"/></dt>--%>
                                <dd>面部护理</dd>
                            </dl>
                        </a>
                        <a href="TS/paint.jsp">
                            <dl>
                                <%--                                <dt><img src="${pageContext.request.contextPath}/static/img/nav7.jpg"/></dt>--%>
                                <dd>身体护理</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>

        </ul>
    </div>
</div>
</body>
</html>
