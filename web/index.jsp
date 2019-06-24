<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>layui</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/layui/css/layui.css"/>
	<script src="${path}/static/layui/layui.js " type="text/javascript" charset="utf-8"></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="//res.layui.com/layui/dist/css/layui.css"  media="all">
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<blockquote class="layui-elem-quote">为节省服务器开销，以下示例均未配置真实上传接口，所以每次上传都会报提示：请求上传接口出现异常，这属于正常现象。</blockquote>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
	<legend>常规使用：普通图片上传</legend>
</fieldset>

<div class="layui-upload">
	<button type="button" class="layui-btn" id="test1">上传图片</button>
	<div class="layui-upload-list">
		<img class="layui-upload-img" id="demo1">
		<p id="demoText"></p>
	</div>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
	<legend>上传多张图片</legend>
</fieldset>

<div class="layui-upload">
	<button type="button" class="layui-btn" id="test2">多图片上传</button>
	<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
		预览图：
		<div class="layui-upload-list" id="demo2"></div>
	</blockquote>
</div>

<script>
	layui.use('upload', function(){
		var $ = layui.jquery
				,upload = layui.upload;
		console.log(upload)

		//普通图片上传
		var uploadInst = upload.render({
			elem: '#test1'
			,url: '${path}/imgoss'

			,before: function(obj){
				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result){
					$('#demo1').attr('src', result); //图片链接（base64）
				});
			}
			,done: function(res){
				console.log(res)
				//如果上传失败
				if(res.code > 0){
					return layer.msg('上传失败');
				}
				//上传成功
			}
			,error: function(){
				//演示失败状态，并实现重传
				var demoText = $('#demoText');
				demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
				demoText.find('.demo-reload').on('click', function(){
					uploadInst.upload();
				});
			},choose: function(obj){
			//将每次选择的文件追加到文件队列
			var files = obj.pushFile();
			console.log(files)

			//预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
			obj.preview(function(index, file, result){
				console.log(index); //得到文件索引
				console.log(file); //得到文件对象
				console.log(result); //得到文件base64编码，比如图片

				//obj.resetFile(index, file, '123.jpg'); //重命名文件名，layui 2.3.0 开始新增

				//这里还可以做一些 append 文件列表 DOM 的操作

				//obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
				//delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
			});
		}
		});





	});
</script>

</body>
</html>