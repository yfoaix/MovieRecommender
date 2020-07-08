(function ($) {
    function crateCommentInfo(obj) {
        if (typeof (obj.time) == "undefined" || obj.time == "") {
            obj.time = getNowDateFormat();
        }

        var el = "<div class='comment-info'><header><a onclick=\"window.location.href='"+ obj.userUrl +"'\"><img src='" + obj.img + "'></a></header><div class='comment-right'><h3>" + obj.replyName + "</h3>" +
            "<div class='comment-content-header'><span><i class='glyphicon glyphicon-time'></i>" + obj.time + "</span>";

        if (typeof (obj.address) != "undefined" && obj.browse != "") {
            el = el + "<span><i class='glyphicon glyphicon-map-marker'></i>" + obj.address + "</span>";
        }
        el = el + "</div><p class='content'>" + obj.content + "</p><div class='comment-content-footer'><div class='row'><div class='col-md-12'>";

        if (typeof (obj.osname) != "undefined" && obj.osname != "") {
            el = el + "<span><i class='glyphicon glyphicon-pushpin'></i> 来自:" + obj.osname + "</span>";
        }

        if (typeof (obj.browse) != "undefined" && obj.browse != "") {
            el = el + "<span style='display: inline;'><i class='glyphicon glyphicon-globe'></i> " + obj.browse + "</span>";
        }

        if (typeof (obj.score) != "undefined" && obj.score != "") {
            el = el + "<span style='display: inline; float:right;'><i class='glyphicon glyphicon-globe'></i>评分： " + obj.score + "</span>";
        }
        el = el + "</div></div></div></div></div>"; //</div>
        return el;
    }

    function getNowDateFormat() {
        var nowDate = new Date();
        var year = nowDate.getFullYear();
        var month = filterNum(nowDate.getMonth() + 1);
        var day = filterNum(nowDate.getDate());
        var hours = filterNum(nowDate.getHours());
        var min = filterNum(nowDate.getMinutes());
        var seconds = filterNum(nowDate.getSeconds());
        return year + "-" + month + "-" + day + " " + hours + ":" + min + ":" + seconds;
    }

    function filterNum(num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return num;
        }
    }
    $.fn.addCommentList = function (options) {
        var defaults = {
            data: [],
            add: ""
        }
        var option = $.extend(defaults, options);
        //加载数据
        if (option.data.length > 0) {
            var dataList = option.data;
            var totalString = "";
            for (var i = 0; i < dataList.length; i++) {
                var obj = dataList[i];
                var objString = crateCommentInfo(obj);
                totalString = totalString + objString;
            }
            $(this).append(totalString).find(".reply-btn").click(function () {
                if ($(this).parent().parent().find(".replybox").length > 0) {
                    $(".replybox").remove();
                } else {
                    $(".replybox").remove();
                    replyClick($(this));
                }
            });
            $(".reply-list-btn").click(function () {
                if ($(this).parent().parent().find(".replybox").length > 0) {
                    $(".replybox").remove();
                } else {
                    $(".replybox").remove();
                    replyClick($(this));
                }
            })
        }

        //添加新数据
        if (option.add != "") {
            obj = option.add;
            var str = crateCommentInfo(obj);
            $(this).prepend(str).find(".reply-btn").click(function () {
                replyClick($(this));
            });
        }
    }
})(jQuery);