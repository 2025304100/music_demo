package com.music_demo.service_model;

import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.mapper.dao.Resource;
import com.music_demo.redis_model.Music;
import com.music_demo.security_module.encryption_model.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class music_Resd extends music_Re {
    //单例查询
    @Autowired
    Resource resource;
    @Autowired
    Music mu;

    @Override
    public int music_in(music mi) {
        return super.music_in(mi);
    }

    public String img_Music(long id, String title) {

        return null;
    }

    private List<music> get_Music(String key, int cont, int size) throws UnsupportedEncodingException {

        List<music> list = resource.get_Music_dm(key, (cont - 1) * size, cont);
        if (list == null) {
            list = new ArrayList<>();
        }
        list = md(list);

        return list;
    }

    @Override
    public List<music> md(List<music> list) throws UnsupportedEncodingException {
        for (music i : list) {
            i.setIdm(Base64.encodeToString(String.valueOf(i.getId()).getBytes("utf-8")));
            //注入缓存识别
            if (mu.getMusic_Temporary(i.getIdm()) == null) {
                //添加临时缓存
                mu.setMusic_Temporary(i.getIdm(), i);
            } else {
                System.out.println("id:" + i.getId() + "已添加");
            }
        }
        return list;
    }

    @Override
    public List<music_des> ind_music(List<music> mu, String type) throws UnsupportedEncodingException {
        return super.ind_music(mu, type);
    }

    public List<music_des> getMusic_con(String key, int cont, int size) throws UnsupportedEncodingException {
        List<music> list = get_Music(key, cont, size);
        List<music_des> list_rs = ind_music(list, key);
        return list_rs;
    }


}
