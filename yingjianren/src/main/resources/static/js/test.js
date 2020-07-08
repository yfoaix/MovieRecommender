var data = {
    "title": "影荐人认证",
    "exam": [{
        "title": "单项选择题",
        "infos": null,
        "values": [{
            "type": 1,
            "questionStem": "《泰坦尼克号》的导演是谁？",
            "options": [
                "詹姆斯·卡梅隆",
                "史蒂文·斯皮尔伯格",
                "大卫芬奇"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "以下哪个是史蒂文斯皮尔伯格的代表作",
            "options": [
                "侏罗纪公园",
                "星球大战",
                "变形金刚"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "哪位华人演员参加了好莱坞影片《我是谁》，并在其中担任主角",
            "options": [
                "李连杰",
                "成龙",
                "洪金宝"
            ],
            "answer": [
                "B"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "钢铁侠的扮演者是",
            "options": [
                "汤姆克鲁斯",
                "小罗伯特唐尼",
                "布拉德皮特"
            ],
            "answer": [
                "B"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "美国的好莱坞电影公司坐落在哪个城市",
            "options": [
                "华盛顿",
                "利福尼亚州洛杉矶市郊区",
                "纽约"
            ],
            "answer": [
                "B"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "08年奥斯卡获奖最多的一部电影，堪称奇迹",
            "options": [
                "贫民窟的百万富翁",
                "本杰明巴顿",
                "蝙蝠侠前传"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "“人生就像巧克力，没人知道下一块是什么味道”，这句台词出于哪一部美国电影，并且获得了多项奥斯卡奖，至今仍然是人们谈论的话题",
            "options": [
                "阿甘正传",
                "肖申克的救赎",
                "楚门的世界"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "港澳台地区的影片是不是进口影片",
            "options": [
                "是",
                "不是",
                "台湾是"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "中国电影金鸡奖是于哪年创办的",
            "options": [
                "1979",
                "1980",
                "1981"
            ],
            "answer": [
                "C"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "《教父》中，教父的三儿子是谁",
            "options": [
                "桑尼",
                "弗雷德",
                "麦克"
            ],
            "answer": [
                "C"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "《金蝉脱壳》中，男主越狱过程中需要的一块金属的要求是",
            "options": [
                "一寸宽",
                "两寸宽",
                "三寸宽"
            ],
            "answer": [
                "C"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "2014年，中国影片《白日焰火》获得什么电影节最佳影片金熊奖",
            "options": [
                "威尼斯",
                "柏林",
                "东京"
            ],
            "answer": [
                "B"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "电影《霸王别姬》（1993年）由陈凯歌导演，张国荣、张丰毅、巩俐主演，曾于1993年荣获法国戛纳国际电影节最高奖项“金棕榈大奖”，这也是我国唯一部获此殊荣的影片。在剧中张国荣所扮演的是那个角色？",
            "options": [
                "段小楼",
                "小豆子",
                "程蝶衣"
            ],
            "answer": [
                "C"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "2004年，电影频道节目中心与上海国际电影节共同打造了（），旨在扶持中国电影产业、推动国产影片发展",
            "options": [
                "华语电影传媒大奖",
                "百合奖",
                "中国电影华表奖"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "功夫皇帝李连杰的成名作是下面哪一部",
            "options": [
                "少林寺",
                "方世玉",
                "骇客帝国"
            ],
            "answer": [
                "A"
            ],
            "analysis": null
        },
        {
            "type": 1,
            "questionStem": "系列电影《加勒比海盗》是哪间电影公司的作品",
            "options": [
                "华纳",
                "好莱坞",
                "迪士尼"
            ],
            "answer": [
                "C"
            ],
            "analysis": null
        }
        ]
    }]
};

//document.write("<script type='text/javascript' src='b.js'></script>");

function test (test_id) {
    if (test_id != "") {
        //$.ajax({
        //type : 'POST',
        //url : 'data/exam.json',
        //data : {'testId':test_id},
        //dataType : "json",
        //success : function(data){
        console.log(data);
        var titleB = data.title;
        var exam = data.exam;
        var test_box = '';

        $.each(exam, function (h, exam) {
            var title = exam.title;
            var info = exam.infos != null ? '<h4 class="jxz-title">' + exam.infos + '</h4>' : '';
            var test = exam.values;
            var topic_box = '';

            $.each(test, function (i, topic) {//1单选2多选3判断4填空5问答8论述题6完型填空7阅读理解
                var type = topic.type;
                var options = topic.options;
                var answer = topic.answer;
                var analysis = topic.analysis == null || topic.analysis == "" ? "无" : topic.analysis;
                var option_box = '';//题目

                if (type == 1) {
                    //answer_op = '';
                    $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box += `
                                    <div class="jxz-option radio">
                                        <label>
                                            <input name="test`+ h + '' + i + `" type="radio" value="` + op + `"> ` + op + `：` + option + `
                                        </label>
                                    </div>
                                    `;
                    });

                } else if (type == 2) {
                    //answer_op = '';
                    $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box += `
                                    <div class="jxz-option checkbox">
                                        <label>
                                            <input name="test`+ h + '' + i + `" type="checkbox" value="` + op + `"> ` + op + `：` + option + `
                                        </label>
                                    </div>
                                    `;
                    });


                } else if (type == 3) {

                    option_box = `
                                <div class="jxz-option radio">
                                    <label>
                                        <input name="test`+ h + '' + i + `" type="radio" value="1"> 正确
                                    </label>
                                </div>
                                <div class="jxz-option radio">
                                    <label>
                                        <input name="test`+ h + '' + i + `" type="radio" value="0"> 错误
                                    </label>
                                </div>
                                `;
                } else if (type == 4) {

                    option_box += `
                                <div class="jxz-option">
                                    <textarea name="test`+ h + '' + i + `" class="form-control" style="width: 50%" rows="5" placeholder="答案以空格隔开"></textarea>
                                </div>
                                `;
                } else if (type == 5 || type == 8) {

                    option_box += `
                                <div class="jxz-option">
                                    <textarea name="test5" class="form-control" style="width: 50%" rows="5" placeholder=""></textarea>
                                </div>
                                `;
                } else if (type == 6) {

                    $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box += `
                                    <div class="jxz-option radio-inline">
                                        <label>
                                            `+ op + `：` + option + `
                                        </label>
                                    </div>
                                    `;
                    });
                    option_box += `
                                <div class="jxz-option">
                                    <textarea name="test`+ h + '' + i + `" class="form-control" style="width: 50%" rows="5" placeholder="答案以空格隔开"></textarea>
                                </div>`;
                } else if (type == 7) {

                    $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box += `
                                    <div class="jxz-option radio">
                                        <label>
                                            <input name="test`+ h + '' + i + `" type="radio" value="` + op + `"> ` + op + `：` + option + `
                                        </label>
                                    </div>
                                    `;
                    });
                }

                var answer_op = '';//答案
                if (type == 3) {
                    $.each(answer, function (i, aw) {
                        answer_op += aw == 1 ? "正确" : "错误";
                    });
                } else {
                    $.each(answer, function (i, aw) {
                        answer_op += answer.length == (i + 1) ? aw : aw + " ";
                    });
                }
                topic_box += `
                            <div class="testCon" data-type="`+ type + `" data-answer="` + answer_op + `">
                                <h4 class="jxz-title">`+ topic.questionStem + `</h4>
                                `+ option_box + `
                                <div class="topic-answer">
                                   <p>您的答案：<span class="userAnswer"></span></p>
                                   <p>正确答案：`+ answer_op + `</p>
                                   <p>解析：`+ analysis + `</p>
                                </div>
                            </div>
                            `;

            });
            test_box += `
                            <div class="jxz-box col-md-12">
                            <h4 class="tesTitle">`+ title + `</h4>
                            `+ info + `
                            `+ topic_box + `
                        </div>
                        `;
        });

        var test_html = `
                    <div class="page-header">
                        <h3 class="text-center">`+ titleB + `</h3>
                    </div>
                    <form class="" id="testForm">
                        <div class="test-form-box">
                            `+ test_box + `
                        </div>
                        <div class="form-group assignment">
                            <button type="button" class="btn btn-primary" onclick="assignment()">交卷</button>
                        </div>
                    </form>`;
        $('#testArea').html(test_html)
        //},
        //error:function(data){
        //alert("网络异常，请稍后重试");
        //}
        //});
    } else {
        swal("试题获取失败！", {
            icon: "error",
        });
    }
}
//交卷
function assignment () {
    $(".testCon h4").css("color", "#555");
    var _temp_tip = "yes";
    var tall = 0;
    $(".testCon").each(function (i) {
        var type = $(this).attr("data-type");
        if (type == 2) {
            if ($(this).find('input[type="checkbox"]:checked').val() == undefined) {
                _temp_tip = "no";
                $(this).find("h4").css("color", "#00B895");
            }
        } else if (type == 1 || type == 3 || type == 7) {
            if ($(this).find('input[type="radio"]:checked').val() == undefined) {
                _temp_tip = "no";
                $(this).find("h4").css("color", "#00B895");
            }
        } else if (type == 4 || type == 5 || type == 8 || type == 6) {
            if ($.trim($(this).find('textarea').val()) == '') {
                _temp_tip = "no";
                $(this).find("h4").css("color", "#00B895");
            }
        }
        tall++;
    });

    if (_temp_tip == "no") {
        alert("eee");
        return;
        // swal({
        //     title: "确定退出？",
        //     text: "中途交卷会自动放弃本次考核",
        //     icon: "warning",
        //     buttons: true,
        //     dangerMode: true,
        // }).then((exit) => {
        //     if (exit) {
        //         return;
        //     }
        // });
    }

    var err = 0;
    $(".testCon").each(function (i) {
        var type = $(this).attr("data-type");
        var aw = $(this).attr("data-answer");
        var set_answer = '';

        if (type == 2) {

            var ckAarray = $(this).find('input[type="checkbox"]:checked');
            var ans = '';
            ckAarray.each(function (i, item) {
                ans += ckAarray.length == i + 1 ? item.value : item.value + " ";
            });
            if (ans != aw) {
                $(this).find("h4").css("color", "red");
                err++;
            }
            set_answer = ans;

        } else if (type == 1 || type == 7) {

            var rd = $(this).find('input[type="radio"]:checked').val();
            if (rd != aw) {
                $(this).find("h4").css("color", "red");
                err++;
            }
            set_answer = rd;

        } else if (type == 3) {

            var rpd = $(this).find('input[type="radio"]:checked').val();
            rpd = rpd == 0 ? "错误" : (rpd == 1 ? "正确" : "");
            if (rpd != aw) {
                $(this).find("h4").css("color", "red");
                err++;
            }
            set_answer = rpd;

        } else if (type == 4 || type == 5 || type == 8 || type == 6) {

            var textVal = $.trim($(this).find('textarea').val());
            if (textVal != aw) {
                $(this).find("h4").css("color", "red");
                err++;
            }
            set_answer = textVal;
        }

        $(this).find("span.userAnswer").text(set_answer);

    });
    $(".topic-answer").show();
}
//数字转换成大写字母
function convert (num) {
    num = num + 1;
    return num <= 26 ? String.fromCharCode(num + 64) : convert(~~((num - 1) / 26)) + convert(num % 26 || 26);
}