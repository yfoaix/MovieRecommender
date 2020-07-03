package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
<<<<<<< HEAD
    private static final String HOMEPAGE_URL = "/";
=======
    //根目录
    @GetMapping("/")
    public ModelAndView layout(){
        ModelAndView model = new ModelAndView();
        //命名为templates里的模板名 index对应 index.html
        //static文件夹自动映射到根目录
        model.setViewName("layout");
        //在视图模型中加入对象，可以通过th标签显示
        model.addObject("title", "title");
        return model;
    }
    //从url中传入数据
>>>>>>> origin/UIpages_k

    // 首页
    @GetMapping(HOMEPAGE_URL)
    public String home() {
        return "home";
    }
}