package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final String HOMEPAGE_URL = "/";

    // 首页
    @GetMapping(HOMEPAGE_URL)
    public String home() {
        return "home";
    }
}