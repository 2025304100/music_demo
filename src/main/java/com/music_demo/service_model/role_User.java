package com.music_demo.service_model;

import com.music_demo.entity.collect_en;
import com.music_demo.mapper.dao.role_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class role_User {
    @Autowired
    role_Mapper rs;

    public boolean ser_Collect(String username, long id) {
        collect_en co = new collect_en();
        co.setMusic_id(id);
        co.setUsername(username);
        Long ids = rs.collect(co);
        if (ids != null)
            return true;

        return false;
    }

    public Integer set_Coller(String username, Long music_id) {
        Integer id = 0;
        Integer sel = 0;
        collect_en collect_en = new collect_en();
        collect_en.setMusic_id(music_id);
        collect_en.setUsername(username);
        collect_en.setIs_buy(0);
        collect_en.setDate(new Date(new java.util.Date().getTime()));
        sel = rs.slectCollect(collect_en.getUsername(), collect_en.getMusic_id());
        if (sel == null)
            id = rs.setConllect(collect_en);
        return id;
    }

    public Integer remove_Coller(String username, Long music_id) {
        Integer state = rs.removeCollect(username, music_id);
        return state;
    }
}
