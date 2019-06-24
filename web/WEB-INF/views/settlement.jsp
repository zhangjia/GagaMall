<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head lang="en">
    <style>
        .bj-order-pay{
            width: 410px;
            position: absolute;
            top: 30%;
            left: 50%;
            margin-top: -180px;
            margin-left: -220px;
            z-index: 30;
            background: #fff;
            border-radius: 10px;
            padding: 15px;
            display: none;
        }
    </style>
    <meta charset="utf-8"/>
    <title>order</title>

    <link rel="stylesheet" type="text/css" href="${path}/static/layui/css/layui.css"/>
    <script src="${path}/static/layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <jsp:include page="public-static-file.jsp"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/mygxin.css"/>
    <script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
    <script type="text/javascript"
            src="https://webapi.amap.com/maps?v=1.4.8&key=b03bbaf1ff4c80e018561e5eb1210906"></script>
    <script src="${path}/static/js/city.js" type="text/javascript" charset="utf-8"></script>
    <script>
        function pay(){

                $(".bj-order-pay").show();
                $(".mask-order-pay").show();

        }

        $(function () {

            $(".testjia").click(function () {
                alert($("#note").val())
            });


            
            $(".pay").click(function () {
                if ($(".addre.fl.on").length === 0) {
                    layer.msg("先选择地址")
                } else {


                    var addreddId = $(".addre.fl.on").children("p:hidden").text();
                    <%--var url = "${path}/pay?"--%>
                    var url = "${path}/submit?"
                    $(".msg ul").children("input:hidden").each(function () {
                        url += "SKUIds=" + this.value + "&";

                    });
                    url += "addressId=" + addreddId;
                    url +=  "&note=" + $("#note").val();
                    console.log(url);
                    $.ajax({
                        url: url,
                        type: "get",

                        success: function (res) {
                            <%--location = "${path}/pay";--%>
                            if(res.success === true ){
                                pay();
                            layer.msg("下单成功")
                            } else  {
                                layer.msg("下单失败")
                            }
                        }
                    });
                }


            });
        });
    </script>
</head>
<body>
<!----------------------------------------order------------------>
<jsp:include page="head.jsp"></jsp:include>
<jsp:include page="order-pay.jsp"></jsp:include>

<div class="order cart mt">
    <!-----------------site------------------->
    <div class="site">
        <p class="wrapper clearfix">
            <span class="fl">订单确认</span>
            <img class="top" src="${path}/static/img/temp/cartTop02.png">
        </p>
    </div>
    <!-----------------orderCon------------------->
    <div class="orderCon wrapper clearfix">
        <div class="orderL fl">
            <jsp:include page="address.jsp"></jsp:include>
            <h3 class="testjia">输入备注</h3>
<%--            <!--------way---------------->--%>
<%--            <div class="way clearfix">--%>
<%--                <img class="on jia-pay-style-banlance" src="${path}/static/img/temp/way01.jpg">--%>
<%--                <img src="${path}/static/img/temp/way02.jpg">--%>
<%--                <img src="${path}/static/img/temp/way03.jpg">--%>
<%--                <img src="${path}/static/img/temp/way04.jpg">--%>
<%--            </div>--%>
                    <div class="layui-form-item layui-form-text">
                        <div style="margin:10px" class="layui-input-block">
                            <textarea id="note" style=" min-height: 10px; margin-top: 20px" name="note" placeholder="请输入备注" class="layui-textarea"></textarea>
                        </div>
                    </div>

        </div>
        <div class="orderR fr">
            <div class="msg">
                <h3>订单内容<a href="cart.html" class="fr">返回购物车</a></h3>
                <!--------ul---------------->
                <c:forEach items="${requestScope.commoditySKUS}" var="commoditySKU">

                    <ul class="clearfix">
                        <li class="fl">
                            <img src="${commoditySKU.IMG_URL}" style="width: 87px;height: auto;">
                        </li>
                        <input type="hidden" value="${commoditySKU.SKU_ID}"/>
                        <li class="fl">
                            <p class="settlement-ellipsis">${commoditySKU.COMMODITY_NAME}</p>
                            <p>
                                <c:set var="string"
                                       value="${fn:replace(fn:replace(fn:replace(fn:replace(commoditySKU.SKU_VALUE, '\"', ''), '{', ''), '}', ''), ',', '    ')}"></c:set>
                                    ${string}
                            </p>
                            <p>数量：${commoditySKU.COMMODITY_COUNT}</p>
                        </li>
                        <li class="fr">￥${commoditySKU.SKU_PRESENT_PRICE}</li>
                    </ul>
                </c:forEach>
            </div>
            <!--------tips---------------->
            <div class="tips">
                <p><span class="fl">商品金额：</span><span
                        class="fr">￥${requestScope.total.SUM_COMMODITY_PRESENT_PRICE}</span></p>
                <p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>
                <c:if test="${requestScope.total.SUM_COMMODITY_PRESENT_PRICE >= 1000}">
                    <p><span class="fl">运费：</span><span class="fr">免运费</span></p>
                </c:if>
                <c:if test="${requestScope.total.SUM_COMMODITY_PRESENT_PRICE < 1000}">
                    <p><span class="fl">运费：</span><span class="fr">10元</span></p>
                </c:if>
            </div>
            <!--------tips count---------------->
            <div class="count tips">
                <p><span class="fl">商品总数：</span><span class="fr">${requestScope.total.SUM_COMMODITY_COUNT}</span></p>
                <p><span class="fl">支付金额：</span><span class="fr">￥${requestScope.total.SUM_COMMODITY_PAY_PRICE}</span>
                </p>
            </div>
            <!--<input type="button" name="" value="去支付"> -->
            <a id="" href="javascript:;" class="pay">去支付</a>
        </div>
    </div>
</div>
<!--编辑弹框-->
<!--遮罩-->

<%--------------------------------------------------------------------------%>



<%----------------------------------------------------------------------%>
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
<script src="${path}/static/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/static/js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
