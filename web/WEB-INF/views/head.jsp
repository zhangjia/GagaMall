<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!------------------------------head.jsp------------------------------>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <h1 class="fl"><a href="index.jsp"><img src="https://zhangjia.oss-cn-qingdao.aliyuncs.com/img/web/logo.png"/></a>
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
                <form action="${path}/list" method="get" class="fl">
                    <input type="text" name="name" value="${param.name}" placeholder="搜索"/>
                    <input type="submit" value="" />
                </form>
                <div class="btn fl clearfix">
                    <a href="mygxin.jsp"><img src="${path}/static/img/grzx.png"/></a>
                    <a href="#" class="er1"><img src="${path}/static/img/ewm.png"/></a>
                    <a href="cart.jsp"><img src="${path}/static/img/gwc.png"/></a>
                    <p><a href="#"><img src="${path}/static/img/smewm.png"/></a></p>
                </div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
<%--            <li><a href="index.jsp">首页${sessionScope.nav2}</a></li>--%>
            <li><a href="${path}/index">首页</a></li>
            <c:forEach items="${requestScope.firstNav}" var="firstNav">
<%--            <c:forEach items="${sessionScope.firstNav}" var="firstNav">--%>
                <li>
                    <a href="list?firstMenuId=${firstNav.FIRST_MENU_ID}">${firstNav.FIRST_MENU_CHINESE_NAME}</a>

                    <div class="sList">
                        <div class="wrapper  clearfix">
                            <c:forEach items="${requestScope.secNav}" var="secNav">
                                <a href="list?secMenuId=${secNav.SEC_MENU_ID}">
                                    <dl>

                                        <dd>${secNav.SEC_MENU_CHINESE_NAME}</dd>
                                    </dl>
                                </a>
                            </c:forEach>
                        </div>

                    </div>
                </li>
            </c:forEach>
           <%-- <li>
                <a href="proList.jsp">数码产品</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="proList.jsp">
                            <dl>
&lt;%&ndash;                                <dt><img src="${path}/static/img/nav1.jpg"/></dt>&ndash;%&gt;
                                <dd>手机</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
&lt;%&ndash;                                <dt><img src="${path}/static/img/nav2.jpg"/></dt>&ndash;%&gt;
                                <dd>笔记本</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
&lt;%&ndash;                                <dt><img src="${path}/static/img/nav3.jpg"/></dt>&ndash;%&gt;
                                <dd>智能手表</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
&lt;%&ndash;                                <dt><img src="${path}/static/img/nav6.jpg"/></dt>&ndash;%&gt;
                                <dd>平板电脑</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
&lt;%&ndash;                                <dt><img src="${path}/static/img/nav7.jpg"/></dt>&ndash;%&gt;
                                <dd>相机</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>--%>
           <%-- <li>
                <a href="idea.jsp">家用电器</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav1.jpg"/></dt>&ndash;%&gt;
                                <dd>洗衣机</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav2.jpg"/></dt>&ndash;%&gt;
                                <dd>空调</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav3.jpg"/></dt>&ndash;%&gt;
                                <dd>冰箱</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav6.jpg"/></dt>&ndash;%&gt;
                                <dd>电视</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav7.jpg"/></dt>&ndash;%&gt;
                                <dd>智能家居</dd>
                            </dl>
                        </a>
                    </div>

                </div>
            </li>
            <li>
                <a href="idea.jsp">居家餐厨</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav1.jpg"/></dt>&ndash;%&gt;
                                <dd>客厅家具</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav2.jpg"/></dt>&ndash;%&gt;
                                <dd>厨房家具</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav3.jpg"/></dt>&ndash;%&gt;
                                <dd>卧室家具</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav6.jpg"/></dt>&ndash;%&gt;
                                <dd>儿童玩具</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav7.jpg"/></dt>&ndash;%&gt;
                                <dd>生活日用</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>
            <li>
                <a href="idea.jsp">个护美妆</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav1.jpg"/></dt>&ndash;%&gt;
                                <dd>口腔清洁</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav2.jpg"/></dt>&ndash;%&gt;
                                <dd>洗护工具</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav3.jpg"/></dt>&ndash;%&gt;
                                <dd>彩妆香氛</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav6.jpg"/></dt>&ndash;%&gt;
                                <dd>面部护理</dd>
                            </dl>
                        </a>
                        <a href="proList.jsp">
                            <dl>
                                &lt;%&ndash;                                <dt><img src="${path}/static/img/nav7.jpg"/></dt>&ndash;%&gt;
                                <dd>身体护理</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>--%>
            <li>
                <a href="${path}/list">全部商品</a>

            </li>
        </ul>
    </div>
</div>
