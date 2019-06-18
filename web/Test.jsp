<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<script>
    $(function () {
        alert($(".test").parent().children(":eq(2)").text())
    });
</script>
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
        <li>1</li>
        <li>2</li>
        <li  class="test">3</li>
        <li>4</li>
    </ul>
</div>
INSERT INTO sku VALUES(seq_sku.nextval,1,'{"颜色":"白色","容量":"256G"}',8000,8000,7000,100,100,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,1,'{"颜色":"白色","容量":"128G"}',7000,7000,6000,100,100,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,1,'{"颜色":"黑色","容量":"128G"}',7000,7000,6000,100,100,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,1,'{"颜色":"黑色","容量":"256G"}',8000,8000,7000,100,100,sysdate,sysdate,1);

------------------------------------
INSERT INTO sku VALUES(seq_sku.nextval,2,'{"颜色":"黑色","版本":"6GB + 64GB"}',3000,2000,2000,200,200,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,2,'{"颜色":"黑色","版本":"8GB + 128GB"}',4000,3000,3000,300,300,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,2,'{"颜色":"白色","版本":"6GB + 64GB"}',3000,2000,2000,400,400,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,2,'{"颜色":"白色","版本":"6GB + 128GB"}',4000,3000,3000,200,100,sysdate,sysdate,1);

-----------------------------
INSERT INTO sku VALUES(seq_sku.nextval,3,'{"版本":"i5 8G 256G"}',6000,5000,5000,200,100,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,3,'{"版本":"i5 16G 256G"}',8000,7000,6000,200,100,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,4,'{"版本":"米家互联网-1.5匹一级"}',2000,1999,1999,200,100,sysdate,sysdate,1);

INSERT INTO sku VALUES(seq_sku.nextval,4,'{"版本":"米家互联网-1.5匹三级"}',3000,2999,2999,200,100,sysdate,sysdate,1);

</body>
</html>