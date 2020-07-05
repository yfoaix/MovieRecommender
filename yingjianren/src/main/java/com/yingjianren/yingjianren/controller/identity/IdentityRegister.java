package com.yingjianren.yingjianren.controller.identity;

import javax.mail.MessagingException;

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

/**
 * IdentityRegister
 */

@Controller
public class IdentityRegister {
    private static final String HOMEPAGE_URL = "/";
    private static final String IDENTITY_REGISTER_URL = "/register";
    private static final String IDENTITY_EMAIL_URL = "/confirmEmail/";
    private static final String REDIRECT_TO_HOMEPAGE = "redirect:"+ HOMEPAGE_URL;
    private static final String REDIRECT_TO_REGISTER = "redirect:"+IDENTITY_REGISTER_URL;

    @Autowired
    emailService emailS;

    @Autowired
    UserRepository userR;

    @Autowired
    JavaMailSenderImpl mailSender;

    // 加载注册界面
    @GetMapping(IDENTITY_REGISTER_URL)
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    //注册表单提交
    @PostMapping(IDENTITY_REGISTER_URL)
    public String registerSuccess(@ModelAttribute(name="user") User user, Model model) {
        int isExistEmail = userR.findIsExistEmail(user.getEmail());
        if(isExistEmail>0){
            // 邮箱已注册
            model.addAttribute("isExistEmail", true);
            return REDIRECT_TO_REGISTER;
        }
        model.addAttribute("isExistEmail", false);
        userR.save(user);

        //获得自增的id
        Long id = userR.findIdByEmail(user.getEmail());
        
        //设置邮件内容
        String title = "影荐人网站用户注册邮箱验证";
        String text = "欢迎您加入《影荐人》，请点击此<a href='http://localhost:8080"+IDENTITY_EMAIL_URL+id+"'>"+"链接"+"</a>确认邮箱";
        try{
           emailS.sendEmail(user.getEmail(), title, text);
           // 成功则返回首页
           return REDIRECT_TO_HOMEPAGE;
        }
        catch(MessagingException e){
           // undo未成功发送邮件
           model.addAttribute("emailConfirm", false);
           return REDIRECT_TO_REGISTER;
        }
    }
}