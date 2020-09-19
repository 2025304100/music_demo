package com.music_demo.offer;

import com.music_demo.entity.User_entity;
import com.music_demo.redis_model.User_Redis;
import com.music_demo.service_model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class user_Offer {
    @Autowired
    User user;
    @Autowired
    User_Redis red;

    public User_entity user_en(String username) {
        User_entity user;
        user = get_cahe(username);
        if (user == null) {
            user = get_sql(username);
        }
        return user;
    }

    //缓存读取
    private User_entity get_cahe(String username) {
        User_entity user = red.getUser(username);
        return user;
    }

    //数据库获取
    private User_entity get_sql(String username) {
        User_entity use = user.login_User(username);

        return use;
    }
}
