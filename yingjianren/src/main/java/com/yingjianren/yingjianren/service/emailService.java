package com.yingjianren.yingjianren.service;

import javax.mail.MessagingException;

public interface emailService {
    public void sendEmail(String toEmail, String title, String text) throws MessagingException;
}