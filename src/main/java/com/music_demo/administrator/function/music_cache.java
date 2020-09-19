package com.music_demo.administrator.function;

import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.redis_model.Music;
import com.music_demo.service_model.music_Re;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class music_cache {
    @Qualifier("music_Re")
    @Autowired
    music_Re music_re;
    @Autowired
    Music music;

    private final int CONTEXT = 200;

    public void setMusic(String type) throws UnsupportedEncodingException {
        System.out.println(type);
        List<music> kd = music_re.get_Music(type, CONTEXT);
        Map<String, music> map = music_re.MD(kd);
        List<music_des> list = music_re.ind_music(kd, type);
        music.setMusic(type, map);
        music.setMusic(type + "index", list);
    }

    public Map<String, music> getMusic(String type) throws UnsupportedEncodingException {
        return new HashMap<>();
    }
}
