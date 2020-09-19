package com.music_demo.redis_model;

import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class Music extends Chache_Redis {
    @Autowired
    public_Redis redis;

    @Autowired

    @Override
    boolean setText(String key, String text) {
        return false;
    }

    @Override
    String getText(String key) {
        return null;
    }

    public boolean Music_Audio(music min) {
        ValueOperations va = redis.GetObj_ValueOperation();
        return false;
    }

    public void setMusic(String type, Map<String, music> map) {
        ValueOperations val = redis.GetObj_ValueOperation();
        val.set(type, map);
    }

    public void setMusic(String type, List<music_des> map) {
        ValueOperations val = redis.GetObj_ValueOperation();
        val.set(type, map);
    }

    public void setMusic_Temporary(String id, music mi) {
        ValueOperations val = redis.GetObj_ValueOperation();
        val.set(id, mi, 30, TimeUnit.MINUTES);
    }

    public music getMusic_Temporary(String id) {
        ValueOperations val = redis.GetObj_ValueOperation();
        return (music) val.get(id);
    }

    public Map<String, music> getMusic(String type) {
        ValueOperations val = redis.GetObj_ValueOperation();

        return (Map<String, music>) val.get(type);
    }

    public List<music_des> getMusic_list(String type) {
        ValueOperations val = redis.GetObj_ValueOperation();
        List<music_des> list = (List<music_des>) val.get(type);
        return list;
    }
}
