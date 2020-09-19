package com.music_demo.entity;

import java.io.Serializable;
import java.sql.Date;

public class music implements Serializable {
    //音乐id
    private Long id;
    private String idm;

    //路径
    private String file_music;
    //封面
    private String file_img;
    //哥名
    private String music_name;
    //作者
    private String music_author_name;
    //上传作者
    private String file_music_author_name;
    //歌的特色
    private Integer music_type;

    //发布时间
    private Date music_data;
    //版权到期时间
    private String music_cpy_music_data;
    //是否有歌词
    private Integer music_ts = 0;
    //如果存在,歌词
    private String file_lyirc;
    //音乐类型 0-普通  1-vip  3-专卖
    private Integer music_index;
    private Integer context;
    private Integer is_play = 0;
    //备选
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }

    public String getIdm() {
        return idm;
    }

    public void setIs_play(Integer is_play) {
        this.is_play = is_play;
    }

    public Integer getIs_play() {
        return is_play;
    }

    public void setMusic_data(Date music_data) {
        this.music_data = music_data;
    }

    public Date getMusic_data() {
        return music_data;
    }

    public void setContext(Integer context) {
        this.context = context;
    }

    public Integer getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "music{" +
                "id=" + id +
                ", idm='" + idm + '\'' +
                ", file_music='" + file_music + '\'' +
                ", file_img='" + file_img + '\'' +
                ", music_name='" + music_name + '\'' +
                ", music_author_name='" + music_author_name + '\'' +
                ", file_music_author_name='" + file_music_author_name + '\'' +
                ", music_type=" + music_type +
                ", music_data='" + music_data + '\'' +
                ", music_cpy_music_data='" + music_cpy_music_data + '\'' +
                ", music_ts=" + music_ts +
                ", file_lyirc='" + file_lyirc + '\'' +
                ", music_index=" + music_index +
                ", contxte=" + context +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFile_music(String file_music) {
        this.file_music = file_music;
    }

    public void setFile_img(String file_img) {
        this.file_img = file_img;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public void setMusic_author_name(String music_author_name) {
        this.music_author_name = music_author_name;
    }

    public void setFile_music_author_name(String file_music_author_name) {
        this.file_music_author_name = file_music_author_name;
    }

    public void setMusic_type(Integer music_type) {
        this.music_type = music_type;
    }

    public void setMusic_cpy_music_data(String music_cpy_music_data) {
        this.music_cpy_music_data = music_cpy_music_data;
    }

    public void setMusic_ts(Integer music_ts) {
        this.music_ts = music_ts;
    }

    public void setFile_lyirc(String file_lyirc) {
        this.file_lyirc = file_lyirc;
    }

    public void setMusic_index(Integer music_index) {
        this.music_index = music_index;
    }

    public String getFile_music() {
        return file_music;
    }

    public String getFile_img() {
        return file_img;
    }

    public String getMusic_name() {
        return music_name;
    }

    public String getMusic_author_name() {
        return music_author_name;
    }

    public String getFile_music_author_name() {
        return file_music_author_name;
    }

    public Integer getMusic_type() {
        return music_type;
    }


    public String getMusic_cpy_music_data() {
        return music_cpy_music_data;
    }

    public Integer getMusic_ts() {
        return music_ts;
    }

    public String getFile_lyirc() {
        return file_lyirc;
    }

    public Integer getMusic_index() {
        return music_index;
    }


}
