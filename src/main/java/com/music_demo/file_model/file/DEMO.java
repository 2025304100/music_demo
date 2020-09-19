package com.music_demo.file_model.file;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter
public class DEMO implements Filter {
    @Value("${music.file.cors}")
    String corsIp;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse re, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) re;
        String ip = req.getRemoteAddr();
        System.out.println(req.getPathInfo());

        HttpSession ses = req.getSession();
        String url = req.getRequestURI();

//资源放行
        // response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有
        response.setHeader("Access-Control-Allow-Origin",corsIp ); // 允许白名单IP
        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 预检请求的结果缓存60分钟

        response.setHeader("Access-Control-Max-Age", "3600");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, XRequestedWith, Content-Type, LastModified");//

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}

