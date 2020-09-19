package com.music_demo.service_model;

import com.music_demo.entity.music;
import com.music_demo.mapper.dao.Resource;
import com.music_demo.service_model.serviceimp.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceImp implements resource {
    @Autowired
    Resource resource;

    @Override
    public boolean Music_Insert(music m) {
        System.out.println(m.toString());
        if (m.getFile_lyirc() != null)
            m.setMusic_ts(1);
        int i = resource.music_red(m);
        //  i=  resource.music_redpa(m);
        if (i == 1)
            return true;
        else return false;
    }
}
