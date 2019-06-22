<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        function setCookie(name, value) {
            var Days = 30;
            var exp = new Date();
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
            document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
        }

        function getCookie(name) {
            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg))
                return unescape(arr[2]);
            else
                return null;
        }

        function delCookie(name) {
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cval = getCookie(name);
            if (cval != null)
                document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
        }

        $(".add-address").click(function () {
            $(".jia-address-form")[0].reset();
            $(".jia-address-id").text('0');
            $(".o-p-hide").text('省份/自治区');
            $(".o-p-hide").val('省份/自治区');
            $(".o-c-hide").text('城市/地区');
            $(".o-c-hide").val('城市/地区');
            $(".o-q-hide").text('区');
            $(".o-q-hide").val('区');
            $(".o-x-hide").text('县');
            $(".o-x-hide").val('县');

        });
        $(".jia-edit").click(function () {
            var addressId = $(this).parent().parent().siblings("p:hidden").text();
            $(".jia-address-id").text(addressId);

            $.ajax({
                url: "${path}/getAddress",
                type: "get",
                data: {
                   addressId:addressId
                },
                success: function (res) {
                   console.log(res);
                    $("input[placeholder='姓名']").val(res.ADDRESS_NAME);
                    $("input[placeholder='手机号']").val(res.ADDRESS_TEL);
                    $(".address-details").val(res.ADDRESS_DETAIL);
                    $(".postcode").val(res.ADDRESS_POSTCODE);
                    $(".o-p-hide").text(res.PROVINCE);
                    $(".o-p-hide").val(res.PROVINCE);
                    $(".o-c-hide").text(res.CITY);
                    $(".o-c-hide").val(res.CITY);
                    $(".o-q-hide").text(res.DISTRICT);
                    $(".o-q-hide").val(res.DISTRICT);
                    $(".o-x-hide").text(res.COUNTY);
                    $(".o-x-hide").val(res.COUNTY);

                }
        });
        });
        $(".save-address").click(function () {

            delCookie("addressId");
            var h = getCookie("addressId");
            console.log(h)

            var name = $("input[placeholder='姓名']").val();

            var tel = $("input[placeholder='手机号']").val();

            var detail = $(".address-details").val();


            var postcode = $(".postcode").val();

            var province = $(".option-province:selected").val();
            console.log("s" + province)

            var city = $(".option-city:selected").val();

            var qu = $(".option-qu:selected").val();

            var xian = $(".option-xian:selected").val();
            /*使用text不能直接==''，使用val可以*/

            var addressId = $(this).parent().siblings("p:hidden").text();
            console.log("id" + addressId)

            console.log(xian.length > 1)
            var detailedAddress = province + " " + city + " " + qu + " " + xian + " " + detail;
            if (name == '' || tel == '' || detail.length == 0 ||
                postcode == '' || province == '' || city == '' || qu == '' || xian == ''
                || province == '省份/自治区' || city == '城市/地区' || qu == '区' || xian == '县') {
                layer.msg("地址不完整");

            } else {
                $.ajax({
                    url: "${path}/addAddress",
                    type: "get",
                    data: {
                        name: name,
                        tel: tel,
                        postcode: postcode,
                        detailedAddress: detailedAddress,
                        province: province,
                        city: city,
                        district: qu,
                        country: xian,
                        addressId:addressId,

                    },
                    success: function (res) {
                        if (res.success === true) {
                            console.log($(".jia-address-id").text() + "d ")

                            if($(".jia-address-id").text() != '0'){
                                layer.msg("修改成功")
                            } else{
                                layer.msg("添加成功")
                            }

                            location.reload();
                        }


                    }

                });
                $(".mask").hide();
                $(".adddz").hide();
                $(".bj").hide();
                $(".xg").hide();
                $(".remima").hide();
                $(".pj").hide();
                $(".chak").hide();
            }


            console.log(name + "--" + tel + "--" + province + "--" + city + "--" + qu + "--" + xian + "--" + detail + postcode)


        });

        $(".op-hide").click(function () {
            $(".o-p-hide").hide();

        });
        $(".oc-hide").click(function () {
            $(".o-c-hide").hide();

        });
        $(".oq-hide").click(function () {
            $(".o-q-hide").hide();

        });
        $(".ox-hide").click(function () {
            $(".o-x-hide").hide();

        });
        $(".jia-default").click(function () {
            var addressId = $(this).parent().parent().siblings("p:hidden").text();
            $.ajax({
                url: "${path}/setDefaultAddress",
                type: "get",
                data: {
                    addressId:addressId
                },
                success: function (res) {
                    console.log(res)
                    if (res.success === true) {
                        layer.msg("默认成功")
                    }


                } });
        });
        $(".jia-address-delete").click(function () {
            var addressId = $(this).parent().parent().siblings("p:hidden").text();
            var thiss = $(this);
            var div = $(this).parent().parent().parent();
            console.log(div[0]);
            $.ajax({
                url: "${path}/deleteAddress",
                type: "get",
                data: {
                    addressId:addressId
                },
                success: function (res) {
                    console.log(res)
                    if (res.success === true) {
                        layer.msg("删除成功")
                        div.remove();

                    }


                } });

        });
        

    });

</script>
<!--------h3---------------->
<h3>收件信息<a href="#" class="fr add-address" id="addxad">新增地址</a></h3>
<!--------addres---------------->

<div class="addres clearfix">
    <c:forEach var="address" items="${requestScope.userAddress}">

        <div class="addre fl">
            <p hidden>${address.ADDRESS_ID}</p>
            <div class="tit clearfix">
                <p class="fl">${address.ADDRESS_NAME}

                    <c:if test="${address.ADDRESS_STATUS == 2}">
                        <span class="default " style="color: red;">[默认地址]</span>
                    </c:if>

                </p>
                <p class="fr">
                    <c:if test="${address.ADDRESS_STATUS == 1}">
                        <a href="#" class="setDefault jia-default">设为默认</a>
                        <span>|</span>
                    </c:if>
                    <a href="#" class="jia-address-delete">删除</a>
                    <span>|</span>
                    <a href="#" class="edit jia-edit">编辑</a>
                </p>

            </div>
            <div class="addCon">
                <p>${address.ADDRESS_DETAIL}</p>
                <p>${address.ADDRESS_TEL}</p>
            </div>
        </div>

    </c:forEach>


</div>

<div class="mask"></div>
<div class="adddz editAddre">
    <form action="#" method="get" class="jia-address-form">
        <p hidden class="jia-address-id"></p>
        <input type="text" placeholder="姓名" class="on" value="${re}"/>
        <input type="text" placeholder="手机号"/>
        <div class="city">

            <div id="app">
                <select @change="provinceChange" class="op-hide">
                    <option class="option-province o-p-hide" v-for="provinceList" value="省份/自治区">省份/自治区</option>
                    <option class="option-province" v-for="province in provinceList">{{province}}</option>
                </select>
                <select @change="cityChange" class="oc-hide">
                    <option class="option-city o-c-hide" v-for="cityList" value="城市/地区">城市/地区</option>
                    <option class="option-city" v-for="city in cityList">{{city}}</option>
                </select>
                <select @change="districtChange" class="oq-hide">
                    <option class="option-qu o-q-hide" v-for="districtList" value="区">区</option>
                    <option class="option-qu" v-for="district in districtList">{{district}}</option>
                </select>
                <select class="ox-hide">
                    <option class="option-xian o-x-hide" v-for="streetList" value="县">县</option>
                    <option class="option-xian" v-for="street in streetList">{{street}}</option>
                </select>
            </div>
        </div>

        <textarea name="" rows="" cols="" placeholder="详细地址" class="address-details"></textarea>
        <input type="text" placeholder="邮政编码" class="postcode"/>
        <div class="bc">
            <input type="button" class="save-address" value="保存"/>
            <input type="button" value="取消"/>
        </div>
    </form>
</div>