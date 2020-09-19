package com.music_demo.monitor;

import com.music_demo.entity.User_entity;
import com.music_demo.entity.user_Identity;
import com.music_demo.monitor.recorder.userLogin;
import com.music_demo.redis_model.User_Redis;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebListener("/user/login")
public class login implements ServletRequestListener {
    private HttpServletRequest req;
    private HttpSession ses;
    private Logger log = Logger.getLogger(com.music_demo.monitor.login.class);
    @Autowired
    User_Redis rds;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        this.req = (HttpServletRequest) sre.getServletRequest();
        this.ses = req.getSession();
        String username = (String) this.ses.getAttribute("username");

        try {
            if (username != null) {
                User_entity us = this.rds.getUser(username);
                user_Identity user_identity = rds.getetuser_Identity(us.getId());
                userLogin.amendContext(-1, user_identity.getGrade());
            }
        } catch (NullPointerException e) {
            log.error("错误:com.music_demo.monitor.login.request 值为null");
        }
    }
}
