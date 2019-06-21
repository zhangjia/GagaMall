<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<head>
<link href="${path}/static/address/css/main.css" rel="stylesheet" type="text/css" />

<!--必要样式-->
<link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${path}/static/address/css/city-picker.css" rel="stylesheet" type="text/css" />


</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/element-ui@2.6.3/lib/theme-chalk/index.css">
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.8/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/element-ui@2.6.3/lib/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/element-china-area-data@4.1.2/dist/app.min.js"></script>

<div id="app">
	<el-cascader
			size="large"
			:options="options"
			v-model="selectedOptions"
			@change="handleChange">
	</el-cascader>
</div>

<script>
	new Vue({
		el: "#app",

		data: {
			options: regionData,
			selectedOptions: []
		},

		mounted: function() {
		},

		methods: {
			handleChange (value) {
				console.log(value);
			}
		}
	});
</script>
</body>
