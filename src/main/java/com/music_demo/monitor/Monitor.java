package com.music_demo.monitor;

import com.music_demo.entity.User_entity;
import com.music_demo.entity.user_Identity;
import com.music_demo.monitor.recorder.userLogin;
import com.music_demo.redis_model.User_Redis;
import org.apache.catalina.Session;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


//添加监听器
@WebListener
public class Monitor implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private HttpServletRequest req;
    private HttpSession ses;

    @Autowired
    User_Redis rds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //上下文启动
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /**
     * 该监听器用啦监听客流量
     *
     * @param sre
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        req = (HttpServletRequest) sre.getServletRequest();
        ses = req.getSession();
        String username = (String) ses.getAttribute("username");
        if (username == null) {

            return;
        }
        if (ses.isNew()) {
            User_entity user = rds.getUser(username);
            user_Identity user_identity = rds.getetuser_Identity(user.getId());
            userLogin.addContext(user_identity.getGrade());
        }

    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession ses = se.getSession();
        //用于记录登录人数
        userLogin.addContext(-1);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }


    /**
     * 创建再登录模块
     */
    public boolean amend(int oldindex, int newindex) {
        try {
            userLogin.amendContext(oldindex, newindex);
        } catch (Exception e) {

            return true;
        }
        return false;
    }
}
