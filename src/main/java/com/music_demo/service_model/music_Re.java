package com.music_demo.service_model;

import com.music_demo.entity.User_entity;
import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.mapper.dao.Resource;
import com.music_demo.redis_model.Music_Up;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.serviceimp.codingMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class music_Re implements codingMusic {
    @Autowired
    Resource resource;
    @Value("${music.host.server.Host}")
    String host;
    public int music_in(music mi) {

        int i = resource.music_red(mi);


        return i;
    }

    //用鱼缓存接口
    public List<music> get_Music(String key, int cont) throws UnsupportedEncodingException {
        List<music> list;
        System.out.println(key);
        list = resource.get_Music(key, cont);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (music i : list
        ) {
            System.out.println(i.toString());
        }
        list = md(list);

        return list;
    }

    public List<music> md(List<music> list) throws UnsupportedEncodingException {
        for (music i : list) {
            i.setIdm(Base64.encodeToString(String.valueOf(i.getId()).getBytes("utf-8")));
            System.out.println(i.toString());
        }
        return list;
    }

    //模糊查询
    public List<music> get_MusicLike(String key) {
        List<music> list = resource.get_MusicLike(key);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    //缓存获取
    public Map<String, music> MD(List<music> mu) throws UnsupportedEncodingException {
        Map<String, music> map = new HashMap<>();
        for (music i : mu) {
            map.put(i.getIdm(), i);
        }
        return map;
    }

    //数据获取
    public List<music_des> ind_music(List<music> mu, String type) throws UnsupportedEncodingException {
        List<music_des> li = new ArrayList<>();

        for (music i : mu) {
            music_des ins = new music_des();
            ins.setContext(i.getContext());
            ins.setIdpas(i.getIdm());
            ins.setFile_img( host + "/music/imgfile/" + type + "/" + i.getIdm());
            ins.setFile_music( host + "/music/audio/" + type + "/" + i.getIdm());
            ins.setFile_lyirc(i.getFile_lyirc());
            ins.setMusic_name(i.getMusic_name());
            ins.setMusic_index(i.getMusic_index());
            ins.setMusic_author_name(i.getMusic_author_name());
            li.add(ins);
        }
        return li;
    }
    //其他获取

}
