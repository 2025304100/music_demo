package com.music_demo.entity;

import java.io.Serializable;
import java.sql.Date;


public class Comment implements Serializable {
    private Long id;
    private Long music_id;
    private String profileint;
    private String after_review;
    private String pror_name;
    private String after_name;
    private String profile_text;
    private String file_img;
    private Date publish_data = new Date(System.currentTimeMillis());

    public void setAfter_name(String after_name) {
        this.after_name = after_name;
    }

    public String getAfter_name() {
        return after_name;
    }

    private Integer click;


    public void setPror_name(String pror_name) {
        this.pror_name = pror_name;
    }

    public String getPror_name() {
        return pror_name;
    }

    public void setPublish_data(Date publish_data) {
        this.publish_data = publish_data;
    }

    public Date getPublish_data() {
        return publish_data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMusic_id(Long music_id) {
        this.music_id = music_id;
    }

    public void setProfileint(String profileint) {
        this.profileint = profileint;
    }

    public void setAfter_review(String after_review) {
        this.after_review = after_review;
    }

    public void setProfile_text(String profile_text) {
        this.profile_text = profile_text;
    }

    public void setFile_img(String file_img) {
        this.file_img = file_img;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Long getId() {
        return id;
    }

    public Long getMusic_id() {
        return music_id;
    }

    public String getProfileint() {
        return profileint;
    }

    public String getAfter_review() {
        return after_review;
    }

    public String getProfile_text() {
        return profile_text;
    }

    public String getFile_img() {
        return file_img;
    }

    public Integer getClick() {
        return click;
    }

}
