package com.music_demo.redis_model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Configuration
public class public_Redis {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public ValueOperations<String, String> GetValueOperation() {
        return stringRedisTemplate.opsForValue();

    }

    public ValueOperations GetObj_ValueOperation() {
        return redisTemplate.opsForValue();
    }

    public RedisTemplate GetRedisTemplate() {
        return redisTemplate;
    }

    public StringRedisTemplate GetStringRedisTemplate() {
        return stringRedisTemplate;
    }
}

