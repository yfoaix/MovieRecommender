package com.yingjianren.yingjianren.controller;

import com.yingjianren.yingjianren.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    UserRepository userR;
    // 首页
    @GetMapping(value = {"/","/index"} )
    public ModelAndView Index(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if(request.getSession().getAttribute("userId")!=null){
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
        }else{
            model.addAttribute("isLogin",false);
        }

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
        if(request.getSession().getAttribute("userId")!=null){
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
        }else{
            model.addAttribute("isLogin",false);
        }
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