package com.music_demo.security_module;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //路径拦截
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager());
        bean.setSecurityManager(defaultWebSecurityManager());
        Map<String, String> map = new HashMap<>();
        map.put("/user/login", "anon");
        map.put("/doLogin", "anon");
        map.put("/Io_img", "anon");
        map.put("/user/index", "authc");
        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/user/login");

        return bean;
    }

    //@Bean
//public ShiroFilterChainDefinition shiroFilterChainDefinition(){
//        DefaultShiroFilterChainDefinition chainDefinition=new DefaultShiroFilterChainDefinition();
//    Map<String,String> map=new HashMap<>();
//            map.put("user/login","anon");
//            map.put("doLogin","anon");
//            map.put("Io_img","anon");
//            map.put("*.html","authc");
//            map.put("user/index","authc");
//            map.put("resource/*","anon");
//        chainDefinition.addPathDefinitions(map);
//        return  chainDefinition;
//}
    //会话管理器
    @Bean("defaultWebSecurityManager")
    DefaultWebSecurityManager defaultWebSecurityManager() {

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(mySiroRealm());

        return manager;
    }


    @Bean
    public MyShiroRealm mySiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    //开启shiro aop注解支持.
    @Bean("authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }
}

