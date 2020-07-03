package com.yingjianren.yingjianren.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.yingjianren.yingjianren.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class emailServiceImpl implements emailService {
    private static final String fromEmail = "fengc1999@sohu.com";

    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public void sendEmail(String toEmail, String title, String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(title);
        helper.setText(text, true);
        helper.setTo(toEmail);
        helper.setFrom(fromEmail);

        mailSender.send(mimeMessage);
    }
}