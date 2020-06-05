<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>cart</title>
    <link rel="stylesheet" type="text/css" href="${path}/static/layui/css/layui.css"/>
    <script src="${path}/static/layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <jsp:include page="public-static-file.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/cart.css"/>
    <script src="${path}/static/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${path}/static/js/cart.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">


        $(function () {
            /*-------------------------------------------------商品全选开始-------------------------------------------------*/

            //计算总价
            function jisuan() {
                var all = 0;
                var len = $(".th input[type='checkbox']:checked").length;
                if (len == 0) {
                    $("#all").text('￥' + parseFloat(0).toFixed(2));
                } else {
                    $(".th input[type='checkbox']:checked").each(function () {
                        //获取小计里的数值
                        var sAll = $(this).parents(".pro").siblings('.sAll').text().substring(1);
                        //累加
                        all += parseFloat(sAll);
                        //赋值
                        $("#all").text('￥' + all.toFixed(2));
                    })
                }

            }

            //计算总共几件商品
            function zg() {
                var zsl = 0;
                var index = $(".th input[type='checkbox']:checked").parents(".th").find(".num .cart-num");
                var len = index.length;
                if (len == 0) {
                    $("#sl").text(0);
                } else {
                    index.each(function () {
                        zsl += parseInt($(this).val());
                        $("#sl").text(zsl);
                    })
                }
                if ($("#sl").text() > 0) {
                    $(".count").css("background", "#c10000");
                } else {
                    $(".count").css("background", "#8e8e8e");
                }
            }


            $("input[type='checkbox']").on('click', function () {

                var sf = $(this).is(":checked");
                var sc = $(this).hasClass("checkAll");
                //如果是选中
                if (sf) {
                    //如果选中的是全选
                    if (sc) {
                        $("input[type='checkbox']").each(function () {
                            var val = $(this).val();
                            //已经失效的不选择
                            //:disabled匹配所有不可用元素
                            if (!$(this).is(':disabled')) {
                                this.checked = true;

                                <%--${pageContext.request.setAttribute("commoditySKUs",)}--%>
                                <%--alert(${sessionScope.commoditySKUs})--%>
                            }

                        });
                        zg();
                        jisuan();
                    } else {
                        $(this).checked = true;
                        var len = $("input[type='checkbox']:checked").length;
                        var len1 = $("input").length - 1;
                        if (len == len1) {
                            $("input[type='checkbox']").each(function () {
                                this.checked = true;
                            });
                        }
                        zg();
                        jisuan();
                    }
                    //    如果是取消勾选
                } else {
                    //如果取消勾选的是全选按钮
                    if (sc) {
                        $("input[type='checkbox']").each(function () {
                            this.checked = false;
                        });
                        zg();
                        jisuan();
                    } else {
                        $(this).checked = false;
                        var len = $(".th input[type='checkbox']:checked").length;
                        var len1 = $("input").length - 1;
                        if (len < len1) {
                            $('.checkAll').attr("checked", false);
                        }
                        zg();
                        jisuan();
                    }
                }

            });


            /*-------------------------------------------------商品全选结束--------------------------------------------------*/

            /*-------------------------------------------------删除商品开始-------------------------------------------------*/
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
                        traditional: true,
                        success: function (res) {
                            if (res.isLogin === false) {

                                location = "${path}/login?uri=${path}/cart";
                            } else {
                                if (res.success) {
                                    layer.msg('删除成功', {time: 300, anim: 1});
                                    remove.remove();
                                } else {
                                    layer.msg('删除失败');
                                }

                            }

                        }

                    });
                    layer.close(index);
                });

            });
            /*-------------------------------------------------删除商品结束-------------------------------------------------*/

            /*-------------------------------------------------删除全部商品开始-------------------------------------------------*/
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
                            url: "${path}/cart/delete",
                            type: "get",
                            data: {
                                SKUIds: SKUIds
                            },
                            traditional: true,
                            success: function (res) {
                                if (res.isLogin === false) {

                                    location = "${path}/login?uri=${path}/cart";
                                } else {
                                    if (res.success) {
                                        layer.msg('删除成功', {time: 300, anim: 1});
                                        remove.remove();
                                    } else {
                                        layer.msg('删除失败');
                                    }
                                }


                            }

                        });

                        layer.close(index);
                    });
                }

            });
            /*-------------------------------------------------删除全部商品结束-------------------------------------------------*/

            /*-------------------------------------------------更改商品数量开始-------------------------------------------------*/

            $(".cart-add").click(function () {
                var SKUId = $(this).parent().parent().parent().children(":first").children(".fl").children("input").val();
                var action = "add";
                var thiss = $(this);

                $.ajax({
                    url: "${path}/updateCount",
                    type: "get",
                    data: {
                        SKUId: SKUId,
                        action: action,
                        count: 1
                    },
                    success: function (res) {
                        if (res.isLogin === false) {

                            location = "${path}/login?uri=${path}/cart";
                        } else {
                            if (res.success) {
                                var nowCartCount = $(thiss).siblings("input").val();
                                console.log(nowCartCount)
                                $(thiss).siblings("input").val(++nowCartCount);
                                console.log()
                                var price = $(thiss).parent().parent().siblings(".cart-price").children("span").text();

                                var allPrice = floatObj.multiply(parseFloat(price), nowCartCount);
                                $(thiss).parent().parent().siblings(".sAll").children("span").text(allPrice);
                                jisuan();

                            } else {
                                layer.msg(res.error)
                            }
                        }


                    }
                });
            });


            $(".cart-sub").click(function () {
                var SKUId = $(this).parent().parent().parent().children(":first").children(".fl").children("input").val();
                var action = "sub";
                var thiss = $(this);
                $.ajax({
                    url: "${path}/updateCount",
                    type: "get",
                    data: {
                        SKUId: SKUId,
                        action: action,
                        count: 1
                    },
                    success: function (res) {
                        if (res.isLogin === false) {

                            location = "${path}/login?uri=${path}/cart";
                        } else {
                            console.log(res.success === true)
                            if (res.success) {
                                var nowCartCount = $(thiss).siblings("input").val();
                                console.log(nowCartCount)
                                $(thiss).siblings("input").val(--nowCartCount);
                                console.log()
                                var price = $(thiss).parent().parent().siblings(".cart-price").children("span").text();

                                var allPrice = floatObj.multiply(parseFloat(price), nowCartCount);
                                $(thiss).parent().parent().siblings(".sAll").children("span").text(allPrice);
                                jisuan()

                            } else {
                                layer.msg(res.error)
                            }
                        }


                    }
                });
            });


            $(".cart-num").each(function () {
                $(this).blur(function () {
                    var count = $(this).val();
                    var thiss = $(this);
                    var action = 'input';
                    var SKUId = $(this).parent().parent().parent().children(":first").children(".fl").children("input").val();
                    $.ajax({
                        url: "${path}/updateCount",
                        type: "get",
                        data: {
                            SKUId: SKUId,
                            action: action,
                            count: count,
                        },
                        success: function (res) {
                            if (res.isLogin === false) {

                                location = "${path}/login?uri=${path}/cart";
                            } else {
                                if (res.success) {
                                    var nowCartCount = $(thiss).val();
                                    console.log(nowCartCount)
                                    // $(thiss).siblings("input").val(++nowCartCount);
                                    console.log()
                                    var price = $(thiss).parent().parent().siblings(".cart-price").children("span").text();

                                    var allPrice = floatObj.multiply(parseFloat(price), nowCartCount);
                                    $(thiss).parent().parent().siblings(".sAll").children("span").text(allPrice);
                                    jisuan();

                                } else {
                                    layer.msg(res.error)
                                    $(thiss).val(res.skuInventory);
                                }
                            }


                        }
                    });
                });
            });
            //------------------------------------------------------------------------------------------------------------
            $(".cart-num").each(function () {

                var count = $(this).val();
                var thiss = $(this);
                var action = 'input';
                var SKUId = $(this).parent().parent().parent().children(":first").children(".fl").children("input").val();
                $.ajax({
                    url: "${path}/updateCount",
                    type: "get",
                    data: {
                        SKUId: SKUId,
                        action: action,
                        count: count,
                    },
                    success: function (res) {
                        if (res.isLogin === false) {

                            location = "${path}/login?uri=${path}/cart";
                        } else {
                            if (res.success) {
                                var nowCartCount = $(thiss).val();
                                console.log(nowCartCount)
                                // $(thiss).siblings("input").val(++nowCartCount);
                                console.log()
                                var price = $(thiss).parent().parent().siblings(".cart-price").children("span").text();

                                var allPrice = floatObj.multiply(parseFloat(price), nowCartCount);
                                $(thiss).parent().parent().siblings(".sAll").children("span").text(allPrice);
                                jisuan();

                            } else {

                                layer.tips('超出库存，已经为您更改为最大库存', thiss, {
                                    tips: [1, '#3595CC'],
                                    time: 1500
                                });
                                $(thiss).val(res.skuInventory);
                            }
                        }


                    }
                });

            });

            /*-------------------------------------------------更改商品数量结束-------------------------------------------------*/
            var floatObj = function () {

                /*
                 * 判断obj是否为一个整数
                 */
                function isInteger(obj) {
                    return Math.floor(obj) === obj
                }

                /*
                 * 将一个浮点数转成整数，返回整数和倍数。如 3.14 >> 314，倍数是 100
                 * @param floatNum {number} 小数
                 * @return {object}
                 *   {times:100, num: 314}
                 */
                function toInteger(floatNum) {
                    var ret = {times: 1, num: 0}
                    var isNegative = floatNum < 0
                    if (isInteger(floatNum)) {
                        ret.num = floatNum
                        return ret
                    }
                    var strfi = floatNum + ''
                    var dotPos = strfi.indexOf('.')
                    var len = strfi.substr(dotPos + 1).length
                    var times = Math.pow(10, len)
                    var intNum = parseInt(Math.abs(floatNum) * times + 0.5, 10)
                    ret.times = times
                    if (isNegative) {
                        intNum = -intNum
                    }
                    ret.num = intNum
                    return ret
                }

                /*
                 * 核心方法，实现加减乘除运算，确保不丢失精度
                 * 思路：把小数放大为整数（乘），进行算术运算，再缩小为小数（除）
                 *
                 * @param a {number} 运算数1
                 * @param b {number} 运算数2
                 * @param digits {number} 精度，保留的小数点数，比如 2, 即保留为两位小数
                 * @param op {string} 运算类型，有加减乘除（add/subtract/multiply/divide）
                 *
                 */
                function operation(a, b, digits, op) {
                    var o1 = toInteger(a)
                    var o2 = toInteger(b)
                    var n1 = o1.num
                    var n2 = o2.num
                    var t1 = o1.times
                    var t2 = o2.times
                    var max = t1 > t2 ? t1 : t2
                    var result = null
                    switch (op) {
                        case 'add':
                            if (t1 === t2) { // 两个小数位数相同
                                result = n1 + n2
                            } else if (t1 > t2) { // o1 小数位 大于 o2
                                result = n1 + n2 * (t1 / t2)
                            } else { // o1 小数位 小于 o2
                                result = n1 * (t2 / t1) + n2
                            }
                            return result / max
                        case 'subtract':
                            if (t1 === t2) {
                                result = n1 - n2
                            } else if (t1 > t2) {
                                result = n1 - n2 * (t1 / t2)
                            } else {
                                result = n1 * (t2 / t1) - n2
                            }
                            return result / max
                        case 'multiply':
                            result = (n1 * n2) / (t1 * t2)
                            return result
                        case 'divide':
                            result = (n1 / n2) * (t2 / t1)
                            return result
                    }
                }

                // 加减乘除的四个接口
                function add(a, b, digits) {
                    return operation(a, b, digits, 'add')
                }

                function subtract(a, b, digits) {
                    return operation(a, b, digits, 'subtract')
                }

                function multiply(a, b, digits) {
                    return operation(a, b, digits, 'multiply')
                }

                function divide(a, b, digits) {
                    return operation(a, b, digits, 'divide')
                }

                // exports
                return {
                    add: add,
                    subtract: subtract,
                    multiply: multiply,
                    divide: divide
                }
            }();
            /*-------------------------------------------------结束-------------------------------------------------*/
            /*-------------------------------------------------结算开始-------------------------------------------------*/
            $(".count").click(function () {
                var checkedCount = $(":checkbox[class='cart-sku-id']:checked").length;
                var checkedCommoditySKU = $(":checkbox[class='cart-sku-id']:checked");
                var url = "${path}/settlement?"
                if (checkedCount === 0) {
                    layer.msg("请选择商品")
                } else {
                    checkedCommoditySKU.each(function () {
                        url += "SKUIds=" + this.value + "&";
                    });
                    console.log(url);
                    location = url;
                }

            });
            /*-------------------------------------------------结算结束-------------------------------------------------*/

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
					<a href="index.jsp"><img class="fl" src="${path}/static/img/temp/logo.png"></a>
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
                    <div class="cart-price">￥<span>${commodity.SKU_PRESENT_PRICE}</span></div>
                    <div class="number">
                        <p class="num clearfix">
                            <button type="button" class="layui-btn layui-btn-primary layui-btn layui-btn-xs cart-sub">
                                <i class="layui-icon">&#xe603;</i>
                            </button>

                            <input type="number" value="${commodity.COMMODITY_COUNT}" autocomplete="off"
                                   class="cart-num" style="text-align:center"
                                   onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn layui-btn-xs cart-add">
                                <i class="layui-icon">&#xe602;</i>
                            </button>
                        </p>
                    </div>
                    <div class="price sAll">￥<span>${commodity.SKU_PRESENT_PRICE * commodity.COMMODITY_COUNT}</span>
                    </div>
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
                <a href="javascript:;" class="count">结算</a>
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
                    <div class="sku-price">￥${commodity.SKU_PRESENT_PRICE}</div>
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
