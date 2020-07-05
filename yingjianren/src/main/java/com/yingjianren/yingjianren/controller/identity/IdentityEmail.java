package com.yingjianren.yingjianren.controller.identity;

import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IdentityEmail {
    private static final String HOMEPAGE_URL = "/";
    private static final String IDENTITY_EMAIL_ID_URL = "/confirmEmail/{userId}";
    private static final String REDIRECT_TO_HOMEPAGE = "redirect:"+ HOMEPAGE_URL;

    @Autowired
    UserRepository userR;
    
    // 验证邮件的URL
    @GetMapping(IDENTITY_EMAIL_ID_URL)
    public String emailConfirm(@PathVariable Long userId) {
        User user = userR.findById(userId).get();
        if (user != null) {
            user.setConfirm(true);
        }
        return REDIRECT_TO_HOMEPAGE;
    }
}