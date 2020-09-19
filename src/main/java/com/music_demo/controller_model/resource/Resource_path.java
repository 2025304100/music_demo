package com.music_demo.controller_model.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.path")
public class Resource_path {
    //图片路径
    private String img;
    //音乐缓存路径
    private String musicchach;
    //音乐存储路径
    private String music;
    //加密密钥
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }


    public String getImg() {
        return img;
    }

    public String getMusicchach() {
        return musicchach;
    }

    public String getMusic() {
        return music;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setMusicchach(String musicchach) {
        this.musicchach = musicchach;
    }

    public void setMusic(String music) {
        this.music = music;
    }


}
