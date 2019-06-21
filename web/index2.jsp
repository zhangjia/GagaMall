<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=b03bbaf1ff4c80e018561e5eb1210906"></script>
</head>
<body>
<div>
	<div>
		<div id="app">
			<select @change="provinceChange">
				<option v-for="province in provinceList">{{province}}</option>
			</select>
			<select @change="cityChange">
				<option v-for="city in cityList">{{city}}</option>
			</select>
			<select @change="districtChange">
				<option v-for="district in districtList">{{district}}</option>
			</select>
			<select>
				<option v-for="street in streetList">{{street}}</option>
			</select>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	new Vue({
		el: "#app",
		data: {
			districtSearch: '',
			provinceList: [],
			cityList: [],
			districtList: [],
			streetList: []
		},
		mounted: function () {
			this.init();
		},
		methods: {
			init: function () {
				var _this = this;
				AMap.plugin('AMap.DistrictSearch', function () {
					_this.districtSearch = new AMap.DistrictSearch({
						level: 'country',
						subdistrict: 1
					})
				});
				this.districtSearch.search('中国', function (status, result) {
					var list = result.districtList[0]['districtList'];
					for (var i = 0; i < list.length; i++) {
						_this.provinceList.push(list[i].name);
					}
				})
			},
			provinceChange: function (e) {
				var _this = this;
				var provinceName = e.target.value;
				this.cityList = [];
				this.districtSearch.search(provinceName, function (status, result) {
					var list = result.districtList[0]['districtList'];
					for (var i = 0; i < list.length; i++) {
						_this.cityList.push(list[i].name);
					}
				})
			},
			cityChange: function (e) {
				var _this = this;
				var cityName = e.target.value;
				this.districtList = [];
				this.districtSearch.search(cityName, function (status, result) {
					var list = result.districtList[0]['districtList'];
					for (var i = 0; i < list.length; i++) {
						_this.districtList.push(list[i].name);
					}
				})
			},
			districtChange: function(e) {
				var _this = this;
				var districtName = e.target.value;
				this.streetList = [];
				this.districtSearch.search(districtName, function (status, result) {
					var list = result.districtList[0]['districtList'];
					for (var i = 0; i < list.length; i++) {
						_this.streetList.push(list[i].name);
						console.log(list[i]);
					}
				})
			}
		}
	})
</script>

</html>
