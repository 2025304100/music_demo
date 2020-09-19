package com.music_demo.service_model.serviceimp;

import com.music_demo.entity.User_entity;

public interface UserImp {
    //用户注册接口
    public boolean Register(User_entity use);

    //用户登录接口
    public String Login_Servlet(String username);

    //用户权限获取
    public String Role_User(String role);

}
