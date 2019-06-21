<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!--------h3---------------->
<h3>收件信息<a href="#" class="fr" id="addxad">新增地址</a></h3>
<!--------addres---------------->
<div class="addres clearfix">
    <c:forEach var="address" items="${requestScope.userAddress}">

        <div class="addre fl">
            <p hidden>${address.ADDRESS_ID}</p>
            <div class="tit clearfix">
                <p class="fl">${address.ADDRESS_NAME}
                        <%--1是正常，-1是删除，2是默认--%>

                    <c:if test="${address.ADDRESS_STATUS == 2}">
                        <span class="default">[默认地址]</span>
                    </c:if>

                </p>
                <p class="fr">
                    <c:if test="${address.ADDRESS_STATUS == 1}">
                        <a href="#" class="setDefault">设为默认</a>
                        <span>|</span>
                    </c:if>
                    <a href="#">删除</a>
                    <span>|</span>
                    <a href="#" class="edit">编辑</a>
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
    <form action="#" method="get">
        <input type="text" placeholder="姓名" class="on"/>
        <input type="text" placeholder="手机号"/>
        <div class="city">

            <div id="app">
                <select @change="provinceChange">
                    <option value="省份/自治区">省份/自治区</option>
                    <option v-for="province in provinceList">{{province}}</option>
                </select>
                <select @change="cityChange">
                    <option value="城市/地区">城市/地区</option>
                    <option v-for="city in cityList">{{city}}</option>
                </select>
                <select @change="districtChange">
                    <option value="区">区</option>
                    <option v-for="district in districtList">{{district}}</option>
                </select>
                <select>
                    <option value="县">县</option>
                    <option v-for="street in streetList">{{street}}</option>
                </select>
            </div>
        </div>

        <textarea name="" rows="" cols="" placeholder="详细地址"></textarea>
        <input type="text" placeholder="邮政编码"/>
        <div class="bc">
            <input type="button" value="保存"/>
            <input type="button" value="取消"/>
        </div>
    </form>
</div>