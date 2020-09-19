package com.music_demo.security_module;

import com.music_demo.entity.User_entity;
import com.music_demo.entity.user_Identity;
import com.music_demo.redis_model.User_Redis;
import com.music_demo.security_module.encryption_model.single_encryption;
import com.music_demo.service_model.User;


import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    User user;
    @Autowired
    User_Redis rds;
    private Logger lg = Logger.getLogger(com.music_demo.security_module.MyShiroRealm.class);

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("权限");
        return null;
    }

    //权限分配
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String d_pas = null;
        System.out.println("认证");
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;//获取用户信息
        String user_ = token1.getUsername();
        User_entity user_entity = user.login_User(user_);
        d_pas = user_entity.getPas();
        long id = user_entity.getId();
        System.out.println("权限获取:" + id);
        try {
            if (d_pas.equals(single_encryption.md5_key(String.valueOf(token1.getPassword()), "SHA-256"))) {
                System.out.println(user_);
                rds.setUser(user_, user_entity);
                new Thread(() -> Identity(id)).start();
                return new SimpleAuthenticationInfo(token1.getUsername(), token1.getPassword(), getName());
            } else
                throw new AuthenticationException();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }

        // 当没有用户的时候，抛出异常
        // throw new AuthenticationException();
    }

    public void Identity(long id) {
        if (id == 0) {
            lg.error("com.music_demo.security_module.MyShiroRealm.Identity 获取的id 为0");
            throw new NullValueInNestedPathException(com.music_demo.security_module.MyShiroRealm.class, "Idntity获取数为0");
        }
        System.out.println(id);
        user_Identity identiy = user.getIdentiy(id);
        if (identiy == null) {
            lg.error("com.music_demo.security_module.MyShiroRealm.Identity 获取的identity 为null");
            throw new NullValueInNestedPathException(com.music_demo.security_module.MyShiroRealm.class, "Idntity获取数为null");
        }
        boolean is = rds.setuser_Identity(id, identiy);
        if (!is) {
            lg.error("com.music_demo.security_module.MyShiroRealm.Identity未写入缓存");
            throw new NullValueInNestedPathException(com.music_demo.security_module.MyShiroRealm.class, "Idntity写入缓存失败");
        }
    }
}

