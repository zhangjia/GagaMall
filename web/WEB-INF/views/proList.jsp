<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>商品列表</title>
    <script src="https://cdn.bootcss.com/layer/2.3/layer.js"></script>
    <%@ include file="public-static-file.jsp" %>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css"/>

    <script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${path}/static/js/cart.js" type="text/javascript" charset="utf-8"></script>


    <script>

        $(function () {


            /************************************点击上一页**********************************/
            $(".lipp").click(function () {
                //如果page = 1，说明已经是第一页了
                if (${param.page == 1}) {
                    layer.alert("已经是第一页了")
                } else {

                    //如果是直接打开的页面，参数中就没有order
                    if (${param.order == null}) {
                        var lo = location.toString();
                        //将当前页数减一
                        lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=${(param.page)-1}");
                        location = lo;
                    } else {
                        // 如果是在排序页面，点击上一页，则进入这里
                        var lo = location.toString();
                        //将当前页数减一
                        lo = lo.replace(lo.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=${(param.page)-1}");
                        //将之前的order和值都删除
                        // lo = lo.replace(lo.slice(lo.indexOf("&order")),"");
                        <%--location =  lo+ "&order=${param.order}";--%>
                        location = lo;
                    }

                }

            });

            /************************************点击下一页**********************************/
            $(".linp").click(function () {
                //如果page = 总页数，说明已经是最后一页了
                if (${param.page == requestScope.commoditiesCount}) {
                    layer.alert("已经是最后一页了")
                } else {
                    //如果是直接打开的页面，参数中就没有order
                    if (${param.order == null}) {
                        var lo = location.toString();
                        lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=${(param.page)+1}");
                        location = lo;
                    } else {
                        // 如果是在排序页面，点击下一页，则进入这里
                        var lo = location.toString();
                        lo = lo.replace(lo.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=${(param.page)+1}");
                        // lo = lo.replace(lo.slice(lo.indexOf("&order")),"");
                        <%--location =  lo+ "&order=${param.order}";--%>
                        location = lo;
                    }
                }

            });
            /************************************点击尾页**********************************/
            $(".endp").click(function () {

                //如果是直接打开的页面，参数中就没有order
                if (${param.order == null}) {
                    var lo = location.toString();

                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=${requestScope.commoditiesCount}");
                    location = lo;
                } else {
                    // 如果是在排序页面，点击尾页，则进入这里
                    var lo = location.toString();
                    lo = lo.replace(lo.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=${requestScope.commoditiesCount}");
                    // lo = lo.replace(lo.slice(lo.indexOf("&order")),"");
                    <%--location =  lo+ "&order=${param.order}";--%>
                    location = lo;
                }


            })
            /************************************点击首页**********************************/
            $(".ip").click(function () {

                //如果是直接打开的页面，参数中就没有order
                if (${param.order == null}) {
                    var lo = location.toString();

                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=1");
                    location = lo;
                } else {
                    // 如果是在排序页面，点击首页，则进入这里
                    var lo = location.toString();
                    lo = lo.replace(lo.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=1");
                    // lo = lo.replace(lo.slice(lo.indexOf("&order")),"");
                    <%--location =  lo+ "&order=${param.order}";--%>
                    location = lo;
                }


            })


            /************************************点击按照时间排序**********************************/
            $(".order-by-time").click(function () {
                var lo = location.toString();


                if (lo.indexOf("&order") == -1) {
                    // 直接访问列表时，不带order参数，如果此时点击排序，则进入这里
                    var lo = location.toString();
                    //只要是点击排序，就回到第一页
                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=1");
                    location = lo + "&order=5";
                } else {
                    //如果已经点了排序，则url中带order参数，此时再次点击排序，则进入这里
                    var nowlocaion = lo.replace(lo.slice(lo.indexOf("&order")), "");
                    nowlocaion = nowlocaion.replace(nowlocaion.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=1");
                    location = nowlocaion + "&order=5";
                }

            });
            /************************************点击按照价格排序**********************************/
            $(".order-by-price").click(function () {
                var lo = location.toString();
                if (lo.indexOf("&order") == -1) {
                    var lo = location.toString();
                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=1");
                    location = lo + "&order=3";
                } else {

                    var nowlocaion = lo.replace(lo.slice(lo.indexOf("&order")), "");
                    nowlocaion = nowlocaion.replace(nowlocaion.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=1");
                    location = nowlocaion + "&order=3";
                }
            });

            /************************************点击按照价格降序排序**********************************/
            $(".order-by-price-desc").click(function () {
                var lo = location.toString();
                if (lo.indexOf("&order") == -1) {
                    var lo = location.toString();
                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=1");
                    location = lo + "&order=4";
                } else {

                    var nowlocaion = lo.replace(lo.slice(lo.indexOf("&order")), "");
                    nowlocaion = nowlocaion.replace(nowlocaion.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=1");
                    location = nowlocaion + "&order=4";
                }
            });

            /************************************点击按照销量排序**********************************/
            $(".order-by-sales").click(function () {

                var lo = location.toString();
                if (lo.indexOf("&order") == -1) {
                    var lo = location.toString();
                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=1");
                    location = lo + "&order=1";
                } else {

                    var nowlocaion = lo.replace(lo.slice(lo.indexOf("&order")), "");
                    nowlocaion = nowlocaion.replace(nowlocaion.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=1");
                    location = nowlocaion + "&order=1";
                }
            });
            /************************************点击按照销量降序排序**********************************/
            $(".order-by-sales-desc").click(function () {
                var lo = location.toString();
                if (lo.indexOf("&order") == -1) {
                    var lo = location.toString();
                    lo = lo.replace(lo.slice(lo.indexOf("page=")), "page=1");
                    location = lo + "&order=2";
                } else {

                    var nowlocaion = lo.replace(lo.slice(lo.indexOf("&order")), "");
                    //切换排序方式的时候，回到第一页开始展示
                    nowlocaion = nowlocaion.replace(nowlocaion.slice(lo.indexOf("page="), lo.indexOf(("&order"))), "page=1");
                    location = nowlocaion + "&order=2";
                }
            });


        })


    </script>

</head>
<body>
<jsp:include page="head.jsp"/>
<!------------------------------banner------------------------------>
<div class="banner">
    <%--			<a href="#"><img src="${path}/static/img/temp/banner1.jpg"/></a>--%>
    <%--			<a href="#"><img src=""></a>--%>
</div>
<!-----------------address------------------------------->
<c:if test="${param.firstMenuId != null || param.secMenuId != null}">
    <div class="address">
        <div class="wrapper clearfix">
            <a href="${path}/index">首页</a>
                <%--    ${requestScope.commodities[0]}--%>
            <c:if test="${param.firstMenuId != null}">
                <span>/</span>
                <a href="${path}/list?firstMenuId=${requestScope.commodities[0].firstMenuId}&page=1">${requestScope.commodities[0].firstMenuChineseName}</a>
            </c:if>


            <c:if test="${param.firstMenuId == null}">
                <span>/</span>
                <a href="${path}/list?firstMenuId=${requestScope.commodities[0].firstMenuId}&page=1">${requestScope.firstMenuChineseName}</a>
                <span>/</span>
                <a href="${path}/list?secMenuId=${requestScope.commodities[0].secMenuId}&page=1">${requestScope.commodities[0].secMenuChineseName}</a>
            </c:if>
        </div>
    </div>
</c:if>
<!-------------------current---------------------->
<div class="current">
    <div class="wrapper clearfix">
        <c:if test="${param.firstMenuId != null}">
            <h3 class="fl">${requestScope.commodities[0].firstMenuChineseName}</h3>
        </c:if>

        <c:if test="${param.secMenuId != null}">
            <h3 class="fl">${requestScope.commodities[0].secMenuChineseName}</h3>
        </c:if>
        <c:if test="${param.firstMenuId == null && param.secMenuId == null }">
            <h3 class="fl">全部商品</h3>
        </c:if>


        <div class="fr choice">

            <c:choose>
                <c:when test="${param.order == null}">
                    <p class="default">排序方式</p>
                </c:when>
                <c:when test="${param.order == 5}">
                    <p class="default">新品上市</p>
                </c:when>
                <c:when test="${param.order == 2}">
                    <p class="default">>销量从高到低</p>
                </c:when>
                <c:when test="${param.order == 1}">
                    <p class="default">>销量从低到高</p>
                </c:when>
                <c:when test="${param.order == 4}">
                    <p class="default">>价格从高到低</p>
                </c:when>
                <c:when test="${param.order == 3}">
                    <p class="default">>价格从低到高</p>
                </c:when>
            </c:choose>


            <ul class="select">
                <li class="order-by-time">新品上市</li>
                <li class="order-by-sales-desc">>销量从高到低</li>
                <li class="order-by-sales">>销量从低到高</li>
                <li class="order-by-price-desc">>价格从高到低</li>
                <li class="order-by-price">>价格从低到高</li>
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
            <a href="${path}/commodityDetail?commodityId=${commodity.commodityId}">
                <dl>
                    <dt>
                        <div>
                            <c:forEach items="${commodity.commodityImg}" var="ct">
                                <c:if test="${ct.IMG_ORDER==1}">
                                    <img src="${ct.IMG_URL}"
                                </c:if>
                            </c:forEach>


                        </div>
                    </dt>
                    <dd>${commodity.commodityName}</dd>

                    <span class="pro-price">￥${commodity.commodityMinPresentPrice}</span>
                    <span class="pro-sales">销量：${commodity.commoditySales}件</span>


                </dl>
            </a>
        </li>
    </c:forEach>


</ul>
<!----------------mask------------------->
<div class="mask"></div>
<!-------------------mask内容------------------->
<div class="proDets">
    <img class="off" src="${path}/static/img/temp/off.jpg"/>
    <div class="tit clearfix">
        <h4 class="fl">【最家】非洲菊仿真花干花</h4>
        <span class="fr">￥17.90</span>
    </div>
    <div class="proCon clearfix">
        <div class="proImg fl">
            <img class="list" src="${path}/static/img/temp/proDet.jpg"/>
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
                    <p class="fl"><img src="${path}/static/img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花"
                                       data-src="${path}/static/img/temp/proBig01.jpg"></p>
                    <p class="fl"><img src="${path}/static/img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草"
                                       data-src="${path}/static/img/temp/proBig02.jpg"></p>
                    <p class="fl"><img src="${path}/static/img/temp/prosmall03.jpg" alt="20支快乐花"
                                       data-src="${path}/static/img/temp/proBig03.jpg"></p>
                    <p class="fl"><img src="${path}/static/img/temp/prosmall04.jpg" alt="20支兔尾巴草"
                                       data-src="${path}/static/img/temp/proBig04.jpg"></p>
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
<jsp:include page="right-sidebar.jsp"></jsp:include>
<div class="msk"></div>

<div class="page">
    <ul class="pagination">

        <li class="page-item"><a class="page-link ip" href="javascript:;">首页</a></li>

        <li class="page-item lipp }"><a class="page-link  pp" href="javascript:;">上一页</a></li>
        <c:forEach begin="1" var="i" end="${requestScope.commoditiesCount}">
            <%--						<li class="page-item ${param.page == i ? "active":""}" ><a class="page-link" href="${path}/list?page=${i}">${i}</a></li>--%>

            <%--
                这里 和上面的js不同，上面的js是直接把原网址的数据要变化的地方替换掉，而下面的方法是逐个判断所有的可能性
                   list?page=1
                   list?page=1&order=2

                   list?firstMenuId=1&page=1
                   list?firstMenuId=1&page=1&order=2

                   list?secMenuId=2&page=1
                   list?secMenuId=2&page=1&order=1

                   list?name=1&page=1
                   list?name=1&page=1&order=1

                --%>

            <%--************************************全部商品中的数字分页**********************************--%>
            <c:if test="${param.firstMenuId == null && param.secMenuId == null && param.name == null && param.page != null}">

                <%--如果没有排序就直接查--%>
                <c:if test="${param.order == null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?page=${i}">${i}</a>
                    </li>

                </c:if>
                <c:if test="${param.order != null }">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?page=${i}&order=${param.order}">${i}</a>
                    </li>

                </c:if>

            </c:if>
            <%--************************************一级菜单中的商品的数字分页**********************************--%>
            <c:if test="${param.firstMenuId != null && param.secMenuId == null && param.name == null && param.page != null}">

                <%--如果没有排序就直接查--%>
                <c:if test="${param.order == null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?firstMenuId=${param.firstMenuId}&page=${i}">${i}</a>
                    </li>
                </c:if>

                <c:if test="${param.order != null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?firstMenuId=${param.firstMenuId}&page=${i}&order=${param.order}">${i}</a>
                    </li>
                </c:if>

            </c:if>

            <%--************************************二级菜单中的商品的数字分页**********************************--%>
            <c:if test="${param.firstMenuId == null && param.secMenuId != null && param.name == null && param.page != null}">

                <%--如果没有排序就直接查--%>
                <c:if test="${param.order == null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?secMenuId=${param.secMenuId}&page=${i}">${i}</a>
                    </li>
                </c:if>

                <c:if test="${param.order != null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?secMenuId=${param.secMenuId}&page=${i}&order=${param.order}">${i}</a>
                    </li>
                </c:if>

            </c:if>


            <%--************************************搜索页的数字分页**********************************--%>
            <c:if test="${param.firstMenuId == null && param.secMenuId == null && param.name != null && param.page != null}">

                <%--如果没有排序就直接查--%>
                <c:if test="${param.order == null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?name=${param.name}&page=${i}">${i}</a>
                    </li>
                </c:if>

                <c:if test="${param.order != null}">
                    <li class="page-item ${param.page == i ? "active":""}"><a class="page-link"
                                                                              href="${path}/list?name=${param.name}&page=${i}&order=${param.order}">${i}</a>
                    </li>
                </c:if>

            </c:if>

        </c:forEach>
        <li class="page-item linp"><a class="page-link np" href="javascript:;">下一页</a></li>

        <li class="page-item endp"><a class="page-link" href="javascript:;">尾页</a></li>
    </ul>
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

</body>
</html>
