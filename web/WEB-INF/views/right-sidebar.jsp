<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!------------------------------head.jsp------------------------------>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!--返回顶部-->
<div class="gotop">
    <a href="${path}/cart">
        <dl>
            <dt><img src="${path}/static/img/gt1.png"/></dt>
            <dd>去购<br />物车</dd>
        </dl>
    </a>
    <a href="${path}/cart" class="dh">
        <dl>
            <dt><img src="${path}/static/img/gt2.png"/></dt>
            <dd>联系<br />客服</dd>
        </dl>
    </a>
    <a href="${path}/personal">
        <dl>
            <dt><img src="${path}/static/img/gt3.png"/></dt>
            <dd>个人<br />中心</dd>
        </dl>
    </a>
    <a href="javascript:;" class="toptop" style="display: none">
        <dl>
            <dt><img src="${path}/static/img/gt4.png"/></dt>
            <dd>返回<br />顶部</dd>
        </dl>
    </a>
    <p>400-800-8200</p>
</div>