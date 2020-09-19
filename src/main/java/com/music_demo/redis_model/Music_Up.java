package com.music_demo.redis_model;

import java.io.Serializable;

//Serializable序列化操作
public class Music_Up implements Serializable {
    //图片路径
    private String img;
    //音乐缓存路径
    private String musicchach;

    public void setImg(String img) {
        this.img = img;
    }

    public void setMusicchach(String musicchach) {
        this.musicchach = musicchach;
    }

    public void setMusic(String music) {
        this.music = music;
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

    //音乐存储路径
    private String music;
}
