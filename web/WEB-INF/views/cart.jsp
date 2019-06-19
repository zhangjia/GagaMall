<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>cart</title>
    <jsp:include page="public-static-file.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css"/>
    <script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${path}/static/js/cart.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function () {
            $(".cart-del").click(function () {
                //必须先把this保存，否则获取的是layer的this
                var thiss = $(this);
                layer.confirm('确定要删除吗?', {icon: 3, title: '嘎嘎警告'}, function (index) {
                    //获取要删除的商品ID
                    var SKUIds = [];
                    var SKUId = thiss.parent().parent().children(":first").children(":first").children("input").val();
                    SKUIds.push(SKUId);
                    //获取用于remove的元素
                    var remove = thiss.parent().parent();
                    $.ajax({
                        url: "${path}/cart/delete",
                        type: "get",
                        data: {
                            SKUIds: SKUIds
                        },
                        traditional:true,
                        success: function (res) {

                            if (res.success) {
                                layer.msg('删除成功', {time: 300, anim: 1});
                                remove.remove();
                            } else {
                                layer.msg('删除失败');
                            }

                        }

                    });
                    layer.close(index);
                });

            });

            $(".cart-all-del").click(function () {
                //必须先把this保存，否则获取的是layer的this

                var thiss = $(this);

                if ($(".th input[type='checkbox']:checked").length == 0) {
                    layer.alert("选择要删除的商品")
                } else {
                    layer.confirm('确定要删除吗?', {icon: 3, title: '嘎嘎警告'}, function (index) {
                        //获取要删除的商品ID

                        var SKUIds = [];
                        // layer.alert( $(".th input[type='checkbox']:checked").length);
                        $(".th input[type='checkbox']:checked").each(function (j) {
                            SKUIds.push($(this).val())
                            indexs = $(this).parents('.th').index() - 1;
                            $(".th").eq(indexs).remove();
                            if ($(".th").length == 0) {
                                $(".table .goOn").show();
                            }
                        })

                        console.log(SKUIds)


                        var SKUId = thiss.parent().parent().children(":first").children(":first").children("input").val();
                        //获取用于remove的元素
                        var remove = thiss.parent().parent();
                        $.ajax({
                            url:"${path}/cart/delete",
                            type: "get",
                            data: {
                                SKUIds: SKUIds
                            },
                            traditional:true,
                            success: function (res) {
                                if (res.success) {
                                    layer.msg('删除成功', {time: 300, anim: 1});
                                    remove.remove();
                                } else {
                                    layer.msg('删除失败');
                                }

                            }

                        });

                        layer.close(index);
                    });
                }

            });
        });
    </script>
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
            <div class="test">单价</div>
            <div>数量</div>
            <div>小计</div>
            <div>操作</div>
        </div>
        <c:forEach items="${requestScope.commodities}" var="commodity">

            <c:if test="${commodity.SKU_INVENTORY > 0}">
                <div class="th">
                    <div class="pro clearfix">
                        <label class="fl">
                            <input class="cart-sku-id" type="checkbox" value="${commodity.SKU_ID}"/>
                            <span></span>
                        </label>
                        <a class="fl" href="#">
                            <dl class="clearfix">
                                <dt class="fl"><img src="${commodity.IMG_URL}" class="cart-commodity-img"></dt>
                                <dd class="fl">
                                    <p class="cart-commodity-name">${commodity.COMMODITY_NAME}</p>
                                    <p>
                                            ${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(commodity.SKU_VALUE, '{', ''),'}' ,'' ),'"' , ''),',' ,'<br />' ),':' ,'：    ' )}
                                                    </p>
                                            <%--										<p>白色瓷瓶+白色串枚</p>--%>
                                </dd>
                            </dl>
                        </a>
                    </div>
                    <div class="price">￥${commodity.SKU_PRESENT_PRICE}</div>
                    <div class="number">
                        <p class="num clearfix">
                            <img class="fl sub" src="${path}/static/img/temp/sub.jpg">
                            <span class="fl">${commodity.COMMODITY_COUNT}</span>
                            <img class="fl add" src="${path}/static/img/temp/add.jpg">
                        </p>
                    </div>
                    <div class="price sAll">￥${commodity.SKU_PRESENT_PRICE * commodity.COMMODITY_COUNT} </div>
<%--                    正常商品删除--%>
                    <div class="price"><a class="cart-del" href="javascript:;">删除</a></div>
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
                <a href="#" class="cart-all-del">删除</a>
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

            <c:if test="${commodity.SKU_INVENTORY <= 0}">

                <hr/>
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
                                            ${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(commodity.SKU_VALUE, '{', ''),'}' ,'' ),'"' , ''),',' ,'<br />' ),':' ,'：    ' )}
                                                    </p>
                                            <%--										<p>白色瓷瓶+白色串枚</p>--%>
                                </dd>
                            </dl>
                        </a>
                    </div>
                    <div class="price">￥${commodity.SKU_PRESENT_PRICE}</div>
                    <div class="number">
                        <p class="num clearfix">
                            <img class="fl sub" src="${path}/static/img/temp/sub.jpg">
                            <span class="fl">0</span>
                            <img class="fl add" src="${path}/static/img/temp/add.jpg">
                        </p>
                    </div>
                    <div class="price sAll">￥${commodity.SKU_PRESENT_PRICE * commodity.COMMODITY_COUNT} </div>
                        <%--							<div class="price"><a class="del cart-del" href="#2">删除</a></div>--%>
<%--                    失效删除--%>
                    <div class="price"><a class="cart-del" href="#2">删除</a></div>
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
    <a href="#" class="toptop" style="display: none;">
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
<!----------------mask------------------->
<div class="mask"></div>
<!-------------------mask内容------------------->
<div class="proDets">
    <img class="off" src="${path}/static/img/temp/off.jpg"/>
    <div class="proCon clearfix">
        <div class="proImg fr">
            <img class="list" src="${path}/static/img/temp/proDet.jpg"/>
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
                    <p class="fl on"><img src="${path}/static/img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花"
                                          data-src="${path}/static/img/temp/proBig01.jpg"></p>
                    <p class="fl"><img src="${path}/static/img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草"
                                       data-src="${path}/static/img/temp/proBig02.jpg"></p>
                    <p class="fl"><img src="${path}/static/img/temp/prosmall03.jpg" alt="20支快乐花"
                                       data-src="${path}/static/img/temp/proBig03.jpg"></p>
                    <p class="fl"><img src="${path}/static/img/temp/prosmall04.jpg" alt="20支兔尾巴草"
                                       data-src="${path}/static/img/temp/proBig04.jpg"></p>
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
    <img class="off" src="${path}/static/img/temp/off.jpg"/>
</div>

</body>
</html>
