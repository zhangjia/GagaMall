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



            // $(".shuxing").addClass("layui-btn-disabled");
            //商品规格，比如颜色，大小，版本等等
            $(".guige").click(function () {

                // $(".shuxing").removeClass("layui-btn-disabled");
                // $(this).addClass("layui-btn-disabled");
                if ($(".number:last").val() == "") {
                    alert(1)
                } else {

                    var htm = "";
                    htm += "    <div class='layui-form-item spgg'>";
                    htm += "    <label class='layui-form-label '>商品规格</label>";
                    htm += "    <div class='layui-input-block'>";
                    htm += " <input type='text' name='spgg' required lay-verify='required' placeholder='请输入' autocomplete='off' class='layui-input commodity-specifications'>";
                    htm += "</div>";
                    htm += "</div>";


                    $('.buttons').before(htm);
                }
                // $(".guige").addClass("layui-btn-disabled");

            });

            $(".shuxing").click(function () {
                // $(".guige").removeClass("layui-btn-disabled");
                if ($(".number:last").val() == "") {
                    alert(1)
                } else {
                    var htm = "";
                    // htm += "    <div class='layui-form-item spsx'>";
                    // htm += "    <label class='layui-form-label '>商品属性</label>";
                    htm += "    <div class='layui-input-inline layui-col-md4 spsx'>";
                    htm += " <input type='text' name='spsx' required lay-verify='required' placeholder='请输入' autocomplete='off' class='layui-input '>";
                    htm += "</div>";
                    $('.spgg:last').append(htm);
                }

                // $(".shuxing").addClass("layui-btn-disabled");
            });


            // var jsonSKUArray = [];
            var sss;
            var jsonAttrs;
            $(".bianli").click(function () {

                var x = 1;

                //用于生成笛卡尔积
                var listValues = [];


                $(".spgg").each(function () {
                    var jsongg = {}
                    //用于生成笛卡尔积
                    var list = [];

                    var t = $(this).children(".spsx").length;
                    var spggValue = $(this).children(".layui-input-block").children("input").val();
                    $(this).children(".spsx ").each(function () {
                        // console.log($(this).children().val());

                        var skuRecord = spggValue + ":" + ($(this).children().val().toString());
                        list.push(skuRecord);
                        // console.log(JSON.stringify(skuRecord))
                    });

                    listValues.push(list);


                    if (t != 0) {
                        x *= t;
                    }
                });


                // var sda = JSON.stringify(listValues).toString();
                // x = x.replace("[","");
                // console.log(x.replace("]",""));
                //"[[\"1:2\",\"1:3\"],[\"4:5\"]]"
                // ["a:1", "b:3"]


//-----------------------------------------生成attributeJson-------------------------------------------------------
                /*
                * 思路：
                * jsonAttr是最后生成的map的最外圈，也就是{....}
                * name就是商品的规格，也就是{"颜色":...,"容量":}，作为JsonAttr的key
                * jsonAttr2 就是每个商品规格下的属性，也就是["黑色","白色"]
                *  首先遍历所有的商品规格，将其值存入name
                * 然后遍历每个商品规格下的商品属性，将其值存入jsonAttr2，并将其作为jsonAttr的Value
                *
                * */
                var jsonAttr = {};

                //{"颜色":["黑色","白色"],"容量":["128G","256G"]}

                //遍历所有的商品规格
                $(".spgg").each(function () {
                    var jsonAttr2 = [];
                    // console.log($(this).children(".layui-input-block").children("input").val())
                    var name = $(this).children(".layui-input-block").children("input").val().toString();
                    //遍历当前商品规格下的商品属性
                    $(this).children(".spsx").each(function () {
                        // console.log($(this).children("input").val());
                        jsonAttr2.push($(this).children("input").val());
                    });

                    jsonAttr[name] = jsonAttr2;

                });

                //将生成的json添加到fom中，并且隐藏
                var jsonattr = "<input type='hidden' name='jsonAttribute' value='" + JSON.stringify(jsonAttr) + "'>";
                $(".layui-form").append(jsonattr);


                // -----------------------------------------生成attributeJson结束---------------------------------------------------
                function calcDescartes(array) {
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

                sss = ss;
                // console.log(ss)

                for (var i = 0; i < x; i++) {
                    var htm = "";
                    htm += "    <div class='layui-form-item create-sku'>";
                    htm += "    <label class='layui-form-label '>商品SKU</label>";
                    htm += "    <div class='layui-input-inline'>";
                    htm += " <input type='text' name='sku-value" + i + "' required lay-verify='required' placeholder='请输入' autocomplete='off' class='layui-input c-sku' value=" + ss[i] + " readonly='readonly'>";
                    htm += "</div>";
                    htm += "    <div class='layui-input-inline layui-col-md4 '>";
                    htm += " <input type='text' name='sku-inventory" + i + "' required lay-verify='required' placeholder='库存' autocomplete='off' class='layui-input i-and-p create-sku-inventory'>";
                    htm += "</div>";
                    htm += "    <div class='layui-input-inline layui-col-md4 '>";
                    htm += " <input type='text' name='sku-present-price" + i + "' required lay-verify='required' placeholder='价格' autocomplete='off' class='layui-input i-and-p create-sku-price'>";
                    htm += "</div>";
                    htm += "</div>";

                    $('.buttons').before(htm);

                    jsonAttrs = jsonAttr;

                }

                //---------------------------------------sku生成开始---------------

            });
            //---------------------------------------sku生成结束---------------


            // $(".submit").click(function () {

            //{"颜色":"白色","容量":"256G"}
            //["1", "3"]
            //颜色:黄,大小:b

            // $(".submits").click(function () {

            $(".as").click(function () {
                var sonspts = [];
                var spts = {};

                // var data = $("form").serialize();
                $("input[name='spt']").each(function () {

                    sonspts.push($(this).val());
                });
                var imgType = "spt";
                spts[imgType] = sonspts;

                var sonspxqt = [];
                var spxqts = {};
                // var data = $("form").serialize();
                $("input[name='spxqt']").each(function () {

                    sonspxqt.push($(this).val());
                });
                imgType = "sptxqt";
                spxqts[imgType] = sonspxqt;
                console.log(spts)
                console.log(spxqts)
                console.log($("input[name='firstMenuId']").val())
                console.log($("input[name='secMenuId']").val())

            });
            layui.use('form', function () {
                var form = layui.form;
                form.on('submit(formDemo)', function (data) {
                    var sonspts = [];
                    var spts = {};

                    // var data = $("form").serialize();
                    $("input[name='spt']").each(function () {

                        sonspts.push($(this).val());
                    });
                    var imgType = "spt";
                    spts[imgType] = sonspts;

                    var sonspxqt = [];
                    var spxqts = {};
                    // var data = $("form").serialize();
                    $("input[name='spxqt']").each(function () {

                        sonspxqt.push($(this).val());
                    });
                    imgType = "spxqt";
                    spxqts[imgType] = sonspxqt;

                    var jsonattr1 = "<input type='hidden' name='jsonspt' value='" + JSON.stringify(spts) + "'>";
                    $(".layui-form").append(jsonattr1);
                    var jsonattr2 = "<input type='hidden' name='jsonspxqt' value='" + JSON.stringify(spxqts) + "'>";
                    $(".layui-form").append(jsonattr2);
                    var skuRecords = [];

                    //----------------------------------------------
                    var jsonSKUValue = {};

                    var jsonsss = JSON.stringify(sss) + "";
                    console.log(JSON.stringify(sss) + "---");
                    console.log(JSON.stringify(jsonSKUValue) + "---");
                    jsonsss = jsonsss.replace(/\[/g, "");
                    jsonsss = jsonsss.replace(/\]/g, "");
                    jsonsss = jsonsss.replace(/:/g, '":"');
                    // "1":"2","4":"5","1":"2","4":"6","1":"3","4":"5","1":"3","4":"6"
                    var leg = $(".spgg").length;

                    var sc = 0;

                    var splits = jsonsss.split(",");
                    for (var m = 0; m < splits.length; m += leg) {
                        console.log(m)
                        var skuRecord = {};
                        var strings = "";
                        for (var y = m; y < m + leg; y++) {
                            if (y + 1 < m + leg) {

                                strings += (splits[y] + ",");
                            } else {
                                strings += splits[y];
                            }

                        }

                        console.log("{" + strings + "}")
                        skuRecord.skuvalue = JSON.parse("{" + strings + "}");


                        // var inventory = $(".create-sku:eq("+(sc+1)+")").children(":eq(2)").children("input").val();
                        //$("ul li:nth-child(2)")
                        var inventory = $(".create-sku:eq(" + (sc) + ")").children(":eq(2)").children("input").val();
                        var price = $(".create-sku:eq(" + (sc++) + ")").children(":eq(3)").children("input").val()
                        // var price = $(".create-sku:eq("+sc+++")").children(":eq(3)").children("input").val()
                        skuRecord.inventory = inventory;
                        skuRecord.price = price;


                        skuRecords.push(skuRecord);


                    }


                    console.log(JSON.stringify(skuRecords))

                    console.log(spts)
                    console.log(spxqts)
                    console.log($("input[name='firstMenuId']").val())
                    console.log($("input[name='secMenuId']").val())
                    var map = data.field;


                    var datas = {
                        commodityName: $("input[name='commodityName']").val(),
                        firstMenuId: map.firstMenuId,
                        secMenuId: map.secMenuId,
                        jsonAttr: $("input[name='jsonAttribute']").val(),
                        spt: $("input[name='jsonspt']").val(),
                        spxqt: $("input[name='jsonspxqt']").val(),
                        skuRecords: JSON.stringify(skuRecords)


                    };
                    console.log(data);
                    alert("a")
                    $.ajax({
                        url: "${path}/addCommodity",
                        type: "get",
                        data: datas,
                        success: function (res) {
                            alert(res)
                        }
                    });

                    return false;
                });


                form.on('select(zj)', function (data) {
                    var value = data.value;

                    $('.zjtest option').not(":first").remove();
                    // $(".secMenuValue").addClass(data.value).removeClass("secMenuValue");
                    //http://localhost:8888/ga/saveOrUpdateCommodity?commodityName=%E6%89%8B%E6%9C%BA&firstMenuId=%E6%95%B0%E7%A0%81%E4%BA%A7%E5%93%81&secMenuId=1&commodityImg=spt&commodityDetails=spxqt&spgg=A&spsx=1&spsx=2&spgg=B&spsx=3&sku-inventory0=1&sku-present-price0=1&sku-inventory1=2&sku-present-price1=2
                    <c:forEach items="${requestScope.nav}" var="nav" varStatus="i">
                    var fid = ${nav.firstMenuId};

                    if (fid == value) {

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
            <%--            <button class="as">a</button>--%>

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

                                <option value="${nav.firstMenuId}">${nav.firstMenuChineseName}</option>
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
                        <input type="text" name="spt" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品图</label>
                    <div class="layui-input-block">
                        <input type="text" name="spt" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品详情图</label>
                    <div class="layui-input-block">
                        <input type="text" name="spxqt" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品详情图</label>
                    <div class="layui-input-block">
                        <input type="text" name="spxqt" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item buttons">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn  layui-btn-warm guige">添加规格</button>
                        <button type="button" class="layui-btn  layui-btn-warm shuxing">添加属性</button>
                        <button type="button" class="layui-btn  layui-btn-normal bianli">规格确定</button>
                        <button class="layui-btn " lay-submit lay-filter="formDemo">立即提交</button>
                        <%--                        <button type="button " class="layui-btn  layui-btn-warm submits">提交</button>--%>

                        <button type="reset" class="layui-btn layui-btn-danger">重置</button>
                    </div>
                </div>
            </form>
        </div>

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
    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div>
</body>
</html>
