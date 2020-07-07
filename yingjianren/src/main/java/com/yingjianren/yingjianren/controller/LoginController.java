package com.yingjianren.yingjianren.controller;

import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.service.emailService;
// import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
        model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);
        return modelAndView;
    }


    @PostMapping("/login")
    public String LoginPost(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException{
        String email=request.getParameter("email");
        String pwd=request.getParameter("password");

        if(userR.findIsExistEmail(email)==0){
            response.sendRedirect(request.getContextPath()+ "/login?errorMsg="+ URLEncoder.encode("邮箱不存在，请检查输入信息或者重新输入","utf-8"));
            return "login";
        }
        Long id=userR.findIdByEmail(email);
        String pwds = userR.findPwdByEmail(email);
        System.out.println("pwd"+pwd);
        System.out.println("pwds"+pwds);
        System.out.println(pwd.equals(pwds));
        if(pwd.equals(pwds)){
            session.setAttribute("userId",id);
            return "redirect:/";

        }

        model.addAttribute("pwdConfirm", false);
        response.sendRedirect(request.getContextPath()+ "/login?errorMsg="+ URLEncoder.encode("密码错误","utf-8"));
        return "login";
    }

    @PostMapping("/register")
    public String Register(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
        String username=request.getParameter("username");
        String pwd=request.getParameter("password");
        String pwd2=request.getParameter("password2");
        String email=request.getParameter("email");


        if(username==null||pwd==null||pwd2==null||email==null){
            response.sendRedirect(request.getContextPath()+ "/login?errorMsg="+ URLEncoder.encode("请输入完整信息","utf-8"));
            return "login";
        }

        if(!pwd.equals(pwd2)){
            response.sendRedirect(request.getContextPath()+ "/login?errorMsg="+ URLEncoder.encode("两次密码不一致，请检查输入","utf-8"));
            return "login";
        }

        int isExistEmail = userR.findIsExistEmail(email);
        if(isExistEmail>0){
            // 邮箱已注册
            model.addAttribute("isExistEmail", true);
            response.sendRedirect(request.getContextPath()+ "/login?errorMsg="+ URLEncoder.encode("邮箱已被注册","utf-8"));
            return "login";
        }

        User user=new User();
        user.setEmail(email);
        user.setUserName(username);
        user.setUserPwd(pwd);

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
            session.setAttribute("userId",id);
            return "redirect:/";
        }
        catch(MessagingException e){
            // undo未成功发送邮件
            model.addAttribute("emailConfirm", false);
            return "redirect:/login";
        }


    }
}
