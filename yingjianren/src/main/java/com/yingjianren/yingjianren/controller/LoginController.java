package com.yingjianren.yingjianren.controller;

import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.service.emailService;
// import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;

@Controller
public class LoginController {

    private static final String IDENTITY_EMAIL_URL = "/Identity/ConfirmEmail/";

    @Autowired
    emailService emailS;

    @Autowired
    UserRepository userR;

    @Autowired
    JavaMailSenderImpl mailSender;

    @GetMapping("/login")
    public ModelAndView LoginGet(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        String types=request.getParameter("errorMsg");
        if(types!=null){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('"+types+"');</script>");
        }
        if(request.getSession().getAttribute("userId")!=null){
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
        }else{
            model.addAttribute("isLogin",false);
        }
        return modelAndView;
    }


    @PostMapping("/login")
    @ResponseBody
    public String LoginPost(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam Map<String, String> parameter,Model model) throws IOException{
        String email=request.getParameter("email");
        String pwd=request.getParameter("password");

        if(userR.findIsExistEmail(email)==0){
            parameter.put("message", "邮箱不存在");
            parameter.put("status", "no");
            return JSON.toJSONString(parameter);
        }
        Long id=userR.findIdByEmail(email);
        String pwds = userR.findPwdByEmail(email);
        //System.out.println("pwd"+pwd);
        //System.out.println("pwds"+pwds);
        //System.out.println(pwd.equals(pwds));
        if(pwd.equals(pwds)){
            session.setAttribute("userId",id);
            parameter.put("message", "欢迎登录");
            parameter.put("status", "ok");
            if(request.getSession().getAttribute("userId")!=null){
                model.addAttribute("isLogin",true);
                model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
            }else{
                model.addAttribute("isLogin",false);
            }
            return JSON.toJSONString(parameter);

        }

        parameter.put("message", "密码错误");
        parameter.put("status", "no");
        return JSON.toJSONString(parameter);
    }

    @PostMapping("/register")
    @ResponseBody
    public String Register(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam Map<String, String> parameter) throws IOException {
        String username=request.getParameter("username");
        String pwd=request.getParameter("password");
        String email=request.getParameter("email");

        int isExistEmail = userR.findIsExistEmail(email);
        if(isExistEmail>0){
            // 邮箱已注册
            parameter.put("message", "邮箱已存在");
            parameter.put("status", "no");
            return JSON.toJSONString(parameter);
        }

        Random r = new Random();
        int random = r.nextInt(9)+1;
        User user=new User();
        user.setEmail(email);
        user.setUserName(username);
        user.setUserPwd(pwd);
        user.setImgUrl("/headImg/"+random+".png");
        userR.save(user);

        //获得自增的id
        Long id = userR.findIdByEmail(user.getEmail());

        //设置邮件内容
        String title = "影荐人网站用户注册邮箱验证";
        String text = "欢迎您加入《影荐人》，请点击此<a href='http://localhost:8080"+IDENTITY_EMAIL_URL+id+"'>"+"链接"+"</a>确认邮箱";
        try{
            emailS.sendEmail(user.getEmail(), title, text);
            // 成功则返回首页
            session.setAttribute("userId",id);
            parameter.put("message", "注册成功");
            parameter.put("status", "ok");
            return JSON.toJSONString(parameter);
        }
        catch(MessagingException e){
            // undo未成功发送邮件
            parameter.put("message", "邮件发送失败");
            parameter.put("status", "no");
            return JSON.toJSONString(parameter);
        }


    }
}
