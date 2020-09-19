package com.music_demo.service_model;

import com.music_demo.mapper.dao.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class music {
    @Autowired
    Resource resource;

    //获取音乐 表单
    public Map<String, String> getMusic(int index, int title, String type) {
        List<com.music_demo.entity.music> music_dm = resource.get_Music_dm(type, (index - 1) * 20, title);

        return null;
    }
    //加工-加密

}
