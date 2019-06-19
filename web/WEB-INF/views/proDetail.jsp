<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>详情页</title>
    <jsp:include page="public-static-file.jsp"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/pro-detail.css"/>
    <script src="${path}/static/js/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${path}/static/js/cart.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        jQuery(".bottom").slide({
            titCell: ".hd ul",
            mainCell: ".bd .likeList",
            autoPage: true,
            autoPlay: false,
            effect: "leftLoop",
            autoPlay: true,
            vis: 1
        });
        //	---------------------------------------------------------------------------------------
        $(function () {
            var x = 1;
            $.ajax({});
            var selectjson;


            $(document).on('click', '.btn-allocate.btn-normal', function () {///对于静态和动态创建的标签都好使
                if ($(this).hasClass("active")) {
                    $(this).removeClass("active")
                } else {

                    // $(("#order"+i)+" .btn-allocate.btn-normal").removeClass("active");
                    $(this).parent().children(" .btn-allocate.btn-normal").removeClass("active");

                    // console.log($(this).parent().children())
                    $(this).addClass("active")
                }

                var length = $(".sku").length;
                if ($(".sku dd.active").length == length) {
                    var le = $(".sku").length;
                    var s = "";
                    var j = {};
                    var json = [];
                    //生成sku-name的json字符串
                    $(".sku").each(function () {
                        // s = s+$(this).children(".sku-key").text();
                        // s = s + $(this).children("dd.active").text();
                        // var name = $(this).children(".sku-key").textAlign.toString.replace(" ", "");
                        var name = $(this).children(".sku-key").text();
                        name = name.replace(/ /g, "");
                        name = name.replace(/\n/g, "");
                        console.log(name);
                        j[name] = $(this).children("dd.active").text();
                        json.push(j);
                    });


                    // var a = JSON.stringify(json);

                    // console.log(JSON.stringify(s));
                    selectjson = j;
                    $.ajax({
                        url: "${path}/commodityDetail",
                        type: "post",
                        data: {
                            SPEC: JSON.stringify(j)
                        },
                        success: function (res) {
                            console.log(JSON.stringify(j));
                            $(".commodity-price").text('￥' + res[0].SKU_PRESENT_PRICE);
                            $(".inventory").text(res[0].SKU_INVENTORY);
                            $(".sale").text(res[0].SKU_SALES);
                        }
                    });
                } else {

                    if (${requestScope.commodity.commodityMaxPresentPrice != requestScope.commodity.commodityMinPresentPrice}) {
                        $(".commodity-price").text('￥' + '${requestScope.commodity.commodityMinPresentPrice}' + "~￥" + '${requestScope.commodity.commodityMaxPresentPrice}');
                    } else {
                        $(".commodity-price").text('￥' + '${requestScope.commodity.commodityMinPresentPrice}');
                    }
                    $(".inventory").text(${requestScope.commodity.commodityInventory});
                    $(".sale").text(${requestScope.commodity.commoditySales});
                }
            });

            //------------------------------------------加入购物车-----------------------------------------------------
            $(".add-cart").click(function () {
                var sku = JSON.stringify(selectjson);
                var commodityCount = $(".cart-commodity-count").text()
                var length = $(".sku").length;
                if ($(".sku dd.active").length != length) {
                    layer.alert("请先选择商品规格")
                } else {
                    $.ajax({
                        url: "${path}/addCart",
                        type: "get",
                        data: {
                            SKU: sku,
                            commodityCount: commodityCount
                        },
                        success: function (res) {
                            console.log(res);
                             if (res.isLogin === false) {

                                location = "${path}/login?uri=/ga/commodityDetail?commodityId=${param.commodityId}";
                            }
                            console.log(res);
                            if (res.success) {
                                layer.alert("添加成功")
                            }

                        }
                    });
                }
            });
        });
    </script>

</head>
<body>
<!------------------------------head------------------------------>
<jsp:include page="head.jsp"/>
<!-----------------address------------------------------->
<div class="address">
    <div class="wrapper clearfix">
        <a href="index.html">首页</a>
        <span>/</span>
        <a href="${path}/list?firstMenuId=${requestScope.commodity.firstMenuId}&page=1">${requestScope.commodity.firstMenuChineseName}</a>
        <span>/</span>
        <a href="${path}/list?secMenuId=${requestScope.commodity.secMenuId}&page=1">${requestScope.commodity.secMenuChineseName}</a>
        <span>/</span>
        <a href="#" class="on">${requestScope.commodity.commodityName}</a>
    </div>
</div>
<!-----------------------Detail------------------------------>
<div class="detCon">
    <div class="proDet wrapper">
        <div class="proCon clearfix">
            <div class="proImg fl">
                <c:forEach items="${requestScope.commodity.commodityImg}" var="img" varStatus="i">

                    <c:if test="${img.IMG_ORDER == 1}">
                        <img class="det" src="${img.IMG_URL}"/>
                    </c:if>
                </c:forEach>


                <div class="smallImg clearfix">
                    <%--					<img src="${path}/static/img/temp/proDet01.jpg" data-src="${path}/static/img/temp/proDet01_big.jpg">--%>
                    <%--					<img src="${path}/static/img/temp/proDet02.jpg" data-src="${path}/static/img/temp/proDet02_big.jpg">--%>
                    <%--					<img src="${path}/static/img/temp/proDet03.jpg" data-src="${path}/static/img/temp/proDet03_big.jpg">--%>
                    <%--					<img src="${path}/static/img/temp/proDet04.jpg" data-src="${path}/static/img/temp/proDet04_big.jpg">--%>
                    <c:forEach items="${requestScope.commodity.commodityImg}" var="img" varStatus="i">

                        <c:if test="${img.IMG_ORDER == i.count}">
                            <%--							<img  src="${img.IMG_URL}" />--%>
                            <%--													<img class="zj" src="https://zhangjia.oss-cn-qingdao.aliyuncs.com/img/web/iphoneXsMax2.jpg"--%>
                            <img src="${img.IMG_URL}" data-src="${img.IMG_URL}">
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="fr intro">
                <div class="title">
                    <h4>${requestScope.commodity.commodityName}</h4>
                    <%--					<p>【破损补寄】【适合放室内、卧室、书房、餐桌、办公室、客厅、电视柜等地方】【无理由退换货】【包邮】【白色现代简约花瓶】</p>--%>
                    <p></p>
                    <c:if test="${requestScope.commodity.commodityMaxPresentPrice == requestScope.commodity.commodityMinPresentPrice}">
                        <span class="commodity-price">￥${requestScope.commodity.commodityMaxPresentPrice}</span>
                    </c:if>
                    <c:if test="${requestScope.commodity.commodityMaxPresentPrice != requestScope.commodity.commodityMinPresentPrice}">
                        <span class="commodity-price">￥${requestScope.commodity.commodityMinPresentPrice}~￥${requestScope.commodity.commodityMaxPresentPrice}</span>
                    </c:if>
                </div>
                <div class="proIntro">
                    <%--<div class="smallImg clearfix categ">
                            <p class="fl"><img src="${path}/static/img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="${path}/static/img/temp/proBig01.jpg"></p>
                            <p class="fl"><img src="${path}/static/img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="${path}/static/img/temp/proBig02.jpg"></p>
                            <p class="fl"><img src="${path}/static/img/temp/prosmall03.jpg" alt="20支快乐花" data-src="${path}/static/img/temp/proBig03.jpg"></p>
                            <p class="fl"><img src="${path}/static/img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="${path}/static/img/temp/proBig04.jpg"></p>
                        </div>--%>
                    <div class="skus">
                        <c:forEach items="${requestScope.SPEC}" var="SPEC">
                            <dl class="sku" id="order">
                                <dt class="sku-key">
                                        ${SPEC.key}
                                </dt>
                                <c:forEach items="${SPEC.value}" var="x">
                                    <dd class="btn-allocate btn-normal">${x}</dd>
                                </c:forEach>


                            </dl>
                        </c:forEach>

                    </div>

                    <div class="commodityCount">
                        <p class="inventorys">库存 <span
                                class="inventory">${requestScope.commodity.commodityInventory}</span> 件
                        </p>

                        <p class="sales">销量 <span class="sale">${requestScope.commodity.commoditySales}</span>件</p>
                    </div>

                    <div class="num clearfix">
                        <img class="fl sub" src="${path}/static/img/temp/sub.jpg">
                        <span class="fl cart-commodity-count" contentEditable="true">1</span>
                        <img class="fl add" src="${path}/static/img/temp/add.jpg">
                        <p class="please fl">请选择商品属性!</p>
                    </div>

                </div>
                <div class="btns clearfix">
                    <a href="#2"><p class="buy fl">立即购买</p></a>
                    <%--                    <a href="javascript:;"><p class="cart fr add-cart">加入购物车</p></a>--%>
                    <a href="javascript:;"><span class="add-cart">加入购物车</span></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="introMsg wrapper clearfix">
    <div class="msgL fl">
        <div class="msgTit clearfix">
            <a class="on">商品详情</a>
            <a>所有评价</a>
        </div>
        <div class="msgAll">
            <div class="msgImgs">
                <c:forEach items="${requestScope.commodity.commodityDetails}" var="de" varStatus="i">

                    <c:if test="${de.IMG_ORDER == i.count}">
                        <img class="zj" src="${de.IMG_URL}"/>
                        <%--						<img class="zj" src="https://zhangjia.oss-cn-qingdao.aliyuncs.com/img/web/iphoneXsMax2.jpg"--%>

                    </c:if>
                </c:forEach>

            </div>
            <div class="eva">
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per01.jpg">
                    <div class="perR fl">
                        <p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix">
                            <p><img src="${path}/static/img/temp/eva01.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva02.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva03.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva04.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva05.jpg"></p>
                        </div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per02.jpg">
                    <div class="perR fl">
                        <p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per01.jpg">
                    <div class="perR fl">
                        <p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix">
                            <p><img src="${path}/static/img/temp/eva01.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva02.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva03.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva04.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva05.jpg"></p>
                        </div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per02.jpg">
                    <div class="perR fl">

                        <p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per01.jpg">
                    <div class="perR fl">
                        <p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix">
                            <p><img src="${path}/static/img/temp/eva01.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva02.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva03.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva04.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva05.jpg"></p>
                        </div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per02.jpg">
                    <div class="perR fl">
                        <p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per01.jpg">
                    <div class="perR fl">
                        <p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix">
                            <p><img src="${path}/static/img/temp/eva01.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva02.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva03.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva04.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva05.jpg"></p>
                        </div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per02.jpg">
                    <div class="perR fl">
                        <p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per01.jpg">
                    <div class="perR fl">
                        <p>馨***呀</p>
                        <p>不好意思评价晚了，产品很好，价格比玻璃品便宜，没有我担心的杂色，发货快，包装好，全5分</p>
                        <div class="clearfix">
                            <p><img src="${path}/static/img/temp/eva01.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva02.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva03.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva04.jpg"></p>
                            <p><img src="${path}/static/img/temp/eva05.jpg"></p>
                        </div>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
                <div class="per clearfix">
                    <img class="fl" src="${path}/static/img/temp/per02.jpg">
                    <div class="perR fl">
                        <p>么***周</p>
                        <p>花瓶超级棒，我看图以为是光面的，收货发现是磨砂，但感觉也超有质感，很喜欢。磨砂上面还有点纹路，不过觉得挺自然的，不影响美观。包装也很好，绝对不会磕碎碰坏，好评！</p>
                        <p><span>2016年12月27日08:31</span><span>颜色分类：大中小三件套（不含花）</span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="msgR fr">
        <h4>为你推荐</h4>
        <div class="seeList">
            <a href="#">
                <dl>
                    <dt><img src="${path}/static/img/temp/see01.jpg"></dt>
                    <dd>【最家】复古文艺风玻璃花瓶</dd>
                    <dd>￥193.20</dd>
                </dl>
            </a>
            <a href="#">
                <dl>
                    <dt><img src="${path}/static/img/temp/see02.jpg"></dt>
                    <dd>【最家】复古文艺风玻璃花瓶</dd>
                    <dd>￥193.20</dd>
                </dl>
            </a>
            <a href="#">
                <dl>
                    <dt><img src="${path}/static/img/temp/see03.jpg"></dt>
                    <dd>【最家】复古文艺风玻璃花瓶</dd>
                    <dd>￥193.20</dd>
                </dl>
            </a>
            <a href="#">
                <dl>
                    <dt><img src="${path}/static/img/temp/see04.jpg"></dt>
                    <dd>【最家】复古文艺风玻璃花瓶</dd>
                    <dd>￥193.20</dd>
                </dl>
            </a>
        </div>

    </div>
</div>
<div class="like">
    <h4>猜你喜欢</h4>
    <div class="bottom">
        <div class="hd">
            <span class="prev"><img src="${path}/static/img/temp/prev.png"></span>
            <span class="next"><img src="${path}/static/img/temp/next.png"></span>
        </div>
        <div class="${path}/static/imgCon bd">
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
<!--返回顶部-->
<div class="gotop">
    <a href="cart.html">
        <dl class="goCart">
            <dt><img src="${path}/static/img/gt1.png"/></dt>
            <dd>去购<br/>物车</dd>
            <span>1</span>
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
    <a href="#" class="toptop" style="display: none;">
        <dl>
            <dt><img src="${path}/static/img/gt4.png"/></dt>
            <dd>返回<br/>顶部</dd>
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
    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div>

</body>
</html>

