<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 实例</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>商品图片</h2>
    <form action="${pageContext.request.contextPath}/add">
        <div class="form-group">
            <label for="url">图片地址:</label>
            <input type="text" class="form-control" id="url" name="url" placeholder="url">
        </div>
        <div class="form-group">
            <label for="cid">商品ID:</label>
            <input type="text" class="form-control" id="cid" name = "cid" placeholder="cid">
        </div>
        <div class="form-group">
            <label for="order">图片顺序:</label>
            <input type="text" class="form-control" id="order" name = "order" placeholder="order">
        </div>

        <input type="radio" name="img"  value="spt"/> 商品图
        <input type="radio" name="img"  value="spxqt"/> 商品详情图

        <br />
        <button type="submit" class="btn btn-primary">添加</button>
    </form>
</div>


</body>
</html>