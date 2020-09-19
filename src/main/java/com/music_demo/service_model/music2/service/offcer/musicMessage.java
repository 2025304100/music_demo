package com.music_demo.service_model.music2.service.offcer;

import com.music_demo.entity.music;
import com.music_demo.entity.music_des;

import java.util.List;
import java.util.Map;

public interface musicMessage {
    public List<Map<String, String>> getNewDates();

    public Integer getCount();

    public Map<String, Object> getMusicTimtle(int index, int title, Integer type, String key, Integer date);

    public music getMusic(Long id);

    public List<music_des> getVague(String crux);

    //登录获取信息
    public Map<String, Object> getUserMusicTimtle(int index, int title, Integer type, String key, Integer date, String username);
}
