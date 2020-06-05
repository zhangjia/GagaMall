<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    .bj-order-pay {
        width: 410px;
        position: fixed;
        top: 50%;
        left: 50%;
        margin-top: -180px;
        margin-left: -220px;
        z-index: 30;
        background: #fff;
        border-radius: 10px;
        padding: 15px;
        display: none;
    }

    .mask-order-pay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        z-index: 20;
        display: none;
    }
</style>

<div class="mask-order-pay"></div>
<!--编辑弹框-->
<div class="bj-order-pay">
    <%--			<div class="clearfix"><a href="#" class="fr gb"><img src="${path}/static/img/icon4.png"/></a></div>--%>
    <h3 style="margin: 10px">订单支付</h3>
    <hr>
    <form class="layui-form" action="">


        <div class="layui-form-item">
            <label class="layui-form-label">订单ID：</label>
            <div class="layui-input-inline">
                <input style="margin-left: 10px;    width: 200px;" type="text" name="orderId" required
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">物流编号：</label>
            <div class="layui-input-inline">
                <input style="margin-left: 10px;    width: 200px;" type="text" name="logistics" required
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn jia-user-order-pay" lay-submit lay-filter="jia-filter-user-order-pay"
                        lay-filter="jia-edit-user-information">发货
                </button>
                <button type="button" class="layui-btn layui-btn-primary jia-btn-cancle">取消</button>
            </div>
        </div>
    </form>

    <script>

        $(function () {
            $(".jia-id-myorder-pay").click(function () {
                var orderId = $(this).parent().parent().siblings(".word.clearfix").children("ul").children(".order-num").children("span").text();
                $("input[name='orderId']").val(orderId);
                $(".bj-order-pay").show();
                $(".mask-order-pay").show();

                var money = $(this).parent().parent().siblings(".word.clearfix").children("p.fr").children("span").text();
                $(".jia-order-pay-view-price").val(money)
            });

            $(".jia-btn-cancle").click(function () {
                $(".bj-order-pay").hide();
                $(".mask-order-pay").hide();

                $(".remima").hide();
                $(".avatar").hide();
                $(".chak").hide();

            });

        });
        //Demo
        layui.use('form', function () {
            var form = layui.form;

            //监听提交
            form.on('submit(jia-filter-user-order-pay)', function (data) {
                console.log(data.field)


                console.log(${requestScope.orderId})


                $.ajax({
                    url: "${path}/deliverGoods",
                    type: "get",
                    data: {

                        orderId: data.field.orderId,

                        logistics: data.field.logistics,

                    },
                    success: function (res) {

                        if (res.success === true) {
                            layer.msg("发货成功");
                            location.reload();
                        }

                    }


                })
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    </script>
</div>