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
    public ModelAndView Index() {
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
    @GetMapping("/login")
    public ModelAndView Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        String types=request.getParameter("errorMsg");
        if(types!=null){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('"+types+"');</script>");
        }

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