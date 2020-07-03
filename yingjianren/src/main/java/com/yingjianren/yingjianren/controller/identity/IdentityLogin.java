package com.yingjianren.yingjianren.controller.identity;

import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IdentityLogin {
    private static final String HOMEPAGE_URL = "/";
    private static final String IDENTITY_REGISTER_URL = "/Identity/Account/Register";
    private static final String IDENTITY_LOGIN_URL = "/Identity/Account/Login";
    private static final String REDIRECT_TO_HOMEPAGE = "redirect:"+ HOMEPAGE_URL;
    private static final String REDIRECT_TO_LOGIN = "redirect:"+IDENTITY_LOGIN_URL;
    private static final String REDIRECT_TO_REGISTER = "redirect:"+IDENTITY_REGISTER_URL;

    @Autowired
    emailService emailS;

    @Autowired
    UserRepository userR;

    @Autowired
    JavaMailSenderImpl mailSender;
    
    // 加载登录界面
    @GetMapping(IDENTITY_LOGIN_URL)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // 登录表单提交
    @PostMapping(IDENTITY_LOGIN_URL)
    public String loginSuccess(@ModelAttribute User user, Model model){
        String email = user.getEmail();
        if(userR.findIsExistEmail(email)==0){
            model.addAttribute("isExistEmail", true);
            return REDIRECT_TO_LOGIN; 
        }
        String pwd = userR.findPwdByEmail(email);
        if(pwd.equals(user.getUserPwd())){
            model.addAttribute("isLogin", true);
            return REDIRECT_TO_HOMEPAGE;
        }
        model.addAttribute("pwdConfirm", false);
        return REDIRECT_TO_LOGIN;
    }
}