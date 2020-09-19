package com.music_demo.redis_model;

import com.music_demo.controller_model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "musicredis.music")
public class Music_Chache extends Chache_Redis {
    public long time;
    @Autowired
    public_Redis redis;

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean setText(String key, String text) {
        ValueOperations<String, String> ds = redis.GetValueOperation();
        if (ds != null) {
            ds.set(key, text, time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public boolean setText_Time(String key, String text, Long Time, TimeUnit unit) {
        ValueOperations<String, String> ds = redis.GetValueOperation();
        if (ds != null) {
            ds.set(key, text, Time, unit);
            return true;
        }
        return false;
    }

    @Override
    public String getText(String key) {
        ValueOperations<String, String> ds = redis.GetValueOperation();
        if (ds != null) {
            return ds.get(key);
        }
        return null;
    }

    public Music_Up getDemo(String key) {
        ValueOperations ds = redis.GetObj_ValueOperation();
        if (ds != null) {
            return (Music_Up) ds.get(key);
        }
        return null;
    }

    public boolean SetDemo(String key, Music_Up demo) {
        ValueOperations ds = redis.GetObj_ValueOperation();
        if (ds != null) {
            System.out.println("dsferrgggggg"+key);
            ds.set(key, demo, time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public boolean Isexists(String key) {
        //移除过期时间
        return redis.GetStringRedisTemplate().persist(key);
    }
}
