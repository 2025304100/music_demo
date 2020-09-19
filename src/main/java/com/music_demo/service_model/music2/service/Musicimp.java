package com.music_demo.service_model.music2.service;

import com.music_demo.entity.loginMusic;
import com.music_demo.entity.loginMusic_des;
import com.music_demo.entity.music;
import com.music_demo.entity.music_des;
import com.music_demo.inform.queue.Roducer;
import com.music_demo.mapper.dao.Resource;

import com.music_demo.redis_model.Music;
import com.music_demo.security_module.encryption_model.Base64;
import com.music_demo.service_model.music2.service.offcer.musicMessage;
import com.music_demo.service_model.music2.usiciInterceptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Musicimp implements musicMessage {
    @Autowired
    Resource resource;
    @Autowired
    usiciInterceptor u;
    private static Integer yeary;

    Logger logger = Logger.getLogger(com.music_demo.service_model.music2.service.Musicimp.class);

    //获取音乐 表单
    @Override
    public Integer getCount() {
        int count = resource.getCount();
        return count;
    }

    @Override
    public Map<String, Object> getMusicTimtle(int index, int title, Integer type, String key, Integer date) {
        Map<String, Object> map = new HashMap<>();
        String where = getWhere(type, date);
        System.out.println("efwfdf::" + where);
        List<music> music_dm = resource.get_Music_dms(key, (index - 1) * 20, title, where);
        if (music_dm == null) {
            logger.error("读取的数据库为空");
            throw new NullPointerException();
        }
        List<music_des> list = Resolves(music_dm, null);
        map.put("musicBean", music_dm);
        map.put("music_desBean", list);
        return map;
    }

    //命令生成器
    private String getWhere(Integer type, Integer date) {
        String datew = "";
        String typew = "";
        String where = "";
        if (date == 0) {
            datew = "";
        } else if (date == -1) {
            datew = "Year(`music_data`)<" + (yeary - 3);
        } else {
            datew = "Year(`music_data`)=" + date;
        }

        if (type == 100000) {
            typew = "";
        } else {
            typew = "`music_type`=" + type;
        }

        if (type != 100000 && date != 0) {
            where = "where " + datew + " and " + typew;
        } else if (type != 100000 || date != 0) {
            //这个其中一个为空
            where = "where  " + datew + typew;
        }
        return where;
    }

    @Override
    //获取音乐详细--完全真地址
    public music getMusic(Long id) {

        music musicind = resource.get_Musicind(id);
        if (musicind == null) {
            logger.error(Musicimp.class + "读取的数据库为空");
            throw new NullPointerException();
        }
        return musicind;
    }

    //模糊搜索
    @Override
    public List<music_des> getVague(String crux) {
        List<music> musicLike = resource.get_MusicLike(crux);
        if (musicLike == null)
            musicLike = new ArrayList<>();
        List<music_des> list = Resolves(musicLike, null);

        return list;
    }

    //解析器
    private List<music_des> Resolves(List<music> mis, String username) {
        List<music_des> list = new ArrayList<>();
        for (music mi : mis
        ) {
            music_des music_des = new music_des();
            String s = Base64.encodeToString(String.valueOf(mi.getId()).getBytes());
            music_des.setIdpas(s);
            mi.setIdm(s);
            music_des.setFile_music(u.getAudioHost() + s);
            music_des.setFile_img(u.getImgHost() + s);
            music_des.setMusic_author_name(mi.getMusic_author_name());
            music_des.setMusic_name(mi.getMusic_name());
            music_des.setMusic_index(mi.getMusic_index());
            music_des.setFile_lyirc(mi.getFile_lyirc());
            music_des.setContext(mi.getContext());
            //查询表示状态
            if (username != null) {
                String user = mi.getUsername();
                if (user != null)
                    music_des.setLogin(1);
                else
                    music_des.setLogin(0);

            }
            list.add(music_des);
        }

        return list;
    }

    //获取给去最新
    public Integer getNewDate() {
        Date newDate = resource.getNewDate();
        int year = newDate.getYear();

        return year;
    }

    public List<Map<String, String>> getNewDates() {
        Date newDate = resource.getNewDate();
        //获取最新的音乐年数 SQL数据库默认选择1900 到现在的时间念书
        int year = newDate.getYear() + 1900;
        //解析
        List<Map<String, String>> list = new ArrayList<>();

        //头部配置
        Map<String, String> newmap = new HashMap<>();
        newmap.put("title", "默认");
        newmap.put("path", "/title/music/title/date/0");
        list.add(newmap);
        for (int i = year; i > year - 4; i--) {
            Map<String, String> map = new HashMap<>();
            map.put("title", i + "");
            map.put("path", "/music/title/date/" + i);
            list.add(map);
        }
        Map<String, String> oldmap = new HashMap<>();
        oldmap.put("title", "更早");
        oldmap.put("path", "/title/music/title/date/-1");
        list.add(oldmap);
        return list;
    }

    /**
     * @param index
     * @param title
     * @param type
     * @param key
     * @param username
     * @return
     * @test:获取login信息
     */
    @Override
    public Map<String, Object> getUserMusicTimtle(int index, int title, Integer type, String key, Integer date, String username) {
        Map<String, Object> map = new HashMap<>();
        String wehere = getWhere(type, date);
        List<music> userMusic = resource.getUserMusic(key, (index - 1) * 20, title, wehere, username);
        if (userMusic == null) {
            logger.error("读取的数据库为空");
            throw new NullPointerException();
        }
        List<music_des> resolves = Resolves(userMusic, username);
        map.put("musicBean", userMusic);
        map.put("music_desBean", resolves);
        return map;
    }

}

