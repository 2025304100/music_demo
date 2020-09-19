package com.music_demo.entity;


import java.io.Serializable;

public class User_entity implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String pas;
    private String file_img;
    private String sex;
    private String email;
    private String phon;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public void setFile_img(String file_img) {
        this.file_img = file_img;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPas() {
        return pas;
    }

    public String getFile_img() {
        return file_img;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", pas='" + pas + '\'' +
                ", file_img='" + file_img + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phon='" + phon + '\'' +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getPhon() {
        return phon;
    }
}
