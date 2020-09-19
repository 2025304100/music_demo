package com.music_demo.file_model.file;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class interceptor_As implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession ses = request.getSession();
        String ysername = (String) ses.getAttribute("username");
//        System.out.println("ffff"+ysername);
        String url = request.getRequestURI();
        System.out.println();
        if (ysername == null) {
            response.getWriter().write("402");
            return false;
        } else if (url.substring(url.lastIndexOf("/") + 1).equals("role")) {
            System.out.println("完成了");
            response.getWriter().write("200");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
