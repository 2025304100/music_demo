package com.music_demo.service_model;

import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.error_model.err.coding;
import com.music_demo.mapper.dao.Resource;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.serviceimp.codingMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
public class select_music extends music_Resd {
    @Autowired
    Resource re;

    public List<music> get_Music(String text) throws UnsupportedEncodingException {
        List list = re.get_MusicLike(text);
        list = md(list);
        return list;
    }

    @Override
    public List<music> md(List<music> list) throws UnsupportedEncodingException {
        for (music i : list) {
            i.setIdm(Base64.encodeToString(String.valueOf(i.getId()).getBytes("utf-8")));
        }
        return list;
    }


    public List<music_des> getMusic_con(String key, String type) throws UnsupportedEncodingException {
        List<music> list = get_Music(key);
        List<music_des> list_rs = ind_music(list, type);
        return list_rs;
    }

    public music getMusic_ls(String idpas) throws UnsupportedEncodingException, coding {
        if (!Base64.isBase64(idpas.getBytes()))
            throw new coding();
        String idsrc = new String(Base64.decode(idpas.getBytes()));

        long id = Long.parseLong(idsrc);
        music ts = re.get_Musicind(id);
        ts.setIdm(Base64.encodeToString(String.valueOf(id).getBytes("utf-8")));
        return ts;
    }
}
