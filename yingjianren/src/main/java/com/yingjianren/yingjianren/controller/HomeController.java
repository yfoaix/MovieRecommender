package com.yingjianren.yingjianren.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private static final String HOMEPAGE_URL = "/";
    private static final String IDENTITY_REGISTER_URL = "/Identity/Account/Register";
    private static final String IDENTITY_LOGIN_URL = "/Identity/Account/Login";
    private static final String IDENTITY_EMAIL_URL = "/Identity/ConfirmEmail/{userId}";
    private static final String REDIRECT_HOMEPAGE = "redirect:/";

    @Autowired
    emailService emailS;

    @Autowired
    UserRepository userR;

    @Autowired
    JavaMailSenderImpl mailSender;

    // 首页
    @GetMapping(HOMEPAGE_URL)
    public String index() {
        return "home";
    }

    // 加载注册界面
    @GetMapping(IDENTITY_REGISTER_URL)
    public String register(Model model) {
        User user = new User();
        user.setEmail("dddd");
        model.addAttribute("user", user);
        return "register";
    }

    //注册表单提交
    @PostMapping(IDENTITY_REGISTER_URL)
    public String registerSuccess(@ModelAttribute(name="user") User user) {
        System.out.println("user name:"+user.getUserName());
        System.out.println("user name:"+user.getUserName());
        System.out.println("user name:"+user.getUserName());
        System.out.println("user name:"+user.getUserName());
        System.out.println("user name:"+user.getUserName());
        userR.save(user);
        

        
        
        //获得自增的id，计算md5
        //Long id = userR.selectIdByEmail(email);
            // String md5 = DigestUtils.md5DigestAsHex(commonUtil.long2Bytes(id));
            // Model.addObject("userId", id);
        //String title = "影荐人网站用户注册邮箱验证";
        //String text = "请点击此链接："+"确认注册";
        //try{
        //    emailS.sendEmail(email, title, text);
        //}
        //catch(MessagingException e){
        //    // undo未成功发送邮件
        //}
        return REDIRECT_HOMEPAGE;
    }

    // 加载登录界面
    @GetMapping(IDENTITY_LOGIN_URL)
    public String login() {
        return "login";
    }

    // 登录界面提交
    @PostMapping(IDENTITY_LOGIN_URL)
    public String loginSuccess(){
        return REDIRECT_HOMEPAGE;
    }

    // 验证邮件的URL
    @GetMapping(IDENTITY_EMAIL_URL)
    public String emailConfirm(Long userId){
        User user = userR.findById(userId).get();
        user.setConfirm(true);
        return REDIRECT_HOMEPAGE;
    }

    // //根目录
    // @GetMapping("/")
    // public ModelAndView Index(){
    // ModelAndView model = new ModelAndView();
    // //命名为templates里的模板名 index对应 index.html
    // //static文件夹自动映射到根目录
    // model.setViewName("index");
    // //在视图模型中加入对象，可以通过th标签显示
    // model.addObject("title", "title");
    // return model;
    // }
    // //从url中传入数据

    // @GetMapping(value = "/test/{page}")
    // // 从该函数的参数中获得
    // public ModelAndView Test(@PathVariable("page") Integer page) {
    //     ModelAndView model = new ModelAndView();
    //     model.setViewName("test");
    //     model.addObject("page", page);
    //     return model;
    // }

}