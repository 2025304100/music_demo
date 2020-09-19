package com.music_demo.controller_model;


import com.music_demo.Information_model.email.email;
import com.music_demo.service_model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class Send_verif {
    @Autowired
    email c;
    @Autowired
    User user;

    @PostMapping("email")
    public String Email(String email, HttpSession session) {
        if (email == null)
            return "网络异常!";
        System.out.println(email);
        if (user.Useremail(email))
            return "邮箱以绑定";
        else
            session.setAttribute(email, email);

        String verif_ = String.valueOf((int) (Math.random() * 9000 + 1000));
        try {
            c.testMimeInlineMail(email, verif_);
            session.setAttribute("verif", verif_);
            return "发送成功!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "发送失败!";
    }
}
