<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container mt-3">
    <%
        String imgs = "1.jpg,2.jpg,3.jpg";
        pageContext.setAttribute("imgs",imgs);
    %>
    <ul>
        <c:forTokens items="${pageScope.imgs}" delims="," var="img" varStatus="status">
            <li>${status.count}</li>
        </c:forTokens>
    </ul>
</div>


<div class="container">
    <ul class="pager">
        <li><a href="#">«</a></li>
        <li><a href="#">上一页</a></li>
        <li><a href="#">下一页</a></li>
        <li><a href="#">»</a></li>
    </ul>
</div>

</body>
</html>