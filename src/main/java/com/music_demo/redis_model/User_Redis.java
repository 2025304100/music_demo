package com.music_demo.redis_model;

import com.music_demo.entity.User_entity;
import com.music_demo.entity.user_Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ValueOperations;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties("music.redis.user.cache")
public class User_Redis extends Chache_Redis {
    private int time_user;
    @Autowired
    public_Redis redis;

    public void setTime(int time) {
        this.time_user = time;
    }

    public void setRedis(public_Redis redis) {
        this.redis = redis;
    }

    public int getTime() {
        return time_user;
    }

    public public_Redis getRedis() {
        return redis;
    }

    @Override
    public boolean setText(String key, String text) {
        return false;
    }

    @Override
    public String getText(String key) {

        return null;
    }

    public boolean setUser(String key, User_entity u) {
        try {
            ValueOperations<String, User_entity> val = redis.GetObj_ValueOperation();
            val.set(key, u, time_user, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setuser_Identity(long id, user_Identity user) {
        ValueOperations<Long, user_Identity> val = redis.GetObj_ValueOperation();
        val.set(id, user, time_user, TimeUnit.MINUTES);
        return true;
    }

    public user_Identity getetuser_Identity(long id) {
        ValueOperations<Long, user_Identity> val = redis.GetObj_ValueOperation();
        user_Identity user_identity = val.get(id);
        return user_identity;
    }

    public boolean setUser_time(String key, User_entity u, int time) {
        try {
            ValueOperations<String, User_entity> val = redis.GetObj_ValueOperation();
            val.set(key, u, time, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User_entity getUser(String key) {
        ValueOperations<String, User_entity> val = redis.GetObj_ValueOperation();

        return val.get(key);
    }
}
