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

    // 首页
    @GetMapping("/")
    public ModelAndView Index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

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

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping("/help")
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("help");
        return modelAndView;
    }
}