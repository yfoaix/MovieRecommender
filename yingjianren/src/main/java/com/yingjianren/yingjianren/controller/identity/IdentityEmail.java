package com.yingjianren.yingjianren.controller.identity;

import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class IdentityEmail {


    @Autowired
    UserRepository userR;
    
    // 验证邮件的URL
    @GetMapping("/Identity/ConfirmEmail")
    public String emailConfirm(HttpServletRequest request, HttpSession session, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String email=request.getParameter("email");
        String idMd5=request.getParameter("id");
        if(email==null||idMd5==null||userR.findIdByEmail(email)==null){
            return "/login?errorMsg="+ URLEncoder.encode("验证异常，请尝试登录，若无法登录，请重新注册","utf-8");
        }
        Long id=userR.findIdByEmail(email);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(id.toString().getBytes());
        byte[] result = md.digest();
        String idMd5_search=new BigInteger(1, result).toString(16);
        if(idMd5.equals(idMd5_search)){
            session.setAttribute("userId",id);
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
            return "redirect:/index";
        }
        return "/login?errorMsg="+ URLEncoder.encode("验证异常，请尝试登录，若无法登录，请重新注册","utf-8");

    }
}