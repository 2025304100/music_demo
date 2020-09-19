package com.music_demo.inform.middle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Map;


public class Email {
    private JavaMailSender javaMailSender = new JavaMailSenderImpl();

    public void testSimpleTextMail(Map<String, String> map) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(map.get("sender"));// 发送者，可选的
        mailMessage.setTo(map.get("username"));//接受者
        mailMessage.setSubject(map.get("theme"));//主题
        mailMessage.setText(map.get("text"));//邮件内容

        javaMailSender.send(mailMessage);
    }
}
