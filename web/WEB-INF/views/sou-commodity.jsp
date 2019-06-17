<%--<html>
<head>
    <meta charset="utf-8"/>
    <title>发布/编辑/更新-用户中心</title>
    <meta name="keywords" content="DeathGhost"/>
    <meta name="description" content="DeathGhost"/>
    <meta name="author" content="DeathGhost,deathghost@deathghost.cn">
    <link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">

    <script>
        $(document).ready(function () {
            $("nav .indexAsideNav").hide();
            $("nav .category").mouseover(function () {
                $(".asideNav").slideDown();
            });
            $("nav .asideNav").mouseleave(function () {
                $(".asideNav").slideUp();
            });
            $(".switchNav li").click(function () {
                $(this).addClass("active").siblings().removeClass("active");
            });
            $("#chanpin").click(function () {
                $(".inputWrap input[type='text']").attr("placeholder", "输入产品关键词或货号");
            });
            $("#shangjia").click(function () {
                $(".inputWrap input[type='text']").attr("placeholder", "输入商家店铺名");
            });
            $("#zixun").click(function () {
                $(".inputWrap input[type='text']").attr("placeholder", "输入关键词查找文章内容");
            });
            $("#wenku").click(function () {
                $(".inputWrap input[type='text']").attr("placeholder", "输入关键词查找文库内容");
            });
        });
    </script>
</head>
<body>


</body>
</html>--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>最家</title>
    <link rel="stylesheet" type="text/css" href="${path}/static/layui/css/layui.css"/>
    <script src="${path}/static/layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <jsp:include page="public-static-file.jsp"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/mygxin.css"/>
    <script src="${path}/static/js/user.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/sou-commodity.css"/>
    <script src="https://cdn.bootcss.com/layer/2.3/layer.js"></script>

    <script>

        $(function () {
            $(".two").hide();
            layui.use('form', function () {
                var form = layui.form;

                //各种基于事件的操作，下面会有进一步介绍

                form.on('select(zj)', function (data) {
                    var value = data.value;
                    $('.zjtest option').not(":first").remove();
                    // $(".secMenuValue").addClass(data.value).removeClass("secMenuValue");
                    <c:forEach items="${requestScope.nav}" var="nav" varStatus="i">
                    if('${nav.firstMenuChineseName}' == value){
                    <c:forEach items="${nav.secMenu}" var="secNav">
                        var option = "<option value='${secNav.SEC_MENU_ID}'>${secNav.SEC_MENU_CHINESE_NAME}</option>";
                        $(".secMenuValue").after(option);
                    </c:forEach>
                    }
                    </c:forEach>
                    form.render(); //

                    $(".two").show(1000);
                });
            });

            // $(".shuxing").addClass("layui-btn-disabled");

            $(".guige").click(function() {

                // $(".shuxing").removeClass("layui-btn-disabled");
                if($(".number:last").val() == "") {
                    alert(1)
                } else {
                    var htm = "";
                    htm += "    <div class='layui-form-item spgg'>";
                    htm += "    <label class='layui-form-label '>商品规格</label>";
                    htm += "    <div class='layui-input-block'>";
                    htm += " <input type='text' name='title' required lay-verify='required' placeholder='请输入' autocomplete='off' class='layui-input commodity-specifications'>";
                    htm += "</div>";
                    htm += "</div>";



                    $('.buttons').before(htm);
                }
                // $(".guige").addClass("layui-btn-disabled");

        });

            $(".shuxing").click(function() {
                // $(".guige").removeClass("layui-btn-disabled");
                if($(".number:last").val() == "") {
                    alert(1)
                } else {
                    var htm = "";
                    // htm += "    <div class='layui-form-item spsx'>";
                    // htm += "    <label class='layui-form-label '>商品属性</label>";
                    htm += "    <div class='layui-input-inline layui-col-md4 spsx'>";
                    htm += " <input type='text' name='title' required lay-verify='required' placeholder='请输入' autocomplete='off' class='layui-input '>";
                    htm += "</div>";
                    $('.spgg:last').append(htm);
                }

                // $(".shuxing").addClass("layui-btn-disabled");
            });

            $(".bianli").click(function() {
                var g = $(".commodity-specifications").length;
                var s = $(".layui-input specification-attribute").length;

                var length = 1;
                var x = 1;

                var listValues =[];
                var listKey = [];
                $(".spgg").each(function () {
                    listKey.push($(this).children(".layui-input-block").children("input").val());
                    var list =[];
                    var t =  $(this).children(".spsx").length;
                    $(this).children(".spsx ").each(function () {
                        console.log($(this).children().val());
                        list.push($(this).children().val());
                    });

                    listValues.push(list);

                    if(t != 0) {
                        x *= t;
                    }


                });


                // //测试数据
                // var listValues =[];
                // var listKey = [];
                // var list =[]; list.push(1);list.push(2);listValues.push(list);
                // var list2 =[]; list2.push(3);list2.push(4);listValues.push(list2);
                // listKey.push("a"); listKey.push("b");
                console.log(listValues)
                console.log(listKey)
                for(var i=0;i<listValues.length;i++){
                    for (var j = 0; j < listValues[i].length; j++) {

                    }
                }
                function calcDescartes (array) {
                    if (array.length < 2) return array[0] || [];
                    return [].reduce.call(array, function (col, set) {
                        var res = [];
                        col.forEach(function (c) {
                            set.forEach(function (s) {
                                var t = [].concat(Array.isArray(c) ? c : [c]);
                                t.push(s);
                                res.push(t);
                            })
                        });
                        return res;
                    });
                }

                var ss = calcDescartes(listValues);

                for(var i = 0; i < x; i++){
                    var htm = "";
                    htm += "    <div class='layui-form-item '>";
                    htm += "    <label class='layui-form-label '>商品SKU</label>";
                    htm += "    <div class='layui-input-inline'>";
                    htm += " <input type='text' name='title' required lay-verify='required' placeholder='请输入' autocomplete='off' class='layui-input c-sku' value="+ss[i]+ " disabled>";
                    htm += "</div>";
                    htm += "    <div class='layui-input-inline layui-col-md4 '>";
                    htm += " <input type='text' name='title' required lay-verify='required' placeholder='库存' autocomplete='off' class='layui-input i-and-p'>";
                    htm += "</div>";
                    htm += "    <div class='layui-input-inline layui-col-md4 '>";
                    htm += " <input type='text' name='title' required lay-verify='required' placeholder='价格' autocomplete='off' class='layui-input i-and-p'>";
                    htm += "</div>";
                    htm += "</div>";

                    $('.buttons').before(htm);
                }

            });

        });
    </script>
</head>
<body>
<jsp:include page="head.jsp"/>
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
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">

            <jsp:include page="personal-left.jsp"/>
        </div>
        <div class="you fl">

            <form class="layui-form" action="">
                <h2>添加商品</h2>
                <div class="layui-form-item">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="commodityName" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">一级菜单</label>
                    <div class="layui-input-block">
<%--                        <select name="firstMenuValue" lay-verify="required" lay-filter="zj">--%>
                        <select name="firstMenuId" lay-verify="required" lay-filter="zj">
                            <option value="">请选择</option>
                            <c:forEach items="${requestScope.nav}" var="nav" varStatus="i">
                                <option value="${nav.firstMenuChineseName}">${nav.firstMenuChineseName}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
                <div class="layui-form-item two">
                    <label class="layui-form-label">二级菜单</label>
                    <div class="layui-input-block">
<%--                        <select class="zjtest" name="secMenuValue" lay-verify="required">--%>
                        <select class="zjtest" name="secMenuId" lay-verify="required">
                            <option class="secMenuValue" value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品图</label>
                    <div class="layui-input-block">
                        <input type="text" name="commodityImg" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品详情图</label>
                    <div class="layui-input-block">
                        <input type="text" name="commodityDetails" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>


                <div class="layui-form-item buttons">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        <button type="button" class="layui-btn  layui-btn-warm guige">添加规格</button>
                        <button type="button" class="layui-btn  layui-btn-warm shuxing">添加属性</button>
                        <button type="button" class="layui-btn  layui-btn-warm bianli">规格确定</button>
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</div>

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
    <a href="#" class="toptop" style="display: none">
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
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div>
</body>
</html>
