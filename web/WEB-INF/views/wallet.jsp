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
        function walletBalance(){

            $.ajax({
                url:"${path}/wallet?type=balance",
                type:'post',
                success:function (res) {
                    $(".jia-view-balance").val(res.WALLET_BALANCE);
                }
            })
        }
        function walletIOU(){

            $.ajax({
                url:"${path}/iou",
                type:'get',
                success:function (res) {
                    //{"IOU_LIMIT":5000,"IOU_USABLE_LIMIT":0,"IOU_ID":1,"USER_ID":1}
                   console.log(res)
                    if(res.success === true) {
                        $(".jia-view-iou").val(res.IOU_LIMIT);
                        $(".jia-view-iou-usable").val(res.IOU_USABLE_LIMIT);
                    } else {

                    }

                }
            })
        }

        $(function () {
            walletBalance();

            $(".jia-recharge").click(function () {
                var rechargeMoney = $(this).siblings("input").val()
                if( rechargeMoney== null || rechargeMoney ==''){
                    layer.msg("请先输入金额")
                } else {
                    console.log(rechargeMoney)
                    $.ajax({
                        url:"${path}/recharge",
                        type:'get',
                        data:{
                            money:rechargeMoney
                        },
                        success:function (res) {
                            console.log(res)
                            if(res.success === true) {
                                layer.msg("充值成功！")
                            } else {
                                layer.msg("充值失败！")
                            }
                        }
                    })
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
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>嘎嘎钱包</legend>
            </fieldset>

            <div class="layui-tab" lay-filter="jia-wallet-filter-balance">
                <ul class="layui-tab-title">
                    <li class="layui-this" lay-filter="jia-wallet-filter-balance">钱包管理</li>
                    <li>充值金额</li>
                    <li>白条管理</li>
<%--                    <li>会员管理</li>--%>

                </ul>
                <div class="layui-tab-content" >
                    <div class="layui-tab-item layui-show " >
                        <button style="" type="button" class="layui-btn layui-btn-warm">余额</button>
                        <input readonly="readonly" style="width:200px; display: inline-block;margin-top: 20px;margin-left:20px" type="number"  name="title" lay-verify="title" autocomplete="off" placeholder="" class="layui-input jia-view-balance">
                        <span style="font-size: 20px;margin-left: 10px;">元</span>
                        <br/>
                    </div>
                    <div class="layui-tab-item">

                        <button style="" type="button" class="layui-btn layui-btn-normal jia-recharge">充值</button>
                        <input  style="width:200px; display: inline-block;margin-top: 20px;margin-left:20px" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="" class="layui-input" oninput="this.value = this.value.replace(/[^0-9]/g, '')">
                        <span style="font-size: 20px;margin-left: 10px;">元</span>
                    </div>
                    <div class="layui-tab-item">
                        <button style="" type="button" class="layui-btn layui-btn-warm">白条额度</button>
                        <input readonly="readonly" style="width:200px; display: inline-block;margin-top: 20px;margin-left:20px" type="number"  name="title" lay-verify="title" autocomplete="off" placeholder="" class="layui-input jia-view-iou">
                        <span style="font-size: 20px;margin-left: 10px;">元</span>
                        <br />
                        <button style="" type="button" class="layui-btn layui-btn-warm">可用额度</button>
                        <input readonly="readonly" style="width:200px; display: inline-block;margin-top: 20px;margin-left:20px" type="number"  name="title" lay-verify="title" autocomplete="off" placeholder="" class="layui-input jia-view-iou-usable">
                        <span style="font-size: 20px;margin-left: 10px;">元</span>
                        <br/>

                    </div>


                </div>
            </div>
            <script>
                layui.use('element', function(){
                    var $ = layui.jquery
                        ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块


                    element.on('tab(jia-wallet-filter-balance)', function(data){
                        if(data.index == 0){
                            walletBalance();
                        }
                        if(data.index == 2){
                            walletIOU();
                        }
                        console.log(this); //当前Tab标题所在的原始DOM元素
                        console.log(data.index); //得到当前Tab的所在下标
                        console.log(data.elem); //得到当前的Tab大容器

                    });
                    //触发事件
                    var active = {
                        tabAdd: function(){
                            //新增一个Tab项
                            element.tabAdd('demo', {
                                                             title: '新选项'+ (Math.random()*1000|0) //用于演示
                                ,content: '内容'+ (Math.random()*1000|0)
                                ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
                            })
                        }
                        ,tabDelete: function(othis){
                                                      //删除指定Tab项
                            element.tabDelete('demo', '44'); //删除：“商品管理”


                            othis.addClass('layui-btn-disabled');
                        }
                        ,tabChange: function(){
                            //切换到指定Tab项
                            element.tabChange('demo', '22'); //切换到：用户管理
                        }
                    };

                    $('.site-demo-active').on('click', function(){
                        var othis = $(this), type = othis.data('type');
                        active[type] ? active[type].call(this, othis) : '';
                    });

                    //Hash地址的定位
                    var layid = location.hash.replace(/^#test=/, '');
                    element.tabChange('test', layid);

                    element.on('tab(test)', function(elem){
                        location.hash = 'test='+ $(this).attr('lay-id');
                    });

                });
            </script>

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
