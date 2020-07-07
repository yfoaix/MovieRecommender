package com.yingjianren.yingjianren.controller.selfSpace;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.History;
import com.yingjianren.yingjianren.entity.HistoryRepository;

import com.yingjianren.yingjianren.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/selfspace")
public class HistoryController {
    private static final String SELFSPACE_URL = "/history";

    @Autowired
    HistoryRepository historyR;
    @Autowired
    UserRepository userR;

    @GetMapping({"", SELFSPACE_URL})
    public ModelAndView showHistory(HttpServletRequest req){
        ModelAndView view = new ModelAndView("selfSpace");
        // 获取用户id
        Long userId= (Long) req.getSession().getAttribute("userId");
        List<History> historyList = historyR.findHistoryByUserId(userId);
        view.addObject("historyList", historyList);
        view.addObject("isLogin", req.getSession().getAttribute("userId") != null);
        if(req.getSession().getAttribute("userId")!=null){
            view.addObject("user",userR.findUserById(((Long) req.getSession().getAttribute("userId"))));
        }
        return view;
    }
}