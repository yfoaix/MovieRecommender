//模拟后端传过来的数据表单
var movieArr = [
    { name: 'asdfaq2e', url: "", src: 'img/gallery-tn-01.jpg', director: 'afae', language: '汉语', tag: '', length: 120, time: 1998, mainActor: 'afdf', score: 3.2 },
    { name: '12r3fqag', url: "", src: 'img/gallery-tn-02.jpg', director: 'sdvz', language: '英语', tag: '', length: 180, time: 2007, mainActor: 'rgwe', score: 1.5 },
    { name: 'q3344654', url: "", src: 'img/gallery-tn-03.jpg', director: 'awf2', language: '西班牙语', tag: '', length: 200, time: 1984, mainActor: 'awdc', score: 4.2 },
    { name: 'asfw4fev', url: "", src: 'img/gallery-tn-04.jpg', director: 'q2edq', language: '日语', tag: '', length: 80, time: 2017, mainActor: 'awref', score: 5 },
    { name: 'af4wtgww', url: "", src: 'img/gallery-tn-05.jpg', director: 'qwdqwg', language: '其他', tag: '', length: 126, time: 1969, mainActor: 'wrda', score: 2.6 }
];

// 初始变量
var oUl = document.getElementsById('resultElement')[0];
var oInput = document.getElementsById('inputSearch')[0];

store.subscribe(function () {
    RenderPage(lastFilterArr(movieArr));//
});

// 数据渲染页面
function RenderPage(data) {
    //遍历数组长度添加
    var htmlStr = ''; //设定一个空字符串接收数据
    oUl.innerHTML = ''; //

    data.forEach(function (ele, index, self) {
        htmlStr = htmlStr + '<a url="' + ele.url + '"><figure class="effect-honey tm-gallery-item"><img src="'
            + ele.src + '" alt="Image" class="img-fluid"><figcaption><h2><i>' + ele.director + '<br><span>' + ele.tag +
            '</span><br>评分' + ele.score + '<i class="fas fa-star"></i></i></h2></figcaption><p>' + ele.name + '</p></figure></a>';
        //遍历出后端里面的数据
    });
    oUl.innerHTML = htmlStr; //把数据以HTML形式付给页面
}
RenderPage(movieArr);// 执行渲染函数

//添加行为
oInput.oninput = debounce(function () { //输入触发过滤
    store.dispatch({ type: 'text', value: this.value });
    //传到渲染页面的函数中，重新绘制页面
}, 1000);//最后再加上防抖功能，也就是相当于套上一个定时器，输入文本1秒后再执行搜索

//btn style
var oBtnArr = [].slice.call(document.getElementsByClassName('filter-item'), 0);
//把btn类数组转为数组

var lastActiveBtn = oBtnArr[5];

oBtnArr.forEach(function (ele, index, self) {
    ele.onclick = function () {
        changeActive(this);
        RenderPage(filterArrBySex(movieArr, this.getAttribute('sex')));
        store.dispatch({ type: 'sex', value: this.getAttribute('sex') });
        //渲染过滤后的性别
    }
});

//点击按钮切换样式
function changeActive(curActiveBtn) {
    curActiveBtn.className = 'btn active';
    lastActiveBtn.className = 'btn';
    lastActiveBtn = curActiveBtn;
}

// text -- 根据文本来过滤
function filterArrByText(data, text){
    if(!text){ //非文本则返回数据表单
        return data;
    }else{  //否则进入一下进行过滤
        return data.filter(function(ele, index){
            return ele.name.indexOf(text) != -1;
            //返回indexOf文本不等于-1的文本
            //如果输入的文本不在数据表单名字里面indexOf则会返回-1
        });
    }
}