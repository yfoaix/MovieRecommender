package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static final String HOMEPAGE_URL = "/";

    // 首页
    @GetMapping(HOMEPAGE_URL)
    public ModelAndView Index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView Search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }
    @GetMapping("/selfspace")
    public ModelAndView SelfSpace() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("selfSpace");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView Login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("/movieinfo")
    public ModelAndView MovieInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("movieinfo");
        return modelAndView;
    }
}