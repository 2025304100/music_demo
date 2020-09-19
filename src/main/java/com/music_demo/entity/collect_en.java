package com.music_demo.entity;

import java.io.Serializable;
import java.sql.Date;

public class collect_en implements Serializable {
    private Long id;
    private long music_id;
    private String username;
    private Integer is_buy;
    private Date data_time;

    public void setId(Long id) {
        this.id = id;
    }

    public void setIs_buy(Integer is_buy) {
        this.is_buy = is_buy;
    }

    public void setDate(Date date) {
        this.data_time = date;
    }

    public Long getId() {
        return id;
    }

    public Integer getIs_buy() {
        return is_buy;
    }

    public Date getDate() {
        return data_time;
    }


    public void setMusic_id(long music_id) {
        this.music_id = music_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getMusic_id() {
        return music_id;
    }

    public String getUsername() {
        return username;
    }

}
