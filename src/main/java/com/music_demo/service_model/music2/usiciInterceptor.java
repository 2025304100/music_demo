package com.music_demo.service_model.music2;

import com.music_demo.entity.music;
import com.music_demo.mapper.dao.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "com.music.host")
public class usiciInterceptor {
    private String audioHost;
    private String imgHost;

    public void setAudioHost(String audioHost) {
        this.audioHost = audioHost;
    }

    public void setImgHost(String imgHost) {
        this.imgHost = imgHost;
    }

    public String getAudioHost() {
        return audioHost;
    }

    public String getImgHost() {
        return imgHost;
    }
}



