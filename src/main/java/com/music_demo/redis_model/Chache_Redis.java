package com.music_demo.redis_model;

import com.music_demo.controller_model.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public abstract class Chache_Redis {
    //缓存有效时间
    Login time;
    @Autowired
    public RedisTemplate redisTemplate;
    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    abstract boolean setText(String key, String text);

    abstract String getText(String key);

}
