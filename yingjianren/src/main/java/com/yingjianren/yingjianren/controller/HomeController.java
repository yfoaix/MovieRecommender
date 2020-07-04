package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    // 根目录
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        // 命名为templates里的模板名 index对应 index.html
        // static文件夹自动映射到根目录
        model.setViewName("selfSpace");
        // 在视图模型中加入对象，可以通过th标签显示
        model.addObject("title", "title");
        return model;
    }
    // 从url中传入数据

    @GetMapping(value = "/test/{page}")
    // 从该函数的参数中获得
    public ModelAndView Test(@PathVariable("page") Integer page) {
        ModelAndView model = new ModelAndView();
        model.setViewName("test");
        model.addObject("page", page);
        return model;
    }

}