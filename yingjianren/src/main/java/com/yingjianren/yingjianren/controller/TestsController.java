package com.yingjianren.yingjianren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestsController {
    @GetMapping("/test")
    public String Test(){

        return "search";
    }
}
