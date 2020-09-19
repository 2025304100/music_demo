package com.music_demo.Information_model.email;

import com.music_demo.connection_model.URLConnection_;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamSource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Component
public class email {
    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSimpleTextMail() {   //发送普通文本邮件

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("FX_SKY");// 发送者，可选的
        mailMessage.setTo("zhouguoqiang43@gmail.com");//接受者
        mailMessage.setSubject("测试邮件");//主题
        mailMessage.setText("Test Email send by javaMailSender!");//邮件内容

        javaMailSender.send(mailMessage);
    }

    @Test
    public void testMimeInlineMail(String emil, String li) throws Exception {   //发送HTML格式的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("2025304100@qq.com");
        helper.setTo(emil);
        helper.setSubject("主题：用户注册验证码");
        helper.setText("<html><body><b>你的当时验证码:</b>" + li + "<h1 style='color:red;'>该验证码30分钟有效</h1></html>", true);
        //Resource只接受
//
//        InputStreamSource im=new InputStreamSource() {
//            @Override
//            public InputStream getInputStream() throws IOException {
//                return new URLConnection_().getImageStream("http://source.aicode.cc/favorite.png","GET");
//            }
//        };
//        helper.addInline("极客云",im,"image/png");
        javaMailSender.send(mimeMessage);
    }

    //警告邮箱
    public Boolean Warning(String text, String[] email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("2025304100@qq.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("警告!");
        mimeMessage.setText(text);
        return true;
    }

    @Test
    public void testMimeMail() throws Exception { //发送HTML格式含图片的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("FX_SKY");
        helper.setTo("zhouguoqiang43@gmail.com");
        helper.setSubject("主题：极客云");
        helper.setText("<html><body><img src=\"cid:logo\" ></body></html>", true);
        javaMailSender.send(mimeMessage);
    }

    @Test
    public void testAttachmentMail() throws Exception { //发送含附件的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);// 第二个参数设置为true，表示允许添加附件
        helper.setFrom("FX_SKY");
        helper.setTo("zhouguoqiang43@gmail.com");
        helper.setSubject("发送含图片附件的邮件");
        helper.setText("含有附件的邮件");

        helper.addAttachment(MimeUtility.encodeText("附件-1.jpg"), new File("C:\\Users\\werfdew\\OneDrive\\图片\\本机照片" +
                "\\IMG_20190604_093022.jpg"));
        helper.addAttachment(MimeUtility.encodeText("附件-2.jpg"), new File("C:\\Users\\werfdew\\OneDrive\\图片\\本机照片" +
                "\\IMG_20190604_093022.jpg"));

        javaMailSender.send(mimeMessage);
    }
}
