package com.yingjianren.yingjianren.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//使用restcontroller 就可以不使用 @ResponseBody 来防止跳转到链接

@RestController
public class TestController {
    @RequestMapping(value = "/ajaxtest/{page}")
    
    public String GetPage(@PathVariable("page") Integer page){
        return "{\"movie\":\"1\"}";
    }

}