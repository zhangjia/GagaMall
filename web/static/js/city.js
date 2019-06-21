$(function () {

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
            districtChange: function (e) {
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


})