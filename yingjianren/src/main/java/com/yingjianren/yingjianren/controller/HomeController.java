package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HomeController {
    private static final String HOMEPAGE_URL = "/";

    // 首页
    @GetMapping(HOMEPAGE_URL)
    public ModelAndView Index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if(request.getSession()!=null){
            System.out.println("主页从session中获取用户");
            System.out.println(request.getSession().getAttribute("user"));
        }

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
        modelAndView.setViewName("selfspace");
        return modelAndView;
    }

    @GetMapping("/movieinfo")
    public ModelAndView MovieInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("movieinfo");
        return modelAndView;
    }
}