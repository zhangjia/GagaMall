//	导航固定顶部
$(function () {
    $(window).scroll(function () {
        var ws = $(window).scrollTop();
        var lo = location.toString().indexOf("list");
        if (lo != -1) {
            if (ws > 600) {
                // $(".head").addClass("ding").css({"background":"rgba(255,255,255,"+ws/300+")"});
                $(".head").addClass("ding");
            } else {
                $(".head").removeClass("ding").css({"background": "#fff"});
            }
        } else {
            if (ws > 60) {
                // $(".head").addClass("ding").css({"background":"rgba(255,255,255,"+ws/300+")"});
                $(".head").addClass("ding");
            } else {
                $(".head").removeClass("ding").css({"background": "#fff"});
            }
        }

    });
})
