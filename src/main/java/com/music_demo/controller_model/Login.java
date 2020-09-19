package com.music_demo.controller_model;

import com.music_demo.Information_model.email.email;
import com.music_demo.entity.User_entity;
import com.music_demo.service_model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
//@Configuration--注销bean的

@Controller
@RequestMapping("/user")
public class Login {
    @Autowired
    User user;
    @Autowired
    email e;
    @Value("${music.host.client.Host}")
    private  String Host;

    @GetMapping("/img")
    public String verify() {
        return "redirect:/Io_img";
    }
    @GetMapping("/find")
    public ModelAndView toFind(){
        ModelAndView find = new ModelAndView("find");
        find.getModel().put("Path",Host+"music/index.html");
        return find;
    }
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.getModel().put("Host",Host);

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView Register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.getModel().put("Host",Host);
        return modelAndView;
    }

    @GetMapping("/updata_mudic")
    public ModelAndView Updata_music() {
        ModelAndView modelAndView = new ModelAndView("music_file_cache/demos");
        modelAndView.getModel().put("Host",Host);
        return modelAndView;
    }

    @PostMapping("login")
    public @ResponseBody
    String demo(HttpSession ses, String username, String password, String verification) {
        String verification_ = (String) ses.getAttribute("verification_code");
        System.out.println(verification + verification_);
        if (verification.equals(verification_)) {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                try {
                    subject.login(token);
                    ses.setAttribute("username", username);
                } catch (AuthenticationException e) {
                    return "账号密码错误";
                }
            }
        } else {
            return "验证码错误";
        }
        //写入户数据
        return "登录成功!";
    }

    @RequestMapping("register")
    public @ResponseBody
    String Register(User_entity form_name, String email_verification, HttpSession session) {
        String email;
        String username;
        String email_ses;
        String username_ses;
        if (form_name == null)
            return "网络异常";
        if (session.getAttribute("is_log") == null)
            session.setAttribute("is_log", true);
        Boolean is = (Boolean) session.getAttribute("is_log");
        if (email_verification.equals(session.getAttribute("verif"))) {
            if (!is) {
                return "正在注册,请勿重复提交...";
            }
            email = form_name.getEmail();
            username = form_name.getUsername();
            email_ses = (String) session.getAttribute(email);
            username_ses = (String) session.getAttribute(username);
            if (!email_ses.equals(email) || !username_ses.equals(username))
                return "邮箱/账号被重用,你修改";
            user.Register(form_name);

            session.setAttribute("is_log", false);
            return "注册成功!";
        } else {
            return "验证码错误";
        }
    }

    @PostMapping("us")
    public @ResponseBody
    String login_is(String username, HttpSession ses) {
        System.out.println(username);
        if (!(user.Username(username))) {
            ses.setAttribute(username, username);
            return "0";
        } else
            return "-1";
    }

    @RequestMapping("index")
    public String Index() {

        return "index.html";

    }

}

