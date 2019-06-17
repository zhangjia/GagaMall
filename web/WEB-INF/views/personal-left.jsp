<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<h3>
    <a href="#"><img src="${path}/static/img/tx.png"/></a>
    <p class="clearfix"><span class="fl">[${sessionScope.user.userName}]</span><span class="fr"><a
            href="${path}/logout">[退出登录]</a></span></p>
</h3>
<div>
    <h4>我的交易</h4>
    <ul>
        <li><a href="cart.jsp">我的购物车</a></li>
        <li><a href="myorderq.jsp">我的订单</a></li>
        <li><a href="myprod.jsp">评价晒单</a></li>
    </ul>
    <h4>个人中心</h4>
    <ul>
        <li class="on"><a href="mygxin.jsp">我的中心</a></li>
        <li><a href="${path}/address">地址管理</a></li>
    </ul>
    <h4>账户管理</h4>
    <ul>
        <li><a href="mygrxx.jsp">个人信息</a></li>
        <li><a href="remima.jsp">修改密码</a></li>
    </ul>
    <h4>商品管理</h4>
    <ul>
        <li><a href="${path}/saveOrUpdateCommodity">添加商品</a></li>
        <li><a href="remima.jsp">修改商品</a></li>
        <li><a href="remima.jsp">删除商品</a></li>
    </ul>
</div>
