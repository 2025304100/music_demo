package com.music_demo.entity;

import java.io.Serializable;

//序列化
public class musicType implements Serializable {
    private Integer id;
    private String musictypeZh;
    private Integer music_type;
    //前端适配器
    private String page;

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setMusictypeZh(String musictypeZh) {
        this.musictypeZh = musictypeZh;
    }

    public void setMusic_type(Integer music_type) {
        this.music_type = music_type;
    }

    public Integer getMusic_type() {
        return music_type;
    }

    public String getMusictypeZh() {
        return musictypeZh;
    }
}
