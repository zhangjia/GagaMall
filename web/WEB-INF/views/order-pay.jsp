<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    /*遮罩*/
    .mask-order-pay{
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,0.5);
        z-index: 20;
        display: none;
    }
    /*!*编辑个人信息*!*/
    /*.bj-order-pay{*/
    /*    width: 410px;*/
    /*    position: absolute;*/
    /*    top: 30%;*/
    /*    left: 50%;*/
    /*    margin-top: -180px;*/
    /*    margin-left: -220px;*/
    /*    z-index: 30;*/
    /*    background: #fff;*/
    /*    border-radius: 10px;*/
    /*    padding: 15px;*/
    /*    display: none;*/
    /*}*/


</style>

<script>

    $(function () {
        $(".jia-btn-cancle").click(function () {
            $(".bj-order-pay").hide();
            $(".mask-order-pay").hide();

            $(".remima").hide();
            $(".avatar").hide();
            $(".chak").hide();

        });
    })
</script>
<div class="mask-order-pay"></div>
<!--编辑弹框-->
<div class="bj-order-pay">
    <%--			<div class="clearfix"><a href="#" class="fr gb"><img src="${path}/static/img/icon4.png"/></a></div>--%>
    <h3 style="margin: 10px">订单支付</h3>
    <hr>
    <form class="layui-form" action="">
        <div class="layui-tab-item layui-show" >
            <span style="margin-left: 50px;">订单金额</span>
            <c:if test="${requestScope.total.SUM_COMMODITY_PAY_PRICE == null}">
                <input value="" readonly="readonly" style="width:200px; display: inline-block;margin-top: 20px;margin-left:20px" type="number"  name="payMoney" lay-verify="title" autocomplete="off" placeholder="" class="layui-input jia-order-pay-view-price ">
            </c:if>
            <c:if test="${requestScope.total.SUM_COMMODITY_PAY_PRICE != null}">
                <input value="${requestScope.total.SUM_COMMODITY_PAY_PRICE}" readonly="readonly" style="width:200px; display: inline-block;margin-top: 20px;margin-left:20px" type="number"  name="payMoney" lay-verify="title" autocomplete="off" placeholder="" class="layui-input ">
            </c:if>
            <span style="font-size: 20px;margin-left: 10px;">元</span>
            <br/>
        </div>

        <div style="margin: 10px 20px;" class="layui-form-item">
            <label class="layui-form-label">支付方式</label>
            <div class="layui-input-block">
                <input type="hidden" name="orderId" value="">
                <input  type="radio" name="payStyle" value="余额" title="余额">
                <input type="radio" name="payStyle" value="白条" title="白条" checked>
            </div>
        </div>
<%--        <div class="layui-form-item layui-form-text">--%>
<%--            <label class="layui-form-label jia-order-note">备注</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <textarea name="note" placeholder="请输入备注" class="layui-textarea"></textarea>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label">支付密码</label>
            <div class="layui-input-inline">
                <input style="margin-left: 10px;    width: 200px;" type="password" name="paypassword" required lay-verify="required" placeholder="请输入支付密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn jia-user-order-pay" lay-submit lay-filter="jia-filter-user-order-pay"
                        lay-filter="jia-edit-user-information">支付
                </button>
                <button type="button" class="layui-btn layui-btn-primary jia-btn-cancle">取消</button>
            </div>
        </div>
    </form>

    <script>

        $(function () {
            $(".jia-id-myorder-pay").click(function () {
                var orderId =  $(this).parent().parent().siblings(".word.clearfix").children("ul").children(".order-num").children("span").text();
                $("input[name='orderId']").val(orderId);
                $(".bj-order-pay").show();
                $(".mask-order-pay").show();

                var money = $(this).parent().parent().siblings(".word.clearfix").children("p.fr").children("span").text();
                $(".jia-order-pay-view-price").val(money)
            });

        });
        //Demo
        layui.use('form', function () {
            var form = layui.form;

            //监听提交
            form.on('submit(jia-filter-user-order-pay)', function (data) {
                console.log(data.field)


                console.log(${requestScope.orderId})
                var locations;
                var orderId;
                var payMoney;
                if(${requestScope.total.SUM_COMMODITY_PAY_PRICE == null}) {
                    locations ="订单页";
                    orderId = data.field.orderId;

                } else {
                    orderId = "${sessionScope.orderId}";
                    payMoney:"${requestScope.total.SUM_COMMODITY_PAY_PRICE}";
                }

                console.log("aa")
                console.log(locations)
                console.log(orderId)
                console.log(payMoney)
                $.ajax({
                    url: "${path}/pay",
                    type: "get",
                    data: {
                        payStyle:data.field.payStyle,
                        payMoney:payMoney,
                        // note:data.field.note,
                        orderId:orderId,
                        location:locations,
                        paypassword:data.field.paypassword,


                    },
                    success: function (res) {
                        console.log(res)
                        if (res.error ==='未设置密码') {

                            layer.msg('未设置支付密码', {

                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function(){
                               location = "${path}/personalInformation";
                            });
                            return;
                        }

                        if (res.error ==='支付密码不正确') {
                            layer.msg("支付密码不正确",{

                                time: 1000 //2秒关闭（如果不配置，默认是3秒）
                            });
                            return;

                        }

                        console.log(res.success ==="支付成功")
                        console.log(res.haha)
                        if (res.success ==='支付成功') {
                            layer.msg("支付成功",{
                                time:1000
                            },function () {
                                location = "${path}/myorder";
                            });


                        } else if(res.error ==='余额不足') {
                            layer.msg("余额不足",{
                                time:1000
                            },function () {
                                location = "${path}/wallet";
                            });

                        } else if(res.error === '额度不足'){
                            layer.msg("额度不足");

                        } else {
                            layer.msg("支付失败");
                        }

                    }


                })
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    </script>
</div>