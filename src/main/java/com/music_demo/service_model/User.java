package com.music_demo.service_model;

import com.music_demo.entity.User_Role;
import com.music_demo.entity.User_entity;
import com.music_demo.entity.user_Identity;
import com.music_demo.mapper.dao.UserMapper;
import com.music_demo.security_module.encryption_model.single_encryption;
import com.music_demo.service_model.serviceimp.Jurisdiction_Role;
import com.music_demo.service_model.serviceimp.UserImp;

import org.apache.log4j.Logger;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class User implements UserImp, Jurisdiction_Role {
    @Autowired
    private UserMapper userMapper;
    //加载long4组件的
    private Logger log = Logger.getLogger(com.music_demo.service_model.User.class);

    @Override
    public boolean Register(User_entity use) {
        //判断邮箱/手机是否被注册过
        if (true) {
            //数据加密
            try {
                use.setPas(single_encryption.md5_key(use.getPas(), "SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Integer a = userMapper.Register(use), b, c;
            user_Identity user_identity = new user_Identity();
            user_identity.setId(use.getId());
            b = userMapper.Identity(user_identity);
            User_Role user_role = new User_Role();
            user_role.setId(use.getId());
            c = userMapper.user_Role(user_role);
            System.out.println("c:" + c);
            return true;
        }
        return false;
    }

    //获取用户附加信息
    public user_Identity getIdentiy(long id) {
        user_Identity us;
        if (id != 0) {
            us = userMapper.getIdentity(id);
        } else {
            log.error("com.music_demo.service_model.User.getIdentiy 获取id 为0 数据获取失败");
            throw new NullValueInNestedPathException(com.music_demo.service_model.User.class, "方法getIdentity id未获取数据:  id为0");
        }
        return us;
    }

    //账号注册合法性查询
    public boolean Username(String username) {
        String user = userMapper.getUsername(username);

        if (user == null)
            return false;
        return true;
    }

    public boolean Useremail(String email) {
        String user = userMapper.getEmail(email);
        System.out.println(user);
        if (user == null)
            return false;

        return true;
    }

    public User_entity login_User(String username) {
        User_entity us;
        us = userMapper.login_User(username);
        return us;
    }

    @Override
    public String Login_Servlet(String username) {

        return null;
    }

    @Override
    public String Resource(String key) {
        return null;
    }

    @Override
    public String Role_User(String role) {
        return null;
    }

    //获取用户的信息
    public Object userInfo(String titles, String username) {
        Long id = userMapper.getUser_id(username);
        if (id == null) {
            throw new NullPointerException();
        }
        Object ob = userMapper.getUser(titles, id);
        if (ob == null) {
            throw new NullPointerException();
        }
        return ob;
    }

}
