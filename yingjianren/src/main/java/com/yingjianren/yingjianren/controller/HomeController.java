package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HomeController {

    // 首页
    @GetMapping(value = {"/","/index"} )
    public ModelAndView Index(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);

        return modelAndView;
    }

    //@GetMapping("/selfspace")
    //public ModelAndView SelfSpace(HttpServletRequest request,Model model) {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("selfspace");
    //    model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);
    //    return modelAndView;
    //}
    @GetMapping("/movieinfo")
    public ModelAndView MovieInfo(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("movieinfo");
        model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);
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