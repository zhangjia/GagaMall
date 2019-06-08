<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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

<div class="container mt-3">
    <h3>设置下拉菜单</h3>
    <p>输入框中添加下拉菜单不需要使用 .dropdown 类。</p>
    <form>
        <div class="input-group mt-3 mb-3">
            <div class="input-group-prepend">
                <button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown">
                    选择网站
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="https://www.google.com">GOOGLE</a>
                    <a class="dropdown-item" href="https://www.runoob.com">RUNOOB</a>
                    <a class="dropdown-item" href="https://www.taobao.com">TAOBAO</a>
                </div>
            </div>
            <input type="text" class="form-control" placeholder="网站地址">
        </div>
    </form>
</div>

</body>
</html>