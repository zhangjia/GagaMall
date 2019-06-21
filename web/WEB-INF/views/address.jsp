<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!--------h3---------------->
<h3>收件信息<a href="#" class="fr">新增地址</a></h3>
<!--------addres---------------->
<div class="addres clearfix">
    <div class="addre fl on">
        <div class="tit clearfix">
            <p class="fl">张三1
                <span class="default">[默认地址]</span>
            </p>
            <p class="fr">
                <a href="#">删除</a>
                <span>|</span>
                <a href="#" class="edit">编辑</a>
            </p>
        </div>
        <div class="addCon">
            <p>河北省&nbsp;唐山市&nbsp;路北区&nbsp;大学生公寓村</p>
            <p>15732570937</p>
        </div>
    </div>
    <div class="addre fl">
        <div class="tit clearfix">
            <p class="fl">张三2
            </p>
            <p class="fr">
                <a href="#" class="setDefault">设为默认</a>
                <span>|</span>
                <a href="#">删除</a>
                <span>|</span>
                <a href="#" class="edit">编辑</a>
            </p>
        </div>
        <div class="addCon">
            <p>河北省&nbsp;唐山市&nbsp;路北区&nbsp;大学生公寓村</p>
            <p>15732570937</p>
        </div>
    </div>
    <div class="addre fl">
        <div class="tit clearfix">
            <p class="fl">张三3
            </p>
            <p class="fr">
                <a href="#" class="setDefault">设为默认</a>
                <span>|</span>
                <a href="#">删除</a>
                <span>|</span>
                <a href="#" class="edit">编辑</a>
            </p>
        </div>
        <div class="addCon">
            <p>河北省&nbsp;唐山市&nbsp;路北区&nbsp;大学生公寓村</p>
            <p>15732570937</p>
        </div>
    </div>
</div>